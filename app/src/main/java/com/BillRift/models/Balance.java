/*
    Balance.java
    Balance Database Component
    Reference Number: 15
 */

package com.BillRift.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Balance implements Serializable {
    @SerializedName("from")
    private String from;
    @SerializedName("to")
    private String to;
    @SerializedName("amount")
    private Double amount;
    @SerializedName("groupId")
    private Integer group;

    public Balance() {}

    public Balance(String from, String to, Double amount, Integer group) {
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

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Integer getGroup() {
        return group;
    }

    public void setGroup(Integer group) {
        this.group = group;
    }

    public String getKey() {
        return getKey(from, to);
    }

    public static String getKey(String from, String to) {
        return from + "#" + to;
    }
}
