package io.github.alizarion.common.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAttribute;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

/**
 * Created by Lucas CHABALIER on 31/01/2017.
 */
@Entity
@Table(name="adress_tel")
public class AdressTel extends Adress implements Serializable {
    @XmlAttribute
    @Column(name="prefixe")
    private String prefixe;

    @XmlAttribute
    @Column(name="num")
    private String num;

    public AdressTel(Set<Person> p, String pre, String n) {
        super(p);
        prefixe = pre;
        num = n;
    }

    public String getPrefixe() {return prefixe;}
    public String getNum() {return num;}

    public void setPrefixe(String pre) {prefixe = pre;}
    public void setNum(String n) { num = n;}

    @Override
    public boolean equals(Object o) {
        if(this==o) return true;
        if(!(o instanceof AdressMail)) return false;
        AdressTel ap = (AdressTel) o;
        return Objects.equals(prefixe, ap.getPrefixe()) &&
               Objects.equals(num, ap.getNum());
    }

    @Override
    public int hashCode() { return Objects.hash(num, prefixe);}
}
