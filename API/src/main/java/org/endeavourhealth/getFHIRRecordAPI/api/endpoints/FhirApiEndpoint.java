package org.endeavourhealth.getFHIRRecordAPI.api.endpoints;
import com.codahale.metrics.annotation.Timed;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.endeavourhealth.common.security.annotations.RequiresAdmin;
import org.endeavourhealth.coreui.endpoints.AbstractEndpoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("/getFHIRRecordAPI")
@Api(description = "API endpoint related to the HL7v2 API")
public final class FhirApiEndpoint extends AbstractEndpoint {
    private static final Logger LOG = LoggerFactory.getLogger(FhirApiEndpoint.class);

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.TEXT_PLAIN)
    @Timed(absolute = true, name="getFHIRRecordAPI.getFHIRRecordAPIEndpoint.Get")
    @Path("/$process-message")
    @ApiOperation(value = "Return the Fhir Message ")
    @RequiresAdmin
    public Response processMessage(String request ) throws Exception {

        LOG.info("Request for Fhir message received "+request);


        return prepareFhirMessage(request);
    }

    private Response prepareFhirMessage(String request) {
        try {
      // prepare the Fhir Message by using Recordviewe API jar

            String test =  "{ \"Response\" : \" "/*+request.getResourceType()*/+" : Message Filed Successfully! \"}";

            return Response
                    .ok()
                    .entity(test)
                    .build();

        } catch (Exception e) {
            throw new RuntimeException("Resource error:" + e);
        }
    }


}

