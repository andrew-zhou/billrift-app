package com.BillRift.databases;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.BillRift.TokenManager;
import com.BillRift.models.Group;
import com.BillRift.models.User;
import com.BillRift.security.CryptManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class UserDatabase {
    private static UserDatabase instance;

    private final Map<String, String> users;

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
            return (User) CryptManager.decryptString(users.get(id), TokenManager.getToken());
        }
    }

    public void saveUser(@NonNull User user) {
        synchronized (users) {
            users.put(user.getId(), CryptManager.encryptObject(user, TokenManager.getToken()));
        }
    }

    public List<User> getUsersForGroup(@NonNull Integer groupId) {
        synchronized(users) {
            Group g = GroupDatabase.getInstance().getGroup(groupId);
            if (g == null) {
                return new ArrayList<>();
            }
            List<String> ids = g.getUserIds();
            ArrayList<User> u = new ArrayList<>();
            for(String id : ids) {
                u.add(getUser(id));
            }
            return u;
        }
    }
}