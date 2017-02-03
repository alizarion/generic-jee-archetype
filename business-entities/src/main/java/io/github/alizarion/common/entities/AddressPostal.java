package io.github.alizarion.common.entities;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

/**
 * @author Lucas CHABALIER on 31/01/2017.
 */
@NamedQuery(name = AddressPostal.FIND_ADRESS_POSTAL,
            query="select a from AddressPostal a")
@Entity
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement
@XmlType(name=AddressPostal.POSTAL_ADDRESS_TYPE)
@DiscriminatorValue(value = AddressPostal.POSTAL_ADDRESS_TYPE )
public class AddressPostal extends Address implements Serializable {
    public static final String FIND_ADRESS_POSTAL = "AdressPostal.FIND_ADRESS_POSTAL";
    public static final String POSTAL_ADDRESS_TYPE = "postal";

    @XmlAttribute
    @Column(name="rue")
    private String rue;

    @XmlAttribute
    @Column(name="postal_code")
    private String postalCode;

    @XmlAttribute
    @Column(name="town")
    private String town;

    @XmlAttribute
    @Column(name="country")
    private String country;

    public String getRue() {return rue;}
    public String getPostalCode() {return postalCode;}
    public String getTown() {return town;}
    public String getCountry() {return country;}

    public void setRue(String rue) {this.rue = rue;}
    public void setPostalCode(String postalCode) { this.postalCode = postalCode;}
    public void setTown(String town) { this.town = town;}
    public void setCountry(String country) {this.country = country;}

    public AddressPostal() {}

    public AddressPostal(String rue, String postalCode, String town, String country) {
        this.rue= rue;
        this.postalCode = postalCode;
        this.town = town;
        this.country = country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        AddressPostal that = (AddressPostal) o;
        return Objects.equals(rue, that.rue) &&
                Objects.equals(postalCode, that.postalCode) &&
                Objects.equals(town, that.town) &&
                Objects.equals(country, that.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), rue, postalCode, town, country);
    }
}
