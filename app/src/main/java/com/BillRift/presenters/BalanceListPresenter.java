package com.BillRift.presenters;

import com.BillRift.API.GroupAPIRoutes;
import com.BillRift.API.Server;
import com.BillRift.databases.BalanceDatabase;
import com.BillRift.models.Balance;
import com.BillRift.views.BalanceListView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BalanceListPresenter extends BasePresenter<List<Balance>, BalanceListView> {
    private boolean isLoaded = false;
    private int groupId;

    public BalanceListPresenter(final int groupId) {
        super();
        this.groupId = groupId;
        setModel(new ArrayList<Balance>());

        Call<List<Balance>> balanceCall = Server.createService(GroupAPIRoutes.class).balances(groupId);
        balanceCall.enqueue(new Callback<List<Balance>>() {
            @Override
            public void onResponse(Call<List<Balance>> call, Response<List<Balance>> response) {
                if (response.isSuccessful()) {
                    isLoaded = true;
                    for (Balance b : response.body()) {
                        BalanceDatabase.getInstance().saveBalance(b);
                    }
                    setModel(response.body());
                } else {
                    isLoaded = true;
                    setModel(BalanceDatabase.getInstance().getBalancesForGroup(groupId));
                }
            }

            @Override
            public void onFailure(Call<List<Balance>> call, Throwable t) {
                isLoaded = true;
                setModel(BalanceDatabase.getInstance().getBalancesForGroup(groupId));
            }
        });
    }

    @Override
    protected void updateView() {
        if (!isLoaded) {
            view().showLoading();
        } else {
            if (model.isEmpty()) {
                view().showEmpty();
            } else {
                view().showBalances(model);
            }
        }
    }
}
