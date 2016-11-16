package com.BillRift.presenters;

import com.BillRift.API.GroupAPIRoutes;
import com.BillRift.API.Server;
import com.BillRift.databases.TransactionDatabase;
import com.BillRift.models.Transaction;
import com.BillRift.views.TransactionListView;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TransactionListPresenter extends BasePresenter<List<Transaction>, TransactionListView> {
    private boolean isLoaded = false;
    private int groupId;

    public TransactionListPresenter(int groupId) {
        super();
        setModel(TransactionDatabase.getInstance().getTransactionsForGroup(groupId));
        this.groupId = groupId;
    }

    @Override
    protected void updateView() {
        if (!isLoaded) {
            view().showLoading();
        } else {
            if (model.isEmpty()) {
                view().showEmpty();
            } else {
                view().showTransactions(model);
            }
        }
    }

    public void update() {
        isLoaded = false;
        updateView();
        Call<List<Transaction>> transactionsCall = Server.createService(GroupAPIRoutes.class).transactions(groupId);
        transactionsCall.enqueue(new Callback<List<Transaction>>() {
            @Override
            public void onResponse(Call<List<Transaction>> call, Response<List<Transaction>> response) {
                if (response.isSuccessful()) {
                    isLoaded = true;
                    for (Transaction t : response.body()) {
                        TransactionDatabase.getInstance().saveTransaction(t);
                    }
                    setModel(response.body());
                } else {
                    isLoaded = true;
                    setModel(TransactionDatabase.getInstance().getTransactionsForGroup(groupId));
                }
            }

            @Override
            public void onFailure(Call<List<Transaction>> call, Throwable t) {
                isLoaded = true;
                setModel(TransactionDatabase.getInstance().getTransactionsForGroup(groupId));
            }
        });
    }

    public void addTransaction() {
        view().goToAddTransaction();
    }

    public void showBalances() {
        view().goToShowBalances();
    }

    public void addGroupMemberButtonClicked() {
        view().showAddGroupMemberDialog();
    }

    public void addGroupMember(String email) {
        Call<ResponseBody> addMemberCall = Server.createService(GroupAPIRoutes.class).user(email);
        addMemberCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {}

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {}
        });
    }
}
