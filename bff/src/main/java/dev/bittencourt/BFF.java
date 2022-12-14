package dev.bittencourt;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.jwt.Claim;
import org.eclipse.microprofile.jwt.Claims;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@Path("/sum")
public class BFF {
  
  @Inject
  @Claim(standard = Claims.full_name)
  String fullName;

  @Inject
  @RestClient
  Client client;

  @GET
  @Path("/{a}/{b}")
  @Produces(MediaType.TEXT_PLAIN)
  @RolesAllowed({"Admin"})

  public int sum(@PathParam("a") int a, @PathParam("b") int b) {
    return client.getSum(a, b);
  }
}
