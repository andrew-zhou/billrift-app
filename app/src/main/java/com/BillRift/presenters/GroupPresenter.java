/*
    GroupPresenter.java
    Business Logic Layer Component
    Reference Number: 8
 */

package com.BillRift.presenters;

import com.BillRift.API.GroupAPIRoutes;
import com.BillRift.API.Server;
import com.BillRift.GroupListMessageEvent;
import com.BillRift.databases.TransactionDatabase;
import com.BillRift.databases.UserDatabase;
import com.BillRift.models.Group;
import com.BillRift.models.Transaction;
import com.BillRift.models.User;
import com.BillRift.views.GroupView;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GroupPresenter extends BasePresenter<Group, GroupView> {
    @Override
    protected void updateView() {
        view().setGroupName(model.getName());
        view().setPersonalBalance(model.getBalance());
    }

    public void onGroupClicked() {
        view().showProgressBar(true);
        final GroupAPIRoutes groupService = Server.createService(GroupAPIRoutes.class);
        Call<List<User>> getUsers = groupService.users(model.getId());
        getUsers.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if (response.isSuccessful()) {
                    for (User u : response.body()) {
                        UserDatabase.getInstance().saveUser(u);
                    }
                    Call<List<Transaction>> getTransactions= groupService.transactions(model.getId());
                    getTransactions.enqueue(new Callback<List<Transaction>>() {
                        @Override
                        public void onResponse(Call<List<Transaction>> call, Response<List<Transaction>> response) {
                            if (response.isSuccessful()) {
                                for (Transaction t : response.body()) {
                                    TransactionDatabase.getInstance().saveTransaction(t);
                                }
                                view().showProgressBar(false);
                                EventBus.getDefault().post(new GroupListMessageEvent(model.getId()));
                            } else {
                                view().showProgressBar(false);
                                view().showError(response.message());
                            }
                        }

                        @Override
                        public void onFailure(Call<List<Transaction>> call, Throwable t) {
                            view().showProgressBar(false);
                            view().showError(t.getMessage());
                        }
                    });
                } else {
                    view().showProgressBar(false);
                    view().showError(response.message());
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                view().showProgressBar(false);
                view().showError(t.getMessage());
            }
        });
    }
}