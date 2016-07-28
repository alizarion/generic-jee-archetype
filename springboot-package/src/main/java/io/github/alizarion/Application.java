package io.github.alizarion;

import io.github.alizarion.common.api.PersonRessource;
import io.github.alizarion.common.secure.filter.AuthenticationFilter;
import io.github.alizarion.common.secure.login.AbstractAuthenticate;
import io.github.alizarion.common.tools.web.filters.AccessControlAllowOriginFilter;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import javax.inject.Named;
import javax.ws.rs.ApplicationPath;

/**
 * @author selim@openlinux.fr.
 */
@SpringBootApplication
public class Application {

    @Named
    @ApplicationPath("/rest")
    public static class JerseyConfig extends ResourceConfig {

        public JerseyConfig() {
            this.register(JacksonFeature.class);
            this.register(PersonRessource.class);
            this.register(AbstractAuthenticate.class);
            this.register(AuthenticationFilter.class);



        }
    }

    @Bean
    public FilterRegistrationBean contextFilterRegistrationBean() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        AccessControlAllowOriginFilter contextFilter = new AccessControlAllowOriginFilter();
        registrationBean.setFilter(contextFilter);
        registrationBean.setOrder(1);
        return registrationBean;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}