package com.vlasov.demo_api.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "usr")
public class User extends ModelParent{

    private String username;
    private String password;
    private String firstname;
    private String lastname;

    public User() {
    }

    public User(String username, String password, String firstname, String lastname) {
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
    }
}
