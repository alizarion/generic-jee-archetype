package io.github.alizarion.common.api;

import io.github.alizarion.common.entities.Groups;
import io.github.alizarion.common.entities.Person;
import io.github.alizarion.common.services.EntityFacade;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by VCO on 27/07/2016.
 */
@Path("/groups")
public class groupsRessources {

    @Inject
    @Named("entityFacade")
    EntityFacade facade;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Groups> findGroups(){
        return new ArrayList<>(facade.findAllGroups());
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Groups getGroup(@PathParam("id") Long id){
        return facade.findGroupByID(id);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/test")
    public Groups testMethod(){
        return new Groups(new Person("selim","bensenouci","selim@openlinux.fr"));
    }
}
