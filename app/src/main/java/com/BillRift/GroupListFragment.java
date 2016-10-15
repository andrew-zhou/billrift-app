package com.BillRift;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ViewAnimator;

import com.BillRift.presenters.GroupListPresenter;
import com.BillRift.views.GroupListView;

/**
 * Created by Dweep on 2016-10-15.
 */

public class GroupListFragment extends MvpFragment<GroupListPresenter> implements GroupListView {
    private ViewAnimator animator;
    private GroupAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_grouplist, container, false);

        animator = (ViewAnimator) view.findViewById(R.id.animator);

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
}
