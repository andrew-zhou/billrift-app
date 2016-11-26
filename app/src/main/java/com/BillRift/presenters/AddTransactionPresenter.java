package com.BillRift.presenters;

import com.BillRift.API.GroupAPIRoutes;
import com.BillRift.API.Server;
import com.BillRift.models.Transaction;
import com.BillRift.views.AddTransactionView;
import com.BillRift.databases.UserDatabase;
import com.BillRift.models.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddTransactionPresenter extends BasePresenter<List<User>, AddTransactionView> {
    private int groupId;
    private String selectedFrom = "";
    private String selectedTo = "";
    private String amount = "";
    private String description = "";
    private boolean isLoaded = false;

    public AddTransactionPresenter(final int groupId) {
        super();
        this.groupId = groupId;
        setModel(new ArrayList<User>());
        Call<List<User>> usersForGroupCall = Server.createService(GroupAPIRoutes.class).users(groupId);
        usersForGroupCall.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if (response.isSuccessful()) {
                    isLoaded = true;
                    setModel(response.body());
                } else {
                    view().showError(response.message());
                    isLoaded = true;
                    setModel(UserDatabase.getInstance().getUsersForGroup(groupId));
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                view().showError(t.getMessage());
                isLoaded = true;
                setModel(UserDatabase.getInstance().getUsersForGroup(groupId));
            }
        });
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
        Map<String, String> transactionObj = new HashMap<>();

        if (selectedFrom.equals(selectedTo) || description.isEmpty()) {
            view().showError("Invalid Input");
            return;
        }

        // For now: just figure out which user they picked based from name
        String fromId = null, toId = null;
        for (User u : model) {
            if (u.getDisplayName().equals(selectedFrom)) {
                fromId = u.getId();
            }
            if (u.getDisplayName().equals(selectedTo)) {
                toId = u.getId();
            }
        }

        try {
            Double.parseDouble(amount); // For type-checking purposes
            transactionObj.put("amount", amount);
            transactionObj.put("userFromId", fromId);
            transactionObj.put("userToId", toId);
            transactionObj.put("title", description);
        } catch (Exception e) {
            view().showError(e.getMessage());
            return;
        }

        // Need to send to server (and make the view loading)
        Call<ResponseBody> transactionCall = Server.createService(GroupAPIRoutes.class).transaction(groupId, transactionObj);
        transactionCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    view().onSubmit();
                } else {
                    view().showError(response.message());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                view().showError(t.getMessage());
            }
        });
    }

    public void scan() {
        view().onScan();
    }

    public void setLoaded(boolean isLoaded) {
        this.isLoaded = isLoaded;
    }
}
