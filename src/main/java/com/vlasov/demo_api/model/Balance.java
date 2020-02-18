package com.vlasov.demo_api.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "balance")
public class Balance {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int balance_id;

    private int userid;
    private double curr1;
    private double curr2;
    private double curr3;

    public Balance() {
    }

    public Balance(int balance_id, int userid, double curr1, double curr2, double curr3) {
        this.userid = userid;
        this.curr1 = curr1;
        this.curr2 = curr2;
        this.curr3 = curr3;
    }

}
