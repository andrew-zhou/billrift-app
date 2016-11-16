package com.BillRift.views;

import com.BillRift.models.Balance;

import java.util.List;

public interface BalanceListView {
    void showEmpty();
    void showLoading();
    void showBalances(List<Balance> balanceList);
    void showError(String msg);
}
