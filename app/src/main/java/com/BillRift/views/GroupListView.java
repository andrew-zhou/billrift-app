package com.BillRift.views;

import com.BillRift.models.Group;

import java.util.List;

/**
 * Created by Dweep on 2016-10-15.
 */

public interface GroupListView {
    void showGroups(List<Group> groups);
    void showLoading();
    void showEmpty();
    void showAddGroupDialog();
}
