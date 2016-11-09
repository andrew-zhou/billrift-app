package com.BillRift;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.BillRift.models.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

    public List<User> getUsersForGroup(@NonNull Integer groupId) {
        List<User> mockUsers = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            User u = new User("User " + Integer.toString(i), "", Integer.toString(i), "");
            mockUsers.add(u);
        }
        return mockUsers;

        // TODO: Uncomment this when no longer mocking data
//        synchronized(groupId) {
//            Set<String> ids = GroupDatabase.getInstance().getGroup(groupId).getUserIds();
//            ArrayList<User> u = new ArrayList<>();
//            for(String id : ids) {
//                u.add(users.get(id));
//            }
//            return u;
//        }
    }
}