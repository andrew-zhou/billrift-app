package com.BillRift;

import android.util.Log;

import com.BillRift.API.GroupAPIRoutes;
import com.BillRift.API.GroupsAPIRoutes;
import com.BillRift.API.Server;
import com.BillRift.databases.UserDatabase;
import com.BillRift.models.Group;
import com.BillRift.models.User;
import com.BillRift.presenters.GroupListPresenter;
import com.BillRift.views.GroupListView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

import static org.mockito.Matchers.anyList;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.when;

/**
 * Created by Dweep on 2016-11-26.
 */

@RunWith(PowerMockRunner.class)
@PrepareForTest({Log.class, Server.class})
public class GroupListPresenterTest {
    private GroupListPresenter presenter;
    private GroupListView view;

    @Before
    public void setUp() {
        mockStatic(Server.class);
        GroupsAPIRoutes fakeAPIRoutes = Mockito.mock(GroupsAPIRoutes.class);
        Call<List<Group>> fakeCall = (Call<List<Group>>) Mockito.mock(Call.class);
        Mockito.doNothing().when(fakeCall).enqueue(Matchers.<Callback<List<Group>>>any());
        Mockito.when(fakeAPIRoutes.groups()).thenReturn(fakeCall);
        when(Server.createService(GroupsAPIRoutes.class)).thenReturn(fakeAPIRoutes);

        presenter = new GroupListPresenter();
        view = Mockito.mock(GroupListView.class);
        presenter.bindView(view);
    }

    @Test
    public void testUpdateView_loadingEmptyModel_showsLoading() {
        verify(view).showLoading();
        verify(view, never()).showEmpty();
        verify(view, never()).showGroups(anyList());
    }

    @Test
    public void testUpdateView_emptyModel_showsEmpty() {
        presenter.setModel(new ArrayList<Group>());
        verify(view).showEmpty();
        verify(view, never()).showGroups(anyList());
    }

    @Test
    public void testUpdateView_withModel_setsView() {
        List<Group> fakeModel = new ArrayList<>();
        fakeModel.add(new Group("1"));
        fakeModel.add(new Group("2"));

        presenter.setModel(fakeModel);

        verify(view).showGroups(fakeModel);
    }
}
