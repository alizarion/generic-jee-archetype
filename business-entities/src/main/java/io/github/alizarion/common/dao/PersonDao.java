package io.github.alizarion.common.dao;

import io.github.alizarion.common.entities.Person;

import javax.persistence.EntityManager;
import java.util.HashSet;
import java.util.Set;

/**
 * @author selim@openlinux.fr.
 */
public class PersonDao {

    private EntityManager em;

    public PersonDao(EntityManager em) {
        this.em = em;
    }


    @SuppressWarnings("unchecked")
    public Set<Person> findAll() {
            return new HashSet<>(
                    this.em.createNamedQuery(Person.FIND_ALL_PERSONS)
                            .getResultList());
    }
}
