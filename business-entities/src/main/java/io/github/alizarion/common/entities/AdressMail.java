package io.github.alizarion.common.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAttribute;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

/**
 * @author Lucas CHABALIER on 31/01/2017.
 */
@Entity
@Table(name="adress_mail")
public class AdressMail extends Adress implements Serializable{

    @XmlAttribute
    @Column(name="mail")
    private String mail;

    public String getMail() {return mail;}
    public void setMail(String mail) {this.mail = mail;}

    public AdressMail() {}

    public AdressMail(String mail) {
        this.mail = mail;
    }

    @Override
    public boolean equals(Object o) {
        if(this==o) return true;
        if(!(o instanceof AdressMail)) return false;
        AdressMail ap = (AdressMail) o;
        return Objects.equals(mail, ap.getMail());
    }

    @Override
    public int hashCode() { return Objects.hash(mail);}


}
