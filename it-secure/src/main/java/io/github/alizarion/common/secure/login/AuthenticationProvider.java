package io.github.alizarion.common.secure.login;

import io.github.alizarion.common.secure.exception.BadLoginException;

import java.security.Principal;
import java.util.Map;
import java.util.Set;

/**
 * @author sbn
 */

public interface AuthenticationProvider {

    public Boolean authenticate(final String username,final String password) throws BadLoginException;

    public String issueToken(final String username);

    public Principal getPrincipal(final Set<Map.Entry<String,Object>> claims);
}
