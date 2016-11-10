package com.BillRift.presenters;

import android.util.Log;

import com.BillRift.API.GroupsAPIRoutes;
import com.BillRift.API.Server;
import com.BillRift.databases.GroupDatabase;
import com.BillRift.models.Group;
import com.BillRift.models.User;
import com.BillRift.views.LoginView;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginPresenter extends BasePresenter<Object, LoginView> {
    private GoogleApiClient googleApiClient;

    @Override
    protected void updateView() { }

    public void onLoginButtonClicked() {
        view().startGoogleLogin(googleApiClient);
    }

    public void setGoogleApiClient(GoogleApiClient googleApiClient) {
        this.googleApiClient = googleApiClient;
    }

    public void getUserGroups(User curUser) {
        GroupsAPIRoutes groupService = Server.createService(GroupsAPIRoutes.class);
        Call<List<Group>> call = groupService.groups();
        call.enqueue(new Callback<List<Group>>() {
            @Override
            public void onResponse(Call<List<Group>> call, Response<List<Group>> response) {
                Log.d("GetUserGroups", "Groups retrieved successfully");

                if (response.isSuccessful()) {
                    List<Group> groups = response.body();

                    for (Group group : groups) {
                        GroupDatabase.getInstance().saveGroup(group);
                    }

                    view().goToGroupsView();
                } else {
                    view().showGroupErrorMessage(response.code() + ": " + response.message());
                }
            }

            @Override
            public void onFailure(Call<List<Group>> call, Throwable t) {
                Log.d("GetUserGroups", "Groups not retrieved for user");

                // Handle failure (no internet connection)
                view().showGroupErrorMessage(t.getMessage());
            }
        });
    }
}
