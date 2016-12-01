/*
    Transaction.java
    Transaction Database Component
    Reference Number: 14
 */

package com.BillRift.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Transaction implements Serializable {
    @SerializedName("userFromId")
    private String from;
    @SerializedName("userToId")
    private String to;
    @SerializedName("amount")
    private Double amount;
    @SerializedName("title")
    private String title;
    @SerializedName("id")
    private Integer id;
    @SerializedName("groupId")
    private Integer groupId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
