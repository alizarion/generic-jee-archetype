package io.github.alizarion;

import io.github.alizarion.common.api.PersonRessource;
import io.github.alizarion.common.tools.web.filters.AccessControlAllowOriginFilter;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import javax.inject.Named;
import javax.ws.rs.ApplicationPath;

/**
 * @author selim@openlinux.fr.
 */
@SpringBootApplication
@ComponentScan( {"io.github.alizarion"})
public class Application extends SpringBootServletInitializer {

    private static Class<Application> appClass = Application.class;


    @Bean
    public FilterRegistrationBean contextFilterRegistrationBean() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        AccessControlAllowOriginFilter contextFilter = new AccessControlAllowOriginFilter();
        registrationBean.setFilter(contextFilter);
        registrationBean.setOrder(1);
        return registrationBean;
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(appClass);
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}