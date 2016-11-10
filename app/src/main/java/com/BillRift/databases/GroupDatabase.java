package com.BillRift.databases;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.BillRift.TokenManager;
import com.BillRift.models.Group;
import com.BillRift.security.CryptManager;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Dweep on 2016-10-15.
 */

public class GroupDatabase {
    private static GroupDatabase instance;

    private final Map<Integer, String> groups;

    // TODO: Remove once we stop mocking data
    private int nextId = 1;

    private GroupDatabase() {
        groups = new HashMap<>();
    }

    public static synchronized GroupDatabase getInstance() {
        if (instance == null) {
            instance = new GroupDatabase();
        }
        return instance;
    }

    @Nullable public Group getGroup(int id) {
        synchronized (groups) {
            return (Group) CryptManager.decryptString(groups.get(id), TokenManager.getToken() /* TODO: Get token here */ );
        }
    }

    public void saveGroup(@NonNull Group group) {
        synchronized (groups) {
            // TODO: Remove once we stop mocking data
            int id = nextId++;
            group.setId(id);
            groups.put(id, CryptManager.encryptObject(group, TokenManager.getToken() /* TODO: Get token here */ ));
        }
    }
}