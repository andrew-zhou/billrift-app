package com.BillRift.databases;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.BillRift.TokenManager;
import com.BillRift.models.Group;
import com.BillRift.security.CryptManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Dweep on 2016-10-15.
 */

public class GroupDatabase {
    private static GroupDatabase instance;

    private final Map<Integer, String> groups;

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
            return (Group) CryptManager.decryptString(groups.get(id), TokenManager.getToken());
        }
    }

    public void saveGroup(@NonNull Group group) {
        synchronized (groups) {
            groups.put(group.getId(), CryptManager.encryptObject(group, TokenManager.getToken()));
        }
    }

    public List<Group> getAllGroups() {
        synchronized (groups) {
            List<Group> g = new ArrayList<>();
            for (String groupStr : groups.values()) {
                g.add((Group) CryptManager.decryptString(groupStr, TokenManager.getToken()));
            }
            return g;
        }
    }
}