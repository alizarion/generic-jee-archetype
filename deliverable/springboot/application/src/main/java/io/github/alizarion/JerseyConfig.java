package io.github.alizarion;

import org.glassfish.jersey.server.ResourceConfig;

import javax.inject.Named;

@Named
public class JerseyConfig extends ResourceConfig
{
    public JerseyConfig() {
        registerEndpoints();
    }

    private void registerEndpoints() {
        packages("io.github.alizarion");
    }
}
