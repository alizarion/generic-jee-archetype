package io.github.alizarion.common.secure.login;

import io.github.alizarion.common.secure.exception.BadLoginException;

/**
 * @author sbn
 */

public interface AuthenticationProvider {

    public Boolean authenticate(final String username,final String password) throws BadLoginException;

    public String issueToken(final String username);
}
