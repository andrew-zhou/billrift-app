package com.BillRift;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.BillRift.models.Group;
import com.BillRift.presenters.GroupPresenter;
import com.BillRift.views.GroupViewHolder;

/**
 * Created by Dweep on 2016-10-15.
 */

public class GroupAdapter extends MvpRecyclerListAdapter<Group, GroupPresenter, GroupViewHolder> {
    @Override
    public GroupViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new GroupViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout., parent, false));
    }

    @NonNull
    @Override
    protected GroupPresenter createPresenter(@NonNull Group counter) {
        GroupPresenter presenter = new GroupPresenter();
        presenter.setModel(counter);
        return presenter;
    }

    @NonNull
    @Override
    protected Object getModelId(@NonNull Group model) {
        return model.getId();
    }
}
