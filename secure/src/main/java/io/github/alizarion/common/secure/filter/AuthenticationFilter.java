package io.github.alizarion.common.secure.filter;

import io.github.alizarion.common.secure.utils.SecurityUtils;

import javax.annotation.Priority;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

/**
 * @author selim@openlinux.fr.
 */
@Provider
@Priority(Priorities.AUTHENTICATION)
public class AuthenticationFilter implements ContainerRequestFilter {

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        // Get the HTTP Authorization header from the request
               String authorizationHeader =
                   requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);

               // Check if the HTTP Authorization header is present and formatted correctly
               if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
                   throw new NotAuthorizedException("Authorization header must be provided");
               }

               // Extract the token from the HTTP Authorization header
               String token = authorizationHeader.substring("Bearer".length()).trim();

               try {

                   // Validate the token
                   SecurityUtils.validateToken(token);

               } catch (Exception e) {
                   requestContext.abortWith(
                       Response.status(Response.Status.UNAUTHORIZED).build());
               }
    }
}
