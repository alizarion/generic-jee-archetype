package io.github.alizarion.common.api;

import io.github.alizarion.common.entities.*;
import io.github.alizarion.common.services.EntityFacade;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author selim@openlinux.fr.
 */
@Path("/persons")
public class PersonRessource {

    @Inject
    @Named("entityFacade")
    EntityFacade facade;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Person> findPersons(){
        return new ArrayList<>(facade.findAllPerson());
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Person updatePerson(Person person){
        return facade.mergePerson(person);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({"admin"})
    @Consumes(MediaType.APPLICATION_JSON)
    public Person createPerson(Person person){
        return facade.mergePerson(person);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @PermitAll
    @Path("/{id}")
    public Person getPerson(@PathParam("id") Long id){
        return facade.findPersonByID(id);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/test")
    public Person testMethod(){
        return new Person("selim","bensenouci");
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/user")
    public MyCustomPrincipal testMethod(@Context SecurityContext securityContext){
        return (MyCustomPrincipal) securityContext.getUserPrincipal();
    }
}
