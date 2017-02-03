package io.github.alizarion.common.entities;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * @author Lucas CHABALIER on 31/01/2017.
 */
@Entity
@XmlAccessorType(XmlAccessType.NONE)
@XmlType(name =AddressTel.TEL_ADDRESS_TYPE)
@XmlRootElement
@DiscriminatorValue(value = AddressTel.TEL_ADDRESS_TYPE )
public class AddressTel extends Address implements Serializable {
    public static final String TEL_ADDRESS_TYPE = "tel";

    @XmlAttribute
    @Column(name="prefixe")
    private String prefixe;

    @XmlAttribute
    @Column(name="num")
    private String num;

    public AddressTel() {}

    public AddressTel(String pre, String n) {
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
        if(!(o instanceof AddressMail)) return false;
        AddressTel ap = (AddressTel) o;
        return Objects.equals(prefixe, ap.getPrefixe()) &&
               Objects.equals(num, ap.getNum());
    }

    @Override
    public int hashCode() { return Objects.hash(num, prefixe);}
}
