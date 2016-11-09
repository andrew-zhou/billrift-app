package com.BillRift;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ViewAnimator;

import com.BillRift.models.Transaction;
import com.BillRift.presenters.TransactionListPresenter;
import com.BillRift.views.TransactionListView;

import java.util.List;

public class TransactionListFragment extends MvpFragment<TransactionListPresenter> implements TransactionListView {
    public static final String GROUP_ID_KEY = "GROUP_ID_KEY";

    private static final int POSITION_LIST = 0;
    private static final int POSITION_LOADING = 1;
    private static final int POSITION_EMPTY = 2;

    @Nullable private Listener listener;
    private Button showBalances;
    private Button addTransaction;
    private ViewAnimator viewAnimator;
    private TransactionAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_transaction_list, container, false);

        showBalances = (Button)view.findViewById(R.id.btn_balances);
        showBalances.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.showBalances();
            }
        });
        addTransaction = (Button)view.findViewById(R.id.btn_transactions);
        addTransaction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.addTransaction();
            }
        });

        viewAnimator = (ViewAnimator)view.findViewById(R.id.animator);
        RecyclerView recyclerView = (RecyclerView)viewAnimator.getChildAt(POSITION_LIST);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        adapter = new TransactionAdapter();
        recyclerView.setAdapter(adapter);

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listener = (Listener) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    @Override
    protected TransactionListPresenter createPresenter() {
        return new TransactionListPresenter(getArguments().getInt(GROUP_ID_KEY));
    }

    @Override
    public void showEmpty() {
        viewAnimator.setDisplayedChild(POSITION_EMPTY);
    }

    @Override
    public void showLoading() {
        viewAnimator.setDisplayedChild(POSITION_LOADING);
    }

    @Override
    public void showTransactions(List<Transaction> transactionList) {
        adapter.clearAndAddAll(transactionList);
        viewAnimator.setDisplayedChild(POSITION_LIST);
    }

    @Override
    public void goToAddTransaction() {
        // TODO
        if (listener != null) {
            listener.goToAddTransactionActivity();
        }
    }

    @Override
    public void goToShowBalances() {
        // TODO
        if (listener != null) {
            listener.goToShowBalancesActivity();
        }
    }

    public interface Listener {
        void goToAddTransactionActivity();
        void goToShowBalancesActivity();
    }
}