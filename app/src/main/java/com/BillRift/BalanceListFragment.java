package com.BillRift;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import android.widget.ViewAnimator;

import com.BillRift.models.Balance;
import com.BillRift.presenters.BalanceListPresenter;
import com.BillRift.timing.MethodTimer;
import com.BillRift.views.BalanceListView;

import java.util.List;

/**
 * Created by Andrew on 11/9/2016.
 */

public class BalanceListFragment extends MvpFragment<BalanceListPresenter> implements BalanceListView {
    public static final String GROUP_ID_KEY = "GROUP_ID_KEY";

    private static final int POSITION_LIST = 0;
    private static final int POSITION_LOADING = 1;
    private static final int POSITION_EMPTY = 2;

    private ViewAnimator viewAnimator;
    private BalanceAdapter adapter;
    private MethodTimer timer;

    @Override
    protected BalanceListPresenter createPresenter() {
        return new BalanceListPresenter(getArguments().getInt(GROUP_ID_KEY));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_balance_list, container, false);

        timer = new MethodTimer();

        viewAnimator = (ViewAnimator)view.findViewById(R.id.animator);
        RecyclerView recyclerView = (RecyclerView)viewAnimator.getChildAt(POSITION_LIST);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        adapter = new BalanceAdapter();
        recyclerView.setAdapter(adapter);

        return view;
    }

    @Override
    public void showEmpty() {
        if (timer != null) {
            if (!timer.durationWithinLimit(1)) {
                Log.e("TIMING ISSUE", "Took more than 1 second for responsive UI");
            }
            timer = null;
        }
        viewAnimator.setDisplayedChild(POSITION_EMPTY);
    }

    @Override
    public void showLoading() {
        if (timer != null) {
            if (!timer.durationWithinLimit(1)) {
                Log.e("TIMING ISSUE", "Took more than 1 second for responsive UI");
            }
            timer = null;
        }
        viewAnimator.setDisplayedChild(POSITION_LOADING);
    }

    @Override
    public void showBalances(List<Balance> balanceList) {
        if (timer != null) {
            if (!timer.durationWithinLimit(1)) {
                Log.e("TIMING ISSUE", "Took more than 1 second for responsive UI");
            }
            timer = null;
        }
        adapter.clearAndAddAll(balanceList);
        viewAnimator.setDisplayedChild(POSITION_LIST);
    }

    @Override
    public void showError(String msg) {
        if (timer != null) {
            if (!timer.durationWithinLimit(1)) {
                Log.e("TIMING ISSUE", "Took more than 1 second for responsive UI");
            }
            timer = null;
        }
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }
}
