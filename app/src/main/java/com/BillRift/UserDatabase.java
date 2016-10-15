package com.BillRift;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.BillRift.models.User;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Dweep on 2016-10-15.
 */

public class UserDatabase {
    private static UserDatabase instance;

    private final Map<String, User> users;

    private UserDatabase() {
        users = new HashMap<>();
    }

    public static synchronized UserDatabase getInstance() {
        if (instance == null) {
            instance = new UserDatabase();
        }
        return instance;
    }

    @Nullable public User getUser(String id) {
        synchronized (users) {
            return users.get(id);
        }
    }

    public void saveUser(@NonNull User user) {
        synchronized (users) {
            users.put(user.getId(), user);
        }
    }
}