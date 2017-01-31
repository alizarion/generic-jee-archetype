package io.github.alizarion.common.entities;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Created by Lucas CHABALIER on 31/01/2017.
 */

@Entity
@Table(name="adress")
@XmlAccessorType(XmlAccessType.NONE)
public abstract class Adress implements Serializable{
    @Id
    @Column(name = "id")
    @XmlAttribute
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @XmlAttribute
    @Column(name = "person")
    private Set<Person> persons = new HashSet<Person>();

    public Adress(Set<Person> p) {
        persons = p;
    }

    public Long getId() {return id;}
    public Set<Person> getPerson() { return persons;}

    public void setId(long id) { this.id = id;}
    public void setPerson(Set<Person> person) { this.persons = person;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Adress adress = (Adress) o;
        return Objects.equals(id, adress.id) &&
                Objects.equals(persons, adress.persons);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, persons);
    }
}
