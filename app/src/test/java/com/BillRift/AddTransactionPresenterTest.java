package com.BillRift;

import android.util.Log;

import com.BillRift.API.GroupAPIRoutes;
import com.BillRift.API.Server;
import com.BillRift.models.User;
import com.BillRift.presenters.AddTransactionPresenter;
import com.BillRift.views.AddTransactionView;

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
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.when;

/**
 * Created by Dweep on 2016-11-26.
 */

@RunWith(PowerMockRunner.class)
@PrepareForTest({Log.class, Server.class, })
public class AddTransactionPresenterTest {
    private AddTransactionPresenter presenter;
    private AddTransactionView view;

    @Before
    public void setUp() {
        mockStatic(Server.class);
        GroupAPIRoutes fakeAPIRoutes = Mockito.mock(GroupAPIRoutes.class);
        Call<List<User>> fakeCall = (Call<List<User>>) Mockito.mock(Call.class);
        Mockito.doNothing().when(fakeCall).enqueue(Matchers.<Callback<List<User>>>any());
        Mockito.when(fakeAPIRoutes.users(1)).thenReturn(fakeCall);
        when(Server.createService(GroupAPIRoutes.class)).thenReturn(fakeAPIRoutes);

        presenter = new AddTransactionPresenter(1);
        view = Mockito.mock(AddTransactionView.class);
        mockStatic(Log.class);
        presenter.bindView(view);
    }

    @Test
    public void testUpdateView_emptyModel_showsLoading() {
        verify(view).showLoading();
        verify(view, never()).showView(anyList());
    }

    @Test
    public void testUpdateView_withModel_setsView() {
        List<User> users = new ArrayList<>();
        users.add(new User("Fake", "Fake", "1"));
        users.add(new User("Fake", "Fake", "2"));

        presenter.setLoaded(true);
        presenter.setModel(users);

        verify(view).showView(anyList());
        verify(view).setSelectedFrom(anyString());
        verify(view).setSelectedTo(anyString());
        verify(view).setAmount(anyString());
        verify(view).setDescription(anyString());
    }

    @Test
    public void testScan() {
        presenter.scan();

        verify(view).onScan();
    }
}
