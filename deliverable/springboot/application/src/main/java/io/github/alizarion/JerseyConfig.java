package io.github.alizarion;

import com.itesoft.common.utils.security.filter.AuthenticationFilter;
import io.github.alizarion.common.api.PersonRessource;
import org.glassfish.jersey.server.ResourceConfig;

import javax.inject.Named;
import javax.ws.rs.ApplicationPath;

@ApplicationPath("/rest")
@Named
public class JerseyConfig extends ResourceConfig
{
    public JerseyConfig() {
       register(PersonRessource.class);
       register(AuthenticationFilter.class);
    }


}
