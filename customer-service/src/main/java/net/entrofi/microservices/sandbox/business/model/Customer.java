
package net.entrofi.microservices.sandbox.business.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    private String name;

    private String surname;

    @Column(nullable = false, unique = true)
    private String email;


    public Customer() {

    }


    public Customer(String name, String surname, String email) {
        this.name = name;
        this.surname = surname;
        this.email = email;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String toString() {
        return "Customer { id: " + id + ", name: " + name + ", surname: " + surname + ", email: " + email + "}";
    }
}
