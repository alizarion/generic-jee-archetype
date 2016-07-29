package io.github.alizarion.common.secure.login;

import io.github.alizarion.common.secure.exception.BadLoginException;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;


/**
 * @author sbn sbn
 */
@Path("/authentication")
public  class AuthenticationEndPoint {

    @Inject
    @AuthenticateProvider
    AuthenticationProvider authenticationProvider;


    @POST
    @Path("/login")
    public Response login(@FormParam("username") String username,
                          @FormParam("password") String password)
            throws BadLoginException {
        if(authenticationProvider.authenticate(username,password)){
            NewCookie cookie = new NewCookie("token", authenticationProvider.issueToken(username));
            return  Response.ok("{\"token\":\""+cookie.getValue()+"\"}")
                    .cookie(cookie).build();
        } else {
            return   Response.status(Response.Status.FORBIDDEN).entity("{\"login\":\"ok\"}").build();
        }

    }

    @GET
    @Path("/test")
    public Response login(){
       return Response.ok("{\"status\":\"ok\"}").build();

    }


}
