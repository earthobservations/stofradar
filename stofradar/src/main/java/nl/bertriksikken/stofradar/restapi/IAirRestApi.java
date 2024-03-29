package nl.bertriksikken.stofradar.restapi;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/air")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface IAirRestApi {

    @GET
    @Path("/{lat}/{lon}")
    AirResult getAir(@HeaderParam("User-Agent") String userAgent, @PathParam("lat") double latitude,
            @PathParam("lon") double longitude);

}
