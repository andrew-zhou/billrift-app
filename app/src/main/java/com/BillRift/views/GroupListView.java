package com.BillRift.views;

import com.BillRift.models.Group;

import java.util.List;

public interface GroupListView {
    void showGroups(List<Group> groups);
    void showLoading();
    void showEmpty();
    void showAddGroupDialog();
    void showProgressBar(boolean show);
    void showError(String msg);
}
