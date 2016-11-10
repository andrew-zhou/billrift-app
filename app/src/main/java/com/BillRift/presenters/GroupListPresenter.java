package com.BillRift.presenters;

import android.support.annotation.NonNull;

import com.BillRift.databases.GroupDatabase;
import com.BillRift.models.Group;
import com.BillRift.views.GroupListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dweep on 2016-10-15.
 */

public class GroupListPresenter extends BasePresenter<List<Group>, GroupListView> {
    private boolean isLoadingData = false;

    @Override
    protected void updateView() {
        // Business logic is in the presenter
        if (model.size() == 0) {
            view().showEmpty();
        } else {
            view().showGroups(model);
        }
    }

    @Override
    public void bindView(@NonNull GroupListView view) {
        super.bindView(view);

        // Let's not reload data if it's already here
        if (model == null && !isLoadingData) {
            view().showLoading();
            loadData();
        }
    }

    private void loadData() {
        isLoadingData = true;
//        new LoadDataTask().execute();
//        setModel(GroupDatabase.getInstance())
        List<Group> mockData = new ArrayList<>();
        // TODO: load actual data from db
        Group vacation = new Group("Vacation");
        vacation.setBalance(10.00);
        Group house = new Group("House Expenses");
        house.setBalance(0.0);
        Group architecture = new Group("Architecture Party");
        architecture.setBalance(-10.00);
        GroupDatabase.getInstance().saveGroup(vacation);
        GroupDatabase.getInstance().saveGroup(house);
        GroupDatabase.getInstance().saveGroup(architecture);
        mockData.add(vacation);
        mockData.add(house);
        mockData.add(architecture);

        setModel(mockData);
    }

    public void onAddGroupClicked() {
        view().showAddGroupDialog();
    }

    public void addGroup(String groupName) {
        // TODO: Make network call to create new group
        // After creating new group, make a call to get groups from server if success
        // After success to get groups from server refresh the database
        // Then update view
    }
}
