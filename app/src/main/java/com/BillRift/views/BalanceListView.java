package com.BillRift.views;

import com.BillRift.models.Balance;

import java.util.List;

/**
 * Created by Andrew on 11/9/2016.
 */

public interface BalanceListView {
    void showEmpty();
    void showLoading();
    void showBalances(List<Balance> balanceList);
}
