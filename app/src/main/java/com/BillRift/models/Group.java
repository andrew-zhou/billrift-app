package com.BillRift.models;

/**
 * Created by Zal on 2016-10-15.
 */

import java.util.HashSet;
import java.util.Set;

public class Group {
    public int id;
    private String name;
    private Set<String> userIds;

    public Group(String name) {
        this.name = name;
        this.userIds = new HashSet<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<String> getUserIds() {
        return userIds;
    }

    public void addUserId(String userId) {
        userIds.add(userId);
    }

    public void removeUserId(String userId) {
        userIds.remove(userId);
    }
}
