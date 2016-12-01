/*
    GroupListFragment.java
    UI Layer Component
    Reference Number: 1
 */

package com.BillRift;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ViewAnimator;

import com.BillRift.models.Group;
import com.BillRift.presenters.GroupListPresenter;
import com.BillRift.timing.MethodTimer;
import com.BillRift.views.GroupListView;

import java.util.List;

public class GroupListFragment extends MvpFragment<GroupListPresenter> implements GroupListView {
    private static final int POSITION_LIST = 0;
    private static final int POSITION_LOADING = 1;
    private static final int POSITION_EMPTY = 2;

    private ViewAnimator animator;
    private GroupAdapter adapter;
    private Button addGroupButton;
    private ProgressDialog progressDialog;
    private MethodTimer timer;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_grouplist, container, false);

        timer = new MethodTimer();

        addGroupButton = (Button) view.findViewById(R.id.btn_add_group);
        addGroupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timer = new MethodTimer();
                presenter.onAddGroupClicked();
            }
        });

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
        if (timer != null) {
            if (!timer.durationWithinLimit(1)) {
                Log.e("TIMING ISSUE", "Took more than 1 second for responsive UI");
            }
            timer = null;
        }
        adapter.clearAndAddAll(groups);
        animator.setDisplayedChild(POSITION_LIST);
    }

    @Override
    public void showLoading() {
        if (timer != null) {
            if (!timer.durationWithinLimit(1)) {
                Log.e("TIMING ISSUE", "Took more than 1 second for responsive UI");
            }
            timer = null;
        }
        animator.setDisplayedChild(POSITION_LOADING);
    }

    @Override
    public void showEmpty() {
        if (timer != null) {
            if (!timer.durationWithinLimit(1)) {
                Log.e("TIMING ISSUE", "Took more than 1 second for responsive UI");
            }
            timer = null;
        }
        animator.setDisplayedChild(POSITION_EMPTY);
    }

    @Override
    public void showAddGroupDialog() {
        if (timer != null) {
            if (!timer.durationWithinLimit(1)) {
                Log.e("TIMING ISSUE", "Took more than 1 second for responsive UI");
            }
            timer = null;
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Enter New Group Name");

        final EditText input = new EditText(getActivity());
        builder.setView(input);

        builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                presenter.addGroup(input.getText().toString());
                dialogInterface.dismiss();
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });

        builder.show();
    }

    @Override
    public void showProgressBar(boolean show) {
        if (timer != null) {
            if (!timer.durationWithinLimit(1)) {
                Log.e("TIMING ISSUE", "Took more than 1 second for responsive UI");
            }
            timer = null;
        }
        if (show) {
            showProgressDialog();
        } else {
            hideProgressDialog();
        }
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

    private void showProgressDialog() {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(getActivity());
            progressDialog.setMessage(getString(R.string.loading));
            progressDialog.setIndeterminate(true);
        }

        progressDialog.show();
    }

    private void hideProgressDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.hide();
        }
    }
}
