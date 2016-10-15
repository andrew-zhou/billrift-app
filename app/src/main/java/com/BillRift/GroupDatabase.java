package com.BillRift;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.BillRift.models.Group;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Dweep on 2016-10-15.
 */

public class GroupDatabase {
    private static GroupDatabase instance;

    private final Map<Integer, Group> groups;

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
            return groups.get(id);
        }
    }

    public void saveGroup(@NonNull Group group) {
        synchronized (groups) {
            int id = nextId++;
            group.setId(id);
            groups.put(id, group);
        }
    }
}