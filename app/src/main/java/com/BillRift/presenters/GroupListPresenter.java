package com.BillRift.presenters;

import android.support.annotation.NonNull;

import com.BillRift.API.GroupAPIRoutes;
import com.BillRift.API.GroupsAPIRoutes;
import com.BillRift.API.Server;
import com.BillRift.databases.GroupDatabase;
import com.BillRift.models.Group;
import com.BillRift.views.GroupListView;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
        setModel(GroupDatabase.getInstance().getAllGroups());
    }

    public void onAddGroupClicked() {
        view().showAddGroupDialog();
    }

    public void addGroup(String groupName) {
        view().showProgressBar(true);
        final GroupsAPIRoutes groupsService = Server.createService(GroupsAPIRoutes.class);
        GroupAPIRoutes groupService = Server.createService(GroupAPIRoutes.class);
        Call<ResponseBody> makeGroup = groupService.group(groupName);
        makeGroup.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    Call<List<Group>> newGroups = groupsService.groups();
                    newGroups.enqueue(new Callback<List<Group>>() {
                        @Override
                        public void onResponse(Call<List<Group>> call, Response<List<Group>> response) {
                            if (response.isSuccessful()) {
                                for (Group g : response.body()) {
                                    GroupDatabase.getInstance().saveGroup(g);
                                }
                                view().showProgressBar(false);
                                updateView();
                            } else {
                                view().showProgressBar(false);
                                view().showError();
                            }
                        }

                        @Override
                        public void onFailure(Call<List<Group>> call, Throwable t) {
                            view().showProgressBar(false);
                            view().showError();
                        }
                    });
                } else {
                    view().showProgressBar(false);
                    view().showError();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                view().showProgressBar(false);
                view().showError();
            }
        });
    }
}
