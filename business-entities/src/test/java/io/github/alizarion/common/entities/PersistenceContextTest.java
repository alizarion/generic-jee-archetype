package io.github.alizarion.common.entities;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * @author Lucas CHABALIER on 31/01/2017.
 */
public class PersistenceContextTest {

    private EntityManagerFactory emf;
    private EntityManager em;

    @Before
    public void init(){
        emf = Persistence.createEntityManagerFactory("test");
        if(emf != null)
            em = emf.createEntityManager();
        // initialiser le context de persistence

    }

    @After
    public void after(){
        // décharger le context de persistence
        System.out.println("Fermeture EntityManger / EntityMangerFactory");
        em.close();
        emf.close();
    }


    @Test
    public void testContext(){
        System.out.println("TestContext!");
        EntityTransaction et = em.getTransaction();
        et.begin();
        Person newP = new Person("firstName","lastName");
        em.persist(newP);
        et.commit();
    }

    @Test
    public void testContextAddress(){
        EntityTransaction et = em.getTransaction();
        et.begin();
        Person newP = new Person("firstName","lastName");
        Set<Address> s= new HashSet<>();
        s.add(new AddressMail("mail@mail.com"));
        s.add(new AddressTel("+33","69854125"));
        s.add(new AddressPostal("15 rue de la fleur","34090","Mtp","France"));
        newP.setAddresses(s);
        em.persist(newP);
        et.commit();
    }

    @Test
    public void testContextAddressOnExistingPerson(){
        EntityTransaction et = em.getTransaction();
        et.begin();
        Person newP = new Person("firstName","lastName");
        em.persist(newP);

        Person p = em.find(Person.class,Integer.toUnsignedLong(1));
        Set<Address> s= new HashSet<>();
        s.add(new AddressMail("mail@mail.com"));
        s.add(new AddressTel("+33","69854125"));
        s.add(new AddressPostal("15 rue de la fleur","34090","Mtp","France"));
        p.setAddresses(s);
        em.merge(p);

        et.commit();
    }

}