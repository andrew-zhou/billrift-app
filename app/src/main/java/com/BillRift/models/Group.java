package com.BillRift.models;

/**
 * Created by Zal on 2016-10-15.
 */

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Group implements Serializable {
    @SerializedName("id")
    public Integer id;
    @SerializedName("name")
    private String name;
    @SerializedName("userIds")
    private List<String> userIds;
    @SerializedName("balance")
    private Double balance = 0.0;

    public Group() {
        this(null, new ArrayList<String>());
    }

    public Group(String name) {
        this(name, new ArrayList<String>());
    }

    public Group(String name, List<String> userIds) {
        this.name = name;
        this.userIds = userIds;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getUserIds() {
        return userIds;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUserIds(List<String> userIds) {
        this.userIds = userIds;
    }

    public void addUserId(String userId) {
        userIds.add(userId);
    }

    public void removeUserId(String userId) {
        userIds.remove(userId);
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }
}
