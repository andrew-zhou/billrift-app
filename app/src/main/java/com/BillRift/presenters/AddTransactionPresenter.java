package com.BillRift.presenters;

import com.BillRift.views.AddTransactionView;
import com.BillRift.UserDatabase;
import com.BillRift.models.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrew on 11/8/2016.
 */

public class AddTransactionPresenter extends BasePresenter<List<User>, AddTransactionView> {
    private int groupId;
    private String selectedName = "";
    private String amount = "";
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
            view().setSelectedName(selectedName);
            view().setAmount(amount);
        }
    }

    public void updateAmount(String amnt) {
        if (!amount.equals(amnt)){
            amount = amnt;
        }
    }

    public void updatePerson(String person) {
        if (!selectedName.equals(person)) {
            selectedName = person;
        }
    }

    public void submit() {
        // Do some stuff here
        view().onSubmit();
    }

    public void scan() {
        view().onScan();
    }
}
