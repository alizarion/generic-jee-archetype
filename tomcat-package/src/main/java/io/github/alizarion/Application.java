package io.github.alizarion;

import io.github.alizarion.common.tools.web.filters.AccessControlAllowOriginFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;

/**
 * Application
 */
@SpringBootApplication
public class Application extends SpringBootServletInitializer
{
    private static Class<Application> appClass = Application.class;

    public static void main(String[] args) {

        SpringApplication.run(Application.class, args);
    }

    @Bean
    @Order(-1)
    public FilterRegistrationBean contextFilterRegistrationBean() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        AccessControlAllowOriginFilter contextFilter = new AccessControlAllowOriginFilter();
        registrationBean.setFilter(contextFilter);
        registrationBean.setOrder(-1);
        return registrationBean;
    }


    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(appClass);
    }
}
