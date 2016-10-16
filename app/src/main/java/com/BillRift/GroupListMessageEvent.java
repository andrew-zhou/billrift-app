package com.BillRift;

/**
 * Created by Dweep on 2016-10-15.
 */

public class GroupListMessageEvent {
    private int groupId;

    public GroupListMessageEvent(int groupId) {
        this.groupId = groupId;
    }

    public int getGroupId() {
        return groupId;
    }
}
