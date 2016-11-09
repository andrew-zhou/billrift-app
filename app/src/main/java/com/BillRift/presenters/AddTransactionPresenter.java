package com.BillRift.presenters;

import com.BillRift.models.Transaction;
import com.BillRift.views.AddTransactionView;
import com.BillRift.databases.UserDatabase;
import com.BillRift.models.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrew on 11/8/2016.
 */

public class AddTransactionPresenter extends BasePresenter<List<User>, AddTransactionView> {
    private int groupId;
    private String selectedFrom = "";
    private String selectedTo = "";
    private String amount = "";
    private String description = "";
    private boolean isLoaded = false;

    public AddTransactionPresenter(int groupId) {
        super();
        this.groupId = groupId;
        setModel(null);
        // TODO: Make call to load users for group to database from server
        // In the callback:
        // transactionList = returnedValue;
        // isLoaded = true;
        // updateView();

        // For mocked purposes:
        setModel(UserDatabase.getInstance().getUsersForGroup(groupId));
        isLoaded = true;
    }

    @Override
    protected void updateView() {
        if (!isLoaded) {
            view().showLoading();
        } else {
            List<String> names = new ArrayList<>();
            for (User u : model) {
                names.add(u.getDisplayName());
            }
            view().showView(names);
            view().setSelectedFrom(selectedFrom);
            view().setSelectedTo(selectedTo);
            view().setAmount(amount);
            view().setDescription(description);
        }
    }

    public void updateDescription(String amnt) {
        if (!description.equals(amnt)){
            description = amnt;
        }
    }

    public void updateAmount(String amnt) {
        if (!amount.equals(amnt)){
            amount = amnt;
        }
    }

    public void updateFrom(String person) {
        if (!selectedFrom.equals(person)) {
            selectedFrom = person;
        }
    }

    public void updateTo(String person) {
        if (!selectedTo.equals(person)) {
            selectedTo = person;
        }
    }

    public void submit() {
        // Create transaction
        Transaction transaction = new Transaction();

        if (selectedFrom.equals(selectedTo) || description.isEmpty()) {
            view().showError();
            return;
        }

        try {
            double amnt = Double.parseDouble(amount);
            transaction.setAmount(amnt);
            transaction.setFrom(selectedFrom);
            transaction.setTo(selectedTo);
            transaction.setGroupId(groupId);
            transaction.setTitle(description);
        } catch (Exception e) {
            view().showError();
            return;
        }

        // Need to send to server (and make the view loading)

        view().onSubmit();
    }

    public void scan() {
        view().onScan();
    }
}
