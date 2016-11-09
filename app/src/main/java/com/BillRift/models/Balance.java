package com.BillRift.models;

import java.io.Serializable;

/**
 * Created by Andrew on 11/9/2016.
 */

public class Balance implements Serializable {
    private String from;
    private String to;
    private double amount;
    private int group;

    public Balance(String from, String to, double amount, int group) {
        this.from = from;
        this.to = to;
        this.amount = amount;
        this.group = group;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getGroup() {
        return group;
    }

    public void setGroup(int group) {
        this.group = group;
    }

    public String getKey() {
        return getKey(from, to);
    }

    public static String getKey(String from, String to) {
        return from + "#" + to;
    }
}
