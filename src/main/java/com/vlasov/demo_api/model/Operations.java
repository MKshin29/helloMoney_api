package com.vlasov.demo_api.model;

public class Operations {

    private String fromUser;
    private String toUser;
    private String currency;
    private double amount;

    public Operations() {
    }

    public Operations(String fromUser, String toUser, String currency, double amount) {
        this.fromUser = fromUser;
        this.toUser = toUser;
        this.currency = currency;
        this.amount = amount;
    }

    public String getFromUser() {return fromUser;}

    public void setFromUser(String fromUser) {this.fromUser = fromUser;}

    public String getToUser() {return toUser;}

    public void setToUser(String toUser) {this.toUser = toUser;}

    public String getCurrency() {return currency;}

    public void setCurrency(String currency) {this.currency = currency;}

    public double getAmount() {return amount;}

    public void setAmount(double amount) {this.amount = amount;}
}
