package org.endeavourhealth.getFHIRRecordAPI.api.endpoints;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import org.apache.http.HttpStatus;
import org.endeavourhealth.getFHIRRecordAPI.common.dal.JDBCDAL;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

public class HL7APIGatewayAuthorizerHandler implements RequestHandler<TokenAuthorizerContext, AuthPolicy> {

    JDBCDAL viewerDAL;

    public AuthPolicy handleRequestByPass(TokenAuthorizerContext input, Context context) {

        // bypass auth for test, will use method below once keycloak login available for client caller

        String methodArn = input.getMethodArn();
        String[] arnPartials = methodArn.split(":");
        String region = arnPartials[3];
        String awsAccountId = arnPartials[4];
        String[] apiGatewayArnPartials = arnPartials[5].split("/");
        String restApiId = apiGatewayArnPartials[0];
        String stage = apiGatewayArnPartials[1];
        String httpMethod = apiGatewayArnPartials[2];
        String resource = ""; // root resource
        if (apiGatewayArnPartials.length == 4) {
            resource = apiGatewayArnPartials[3];
        }

        return new AuthPolicy("XXX", AuthPolicy.PolicyDocument.getAllowAllPolicy(region, awsAccountId, restApiId, stage));
    }

    JDBCDAL getgetFHIRRecordAPIObject(){
        return new JDBCDAL();
    }

    @Override
    public AuthPolicy handleRequest(TokenAuthorizerContext input, Context context) {

        String headerAuthToken = input.getAuthorizationToken();
        String userID = "";

        // validate the incoming authorization token by calling dev keycloak and produce the principal user identifier associated with the token
        Client client = ClientBuilder.newClient();
        String url = "https://devauth.discoverydataservice.net/";
        String path = "auth/realms/endeavour2/protocol/openid-connect/userinfo";

        WebTarget target = client.target(url).path(path);

        Boolean foundFHIRPolicy = false;

        try {
            Response response = target
                    .request()
                    .header("Authorization", "Bearer "+headerAuthToken)
                    .get();

            if (response.getStatus() == HttpStatus.SC_OK) { // user is authorized in keycloak, so get the user record and ID associated with the token
                String entityResponse = response.readEntity(String.class);
                JSONParser parser = new JSONParser();
                JSONObject users = (JSONObject) parser.parse(entityResponse);
                userID = users.get("sub").toString();

                // query user manager with the user ID to check the user's authorized applications and policies match
                viewerDAL = getgetFHIRRecordAPIObject();
                foundFHIRPolicy = viewerDAL.applicationAccessProfile(userID, "hl7v2-api");

                //foundFHIRPolicy = true; // force OK until user had been set up in user manager for this client

            } else { // user is not authorized with this token
                throw new RuntimeException("Unauthorized"); // Not authorized so send 401/403 Unauthorized response to the client
            }

        } catch (Exception ex) {
            throw new RuntimeException("Unauthorized"); // Not authorized so send 401/403 Unauthorized response to the client
        }

        if (!foundFHIRPolicy) {
            throw new RuntimeException("Unauthorized"); // Not authorized so send 401 Unauthorized response to the client
        }

        // if the token is valid, and there is a user policy for this application, a policy is generated which will allow or deny access to the client
        // if access is allowed, API Gateway will proceed with the back-end integration configured on the method that was called

        String methodArn = input.getMethodArn();
        String[] arnPartials = methodArn.split(":");
        String region = arnPartials[3];
        String awsAccountId = arnPartials[4];
        String[] apiGatewayArnPartials = arnPartials[5].split("/");
        String restApiId = apiGatewayArnPartials[0];
        String stage = apiGatewayArnPartials[1];
        String httpMethod = apiGatewayArnPartials[2];
        String resource = ""; // root resource
        if (apiGatewayArnPartials.length == 4) {
            resource = apiGatewayArnPartials[3];
        }

        return new AuthPolicy(userID, AuthPolicy.PolicyDocument.getAllowAllPolicy(region, awsAccountId, restApiId, stage));
    }

}
