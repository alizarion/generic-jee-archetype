package io.github.alizarion.common.api;

import com.itesoft.common.utils.security.login.AuthenticateProvider;
import com.itesoft.common.utils.security.login.AuthenticationProvider;

import javax.inject.Named;
import java.security.AccessControlException;
import java.security.Principal;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;


@AuthenticateProvider
@Named
public class PersonAuthenticationProvider extends AuthenticationProvider {
    @Override
    public Boolean authenticate(String s, String s1) throws AccessControlException {
        return null;
    }

    @Override
    public Map<String, Object> getPublicClaims(String s) {
        return null;
    }

    @Override
    public Principal getPrincipal(Set<Map.Entry<String, Object>> claims) {
        return new MyCustomPrincipal( claims.stream()
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)));
    }
}
