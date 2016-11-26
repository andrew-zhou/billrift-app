package com.BillRift;

import android.util.Log;

import com.BillRift.API.GroupAPIRoutes;
import com.BillRift.API.Server;
import com.BillRift.models.Balance;
import com.BillRift.models.User;
import com.BillRift.presenters.BalanceListPresenter;
import com.BillRift.views.BalanceListView;

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
public class BalanceListPresenterTest {
    private BalanceListPresenter presenter;
    private BalanceListView view;

    @Before
    public void setUp() {
        mockStatic(Server.class);
        GroupAPIRoutes fakeAPIRoutes = Mockito.mock(GroupAPIRoutes.class);
        Call<List<Balance>> fakeCall = (Call<List<Balance>>) Mockito.mock(Call.class);
        Mockito.doNothing().when(fakeCall).enqueue(Matchers.<Callback<List<Balance>>>any());
        Mockito.when(fakeAPIRoutes.balances(1)).thenReturn(fakeCall);
        when(Server.createService(GroupAPIRoutes.class)).thenReturn(fakeAPIRoutes);

        presenter = new BalanceListPresenter(1);
        view = Mockito.mock(BalanceListView.class);
        mockStatic(Log.class);
        presenter.bindView(view);
    }

    @Test
    public void testUpdateView_notLoadedEmptyModel_showLoading() {
        verify(view).showLoading();
        verify(view, never()).showBalances(anyList());
    }

    @Test
    public void testUpdateView_loadedEmptyModel_showsEmpty() {
        presenter.setLoaded(true);
        presenter.setModel(new ArrayList<Balance>());

        verify(view).showEmpty();
        verify(view, never()).showBalances(anyList());
    }

    @Test
    public void testUpdateView_withBalances_showsBalances() {
        Balance b = new Balance();
        List<Balance> fakeModel = new ArrayList<>();
        fakeModel.add(b);

        presenter.setLoaded(true);
        presenter.setModel(fakeModel);

        verify(view).showBalances(fakeModel);
    }
}
