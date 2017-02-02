package io.github.alizarion.common.entities;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @author selim@openlinux.fr.
 */
@Entity
@NamedQuery(name = Person.FIND_ALL_PERSONS,
        query = "select p from Person p")
@Table(name = "person")
@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public class Person implements Serializable {

    public static final String FIND_ALL_PERSONS = "Person.FIND_ALL_PERSONS";

    @Id
    @Column(name = "id")
    @XmlAttribute
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @XmlAttribute
    @Column(name = "first_name")
    private String firstName;

    @XmlAttribute
    @Column(name = "last_name")
    private String lastName;


    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @XmlAttribute
    private Set<Address> addresses = new HashSet<>();

    protected Person() {

    }

    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Person(String firstName, String lastName,Set<Address> s) {
        this.firstName = firstName;
        this.lastName = lastName;
        addresses = s;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @XmlElementRef
    public Set<Address> getAddresses() {return addresses;}
    public void setAddresses(Set<Address> a) { addresses = a;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        Person person = (Person) o;
        return Objects.equals(id, person.id) &&
                Objects.equals(firstName, person.firstName) &&
                Objects.equals(lastName, person.lastName) &&
                Objects.equals(addresses, person.addresses);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName,addresses);
    }
}


