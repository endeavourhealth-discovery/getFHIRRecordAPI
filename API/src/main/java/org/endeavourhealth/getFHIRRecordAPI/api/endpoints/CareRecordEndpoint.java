package org.endeavourhealth.getFHIRRecordAPI.api.endpoints;

import com.google.gson.Gson;
import models.Params;
import models.Request;
import org.endeavourhealth.getFHIRRecordAPI.common.dal.JDBCDAL;
import org.endeavourhealth.getFHIRRecordAPI.common.models.*;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.io.IOException;
import java.util.*;

import static javax.ws.rs.client.Entity.form;

@Path("patient")
public class CareRecordEndpoint {
    private static final Logger LOG = LoggerFactory.getLogger(CareRecordEndpoint.class);

    @GET
    @Path("/$getstructuredrecord")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFhir(@Context SecurityContext sc,
                               @QueryParam("patientId") Integer patientId
    ) throws Exception {


        FhirApi api = getFhirApi();

        JSONObject json = api.getFhirBundle(patientId,"0", "0");

        return Response
                .ok()
                .entity(json)
                .build();
    }

    public FhirApi getFhirApi(){
        return new FhirApi();
    }


    @POST
    @Path("/$getstructuredrecord")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response postFhir(@Context HttpServletRequest httpServletRequest) throws Exception {
        LOG.debug("getFhir POST");

        String userId = httpServletRequest.getHeader("user_id");

        String request = extractRequestBody(httpServletRequest);

        Gson g = new Gson();
        Params p = g.fromJson(request, Params.class);
        Request requestModel = new Request();
        requestModel.setParams(p);
        requestModel.setHttpMethod("POST");


        FhirApi api = new FhirApi();
        try {
            JSONObject result = (JSONObject) api.handleRequest(requestModel, userId);

            return Response
                    .ok()
                    .entity(result)
                    .build();
        } catch (Exception e) {

            return Response
                    .serverError()
                    .entity(e.getMessage())
                    .build();
        }
    }


    static String extractRequestBody(HttpServletRequest request) {
        if ("POST".equalsIgnoreCase(request.getMethod())) {
            Scanner s = null;
            try {
                s = new Scanner(request.getInputStream(), "UTF-8").useDelimiter("\\A");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return s.hasNext() ? s.next() : "";
        }
        return "";
    }



}


