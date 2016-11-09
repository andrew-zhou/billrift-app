package com.BillRift.presenters;

import android.support.annotation.NonNull;

import com.BillRift.GroupDatabase;
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
        // TODO: Change to add appropriate group
        Group group = new Group("Hello");

        // Update view immediately
        model.add(group);
        GroupDatabase.getInstance().saveGroup(group);
        updateView();
    }

    // It's OK for this class not to be static and to keep a reference to the Presenter, as this
    // is retained during orientation changes and is lightweight (has no activity/view reference)
//    private class LoadDataTask extends AsyncTask<Void, Void, Void> {
//        @Override
//        protected Void doInBackground(Void... params) {
//            SystemClock.sleep(3000);
//            return null;
//        }
//
//        @Override
//        protected void onPostExecute(Void aVoid) {
//            setModel(CounterDatabase.getInstance().getAllCounters());
//            isLoadingData = false;
//        }
//    }
}
