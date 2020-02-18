package com.vlasov.demo_api.model;

import org.springframework.context.annotation.EnableMBeanExport;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "history")
public class History {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String fromUser;
    private String toUser;
    private String currency;
    private double amount;
    private Date date;

    public History() {
    }

    public History(String fromUser, String toUser, String currency, double amount, Date date){
        this.fromUser = fromUser;
        this.toUser = toUser;
        this.currency = currency;
        this.amount = amount;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFromUser() {
        return fromUser;
    }

    public void setFromUser(String fromUser) {
        this.fromUser = fromUser;
    }

    public String getToUser() {
        return toUser;
    }

    public void setToUser(String toUser) {
        this.toUser = toUser;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
