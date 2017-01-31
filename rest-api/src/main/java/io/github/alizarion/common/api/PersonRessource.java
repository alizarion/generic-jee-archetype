package io.github.alizarion.common.api;

import io.github.alizarion.common.entities.Adress;
import io.github.alizarion.common.entities.Person;
import io.github.alizarion.common.services.EntityFacade;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

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
    @Path("/{id}/address")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Adress addAddress(Adress address, @PathParam("id") Long personId){
        return this.facade.createAdress(personId,address);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Person createPerson(Person person){
        return facade.mergePerson(person);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
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
}
