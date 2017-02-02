package io.github.alizarion.common.entities;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.util.Objects;

/**
 * @author Lucas CHABALIER on 31/01/2017.
 */

@Entity
@Table(name="address")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@DiscriminatorColumn(name = Address.ADDRESS_TYPE_COLUMN)
@XmlSeeAlso({AddressPostal.class, AddressMail.class,AddressTel.class})
public abstract class Address implements Serializable{

    public static final String ADDRESS_TYPE_COLUMN = "address_type";

    @Id
    @Column(name = "id")
    @XmlAttribute
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;



    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;

    @Column(name = Address.ADDRESS_TYPE_COLUMN,updatable = false,insertable = false)
    private String addressType;

    public Long getId() {return id;}

    public void setId(long id) { this.id = id;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address adress = (Address) o;
        return Objects.equals(id, adress.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
