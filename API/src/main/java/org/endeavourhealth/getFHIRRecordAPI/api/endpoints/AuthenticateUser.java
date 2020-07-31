package org.endeavourhealth.getFHIRRecordAPI.api.endpoints;

import models.*;
import org.apache.http.HttpStatus;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.Response;
import static javax.ws.rs.client.Entity.form;

public class AuthenticateUser {
    private static final Logger LOG = LoggerFactory.getLogger(AuthenticateUser.class);

    public Object handleRequest(UserAuthRequest request) throws ResourceNotFoundException {
        String accessToken = "";

        switch (request.getHttpMethod()) {
            case "POST":
                UserAuth userAuth = request.getParams();

                try {
                    Client client = ClientBuilder.newClient();
                    String url = "https://devauth.discoverydataservice.net/";
                    String path = "auth/realms/endeavour2/protocol/openid-connect/token";

                    WebTarget target = client.target(url).path(path);

                    try {
                        Form form = null;

                        form = new Form()
                                .param("grant_type", userAuth.getGrantType())
                                .param("client_id", userAuth.getClientId())
                                .param("client_secret", userAuth.getClientSecret());

                        Response response = target
                                .request()
                                .post(form(form));

                        if (response.getStatus() == HttpStatus.SC_OK) { // user is authenticated in keycloak, so get the user's access token
                            String loginResponse = response.readEntity(String.class);
                            JSONParser parser = new JSONParser();

                            JSONObject jsonobj = (JSONObject) parser.parse(loginResponse);
                            jsonobj.remove("refresh_token");
                            jsonobj.remove("refresh_expires_in");

                            return jsonobj;
                        } else { // user is not authenticated in Keycloak
                            throw new RuntimeException("Unauthorized Client: "+userAuth.getClientId()+", httpStatus: "+response.getStatus()); // Not authenticated so send 401/403 Unauthorized response to the client
                        }

                    } catch (Exception ex) {
                        throw new RuntimeException("Keycloak error: "+ex.getMessage());
                    }
                } catch (Exception e) {
                    throw new ResourceNotFoundException("Resource error:" + e);
                }

            default:
                // throw exception if called method is not implemented
                break;
        }
        return null;
    }


}
