package com.BillRift.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Zal on 2016-10-15.
 */

public class User implements Serializable {
    @SerializedName("name")
    private String displayName;
    @SerializedName("email")
    private String email;
    @SerializedName("googleId")
    private String id;

    public User() {
        this(null, null, null);
    }

    public User(String displayName, String email, String id) {
        this.displayName = displayName;
        this.email = email;
        this.id = id;
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

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
