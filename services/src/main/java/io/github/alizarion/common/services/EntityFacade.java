package io.github.alizarion.common.services;

import io.github.alizarion.common.dao.PersonDao;
import io.github.alizarion.common.entities.Person;

import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.Set;

/**
 * @author selim@openlinux.fr.
 */
@Named("entityFacade")
public class EntityFacade implements Serializable {

    @PersistenceContext
    EntityManager em;

    private PersonDao personDao;


    @PostConstruct
    protected void init(){
        this.personDao =
                new PersonDao(this.em);
    }


    @Transactional
    public Person findPersonByID(final Long id){
      return this.em.find(Person.class,id);
    }

    @Transactional
    public Person mergePerson(final Person person){
       return this.em.merge(person);
    }

    @Transactional
    public Set<Person> findAllPerson(){
           return this.personDao.findAll();
        }
}
