package com.vlasov.demo_api.model;

import javax.persistence.*;

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

    public int getUserid() {return userid;}

    public void setUserid(int userid) {this.userid = userid;}

    public double getCurr1() {return curr1;}

    public void setCurr1(double curr1) {this.curr1 = curr1;}

    public double getCurr2() {return curr2;}

    public void setCurr2(double curr2) {this.curr2 = curr2;}

    public double getCurr3() {return curr3;}

    public void setCurr3(double curr3) {this.curr3 = curr3;}

    public int getBalance_id() {return balance_id;}

    public void setBalance_id(int id) {this.balance_id = id;}
}
