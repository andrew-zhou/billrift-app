package com.BillRift.views;

import com.BillRift.models.User;

import java.util.List;

/**
 * Created by Andrew on 11/8/2016.
 */

public interface AddTransactionView {
    void showLoading();
    void showView(List<String> names);
    void setAmount(String amount);
    void setSelectedName(String selectedName);
    void onSubmit();
    void onScan();
}
