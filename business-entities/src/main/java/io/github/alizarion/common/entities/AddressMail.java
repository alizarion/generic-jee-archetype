package io.github.alizarion.common.entities;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

/**
 * @author Lucas CHABALIER on 31/01/2017.
 */
@Entity
@XmlAccessorType(XmlAccessType.NONE)
@Table(catalog="address")
@XmlType(name=AddressMail.EMAIL_ADDRESS_TYPE)
@XmlRootElement
@DiscriminatorValue(value = AddressMail.EMAIL_ADDRESS_TYPE )
public class AddressMail extends Address implements Serializable{

    public static final String EMAIL_ADDRESS_TYPE = "mail";

    @XmlAttribute
    @Column(name="mail")
    private String mail;

    public String getMail() {return mail;}
    public void setMail(String mail) {this.mail = mail;}

    public AddressMail() {}

    public AddressMail(String mail) {
        this.mail = mail;
    }

    @Override
    public boolean equals(Object o) {
        if(this==o) return true;
        if(!(o instanceof AddressMail)) return false;
        AddressMail ap = (AddressMail) o;
        return Objects.equals(mail, ap.getMail());
    }

    @Override
    public int hashCode() { return Objects.hash(mail);}


}
