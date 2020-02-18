package com.vlasov.demo_api.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Alien {

    @Id
    @GeneratedValue
    private int aid;
    @Getter
    @Setter
    private String name;
    @Getter
    @Setter
    private String tag;

}
