package io.github.alizarion.common.dao;

import io.github.alizarion.common.entities.Groups;

import javax.persistence.EntityManager;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by VCO on 27/07/2016.
 */
public class GroupDao {

    private EntityManager em;

    public GroupDao(EntityManager em) {
        this.em = em;
    }


    @SuppressWarnings("unchecked")
    public Set<Groups> findAll() {
            return new HashSet<>(
                    this.em.createNamedQuery(Groups.FIND_ALL_GROUPS)
                            .getResultList());
    }
}
