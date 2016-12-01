/*
    GroupListMessageEvent.java
    UI Layer Component
    Reference Number: 1
 */

package com.BillRift;

public class GroupListMessageEvent {
    private int groupId;

    public GroupListMessageEvent(int groupId) {
        this.groupId = groupId;
    }

    public int getGroupId() {
        return groupId;
    }
}
