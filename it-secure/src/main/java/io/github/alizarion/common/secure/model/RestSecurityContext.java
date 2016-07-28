package io.github.alizarion.common.secure.model;

import javax.ws.rs.core.SecurityContext;
import java.security.Principal;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author sbn
 */
public class RestSecurityContext implements SecurityContext{

    private Set<Map.Entry<String, Object>> claims;

    private String jwtToken;

    public RestSecurityContext(final Set<Map.Entry<String, Object>> claims,final String token) {
        this.claims = claims;
        this.jwtToken =  token;
    }

    @Override
    public Principal getUserPrincipal() {
        return new Principal() {
            @Override
            public String getName() {
                return jwtToken;
            }
        };
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean isUserInRole(String role) {
        if (role != null){
            for (Map.Entry<String,Object> entry :  claims) {
                if (entry.getKey().equalsIgnoreCase("role") ||
                        entry.getKey().equalsIgnoreCase("roles")) {
                    if (entry.getValue() instanceof Collection) {
                        Set<String> roles =
                                new HashSet<String>((Collection) entry.getValue());
                        for (String roleEntry : roles) {
                            if (role.equalsIgnoreCase(roleEntry)){
                                return true;
                            }
                        }
                    } else {
                        if (role.equalsIgnoreCase(entry.getValue()
                                .toString())){
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    @Override
    public boolean isSecure() {
        return false;
    }

    @Override
    public String getAuthenticationScheme() {
        return null;
    }
}
