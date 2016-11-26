package com.BillRift;

import android.util.Log;

import com.BillRift.databases.UserDatabase;
import com.BillRift.models.Balance;
import com.BillRift.models.User;
import com.BillRift.presenters.BalancePresenter;
import com.BillRift.views.BalanceView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.when;

/**
 * Created by Dweep on 2016-11-26.
 */

@RunWith(PowerMockRunner.class)
@PrepareForTest({Log.class, UserDatabase.class})
public class BalancePresenterTest {
    private BalancePresenter presenter;
    private BalanceView view;
    private UserDatabase fakeDB;
    private Balance fakeModel;

    @Before
    public void setUp() {
        fakeModel = Mockito.mock(Balance.class);
        presenter = new BalancePresenter(fakeModel);
        view = Mockito.mock(BalanceView.class);
        mockStatic(Log.class);
        presenter.bindView(view);
    }

    @Test
    public void testUpdateView_noModels_doesNotSetTest() {
        verify(view, never()).setText(anyString());
    }

    @Test
    public void testUpdateView_withModel_setsText() {
        mockStatic(UserDatabase.class);

        fakeDB = Mockito.mock(UserDatabase.class);
        Mockito.when(fakeDB.getUser("1")).thenReturn(new User("Fake", "Fake", "1"));
        Mockito.when(fakeDB.getUser("2")).thenReturn(new User("Fake", "Fake", "2"));
        Mockito.when(fakeModel.getFrom()).thenReturn("1");
        Mockito.when(fakeModel.getTo()).thenReturn("2");
        when(UserDatabase.getInstance()).thenReturn(fakeDB);

        reset(view);
        presenter.bindView(view);

        verify(view).setText(Mockito.anyString());
    }
}
