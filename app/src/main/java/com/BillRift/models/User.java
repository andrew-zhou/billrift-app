package com.BillRift.models;

/**
 * Created by Zal on 2016-10-15.
 */

public class User {
    private String displayName;
    private String email;
    private String id;
    private String idToken;

    public User(String displayName, String email, String id, String idToken) {
        this.displayName = displayName;
        this.email = email;
        this.id = id;
        this.idToken = idToken;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getEmail() {
        return email;
    }

    public String getId() {
        return id;
    }

    public String getIdToken() {
        return idToken;
    }
}
