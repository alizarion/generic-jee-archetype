package io.github.alizarion.common.api;

import io.github.alizarion.common.entities.*;
import io.github.alizarion.common.services.EntityFacade;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
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
    @Path("/{id}/addresses")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Address addAddress(Address address, @PathParam("id") Long personId){
        return this.facade.createAdress(personId,address);
    }

    @GET
    @Path("/{id}/addresses")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public List<Address> addAddress(@PathParam("id") Long personId){
        return new ArrayList<>(this.facade.findAddressesByID(personId));
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
        Set<Address> s= new HashSet<>();
        s.add(new AddressMail("mail@mail.com"));
        s.add(new AddressTel("+33","69854125"));
        s.add(new AddressPostal("15 rue de la fleur","34090","Mtp","France"));
        return new Person("selim","bensenouci",s);
    }
}
