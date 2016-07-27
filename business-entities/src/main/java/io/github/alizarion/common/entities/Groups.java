package io.github.alizarion.common.entities;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by VCO on 27/07/2016.
 */
@Entity
@NamedQuery(name = Groups.FIND_ALL_GROUPS,
        query = "select p from Groups p")
@Table(name = "groups")
@XmlAccessorType(XmlAccessType.NONE)
public class Groups implements Serializable {

    public static final String FIND_ALL_GROUPS = "Groups.FIND_ALL_GROUPS";

    @Id
    @Column(name = "id")
    @XmlAttribute
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany
    @XmlAttribute
    private List<Person> persons = new ArrayList<>();

    public Groups(){

    }

    public Groups(Person aPerson){
        this.persons.add(aPerson);
    }
    public Groups(Long aId){
        this.id=aId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }
}
