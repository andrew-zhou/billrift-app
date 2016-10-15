package com.BillRift;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ViewAnimator;

import com.BillRift.models.Group;
import com.BillRift.presenters.GroupListPresenter;
import com.BillRift.views.GroupListView;

import java.util.List;

/**
 * Created by Dweep on 2016-10-15.
 */

public class GroupListFragment extends MvpFragment<GroupListPresenter> implements GroupListView {
    private static final int POSITION_LIST = 0;
    private static final int POSITION_LOADING = 1;
    private static final int POSITION_EMPTY = 2;

    private ViewAnimator animator;
    private GroupAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_grouplist, container, false);

        animator = (ViewAnimator) view.findViewById(R.id.animator);
        RecyclerView recyclerView = (RecyclerView) animator.getChildAt(POSITION_LIST);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        adapter = new GroupAdapter();
        recyclerView.setAdapter(adapter);

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    protected GroupListPresenter createPresenter() {
        return new GroupListPresenter();
    }

    @Override
    public void showGroups(List<Group> groups) {
        adapter.clearAndAddAll(groups);
        animator.setDisplayedChild(POSITION_LIST);
    }

    @Override
    public void showLoading() {
        animator.setDisplayedChild(POSITION_LOADING);
    }

    @Override
    public void showEmpty() {
        animator.setDisplayedChild(POSITION_EMPTY);
    }
}