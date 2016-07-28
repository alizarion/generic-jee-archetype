package io.github.alizarion.common.secure.filter;

import io.github.alizarion.common.secure.model.RestSecurityContext;
import io.github.alizarion.common.secure.utils.SecurityUtils;

import javax.annotation.Priority;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.management.relation.Role;
import javax.security.auth.login.LoginException;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Method;
import java.util.*;

/**
 * @author selim@openlinux.fr.
 */
@Provider
@Priority(Priorities.AUTHENTICATION)
public class AuthenticationFilter implements ContainerRequestFilter {

    @Context
    private ResourceInfo resourceInfo;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        // Get the HTTP Authorization header from the request
        Class<?> resourceClass = resourceInfo.getResourceClass();

        Set<String> classRoles = extractRoles(resourceClass);

        Method resourceMethod = resourceInfo.getResourceMethod();
        Set<String> methodRoles = extractRoles(resourceMethod);

        if ((!resourceClass.isAnnotationPresent(PermitAll.class) &&
                methodRoles.size() == 0 &&
                classRoles.size() == 0)){
            return;
        }
        try {
            String token = getToken(requestContext);
            Set<Map.Entry<String,Object>> claims =
                    SecurityUtils.validateToken(token);
            requestContext.setSecurityContext(new RestSecurityContext(claims,getToken(requestContext)));
            if (methodRoles.size() > 0){
                for(String role : methodRoles){
                    if (requestContext.getSecurityContext().isUserInRole(role)){
                        return;
                    }
                }

            }
            if ((classRoles.size() == 0)){
                for(String role : classRoles){
                    if (requestContext.getSecurityContext().isUserInRole(role)){
                        return;
                    }
                }
            }

            requestContext.abortWith(
                    Response.status(Response.Status.UNAUTHORIZED).build());
        } catch (Exception e) {
            requestContext.abortWith(
                    Response.status(Response.Status.FORBIDDEN).build());
        }
    }

    // Extract the roles from the annotated element
    private Set<String> extractRoles(AnnotatedElement annotatedElement) {

        if (annotatedElement == null) {
            return  new HashSet<>();

        } else {
            RolesAllowed rolesAllowed = annotatedElement.getAnnotation(RolesAllowed.class);
            Set<String> roles = new HashSet<>();
            if (rolesAllowed == null ) {
                return roles;
            } else {
                String[] allowedRoles = rolesAllowed.value();
                return new HashSet<>(Arrays.asList(allowedRoles));
            }
        }
    }


    private String getToken(ContainerRequestContext  requestContext){
        // Get the HTTP Authorization header from the request
        String authorizationHeader =
                requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);

        // Check if the HTTP Authorization header is present and formatted correctly
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            throw new NotAuthorizedException("Authorization header must be provided");
        }

        // Extract the token from the HTTP Authorization header
        return authorizationHeader.substring("Bearer".length()).trim();

    }



}
