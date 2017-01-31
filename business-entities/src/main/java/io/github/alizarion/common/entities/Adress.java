package io.github.alizarion.common.entities;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAttribute;

/**
 * Created by Lucas CHABALIER on 31/01/2017.
 */
public abstract class Adress {
    @Id
    @Column(name = "id")
    @XmlAttribute
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @XmlAttribute
    @Column(name = "person")
    private Person person;

    public Long getId() {return id;}
    public Person getPerson() { return person;}

    public void setId(long id) { this.id = id;}
    public void setPerson(Person person) { this.person = person;}

}
