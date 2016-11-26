package com.BillRift;

import android.util.Log;

import com.BillRift.databases.UserDatabase;
import com.BillRift.models.Transaction;
import com.BillRift.models.User;
import com.BillRift.presenters.TransactionPresenter;
import com.BillRift.views.TransactionView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest({Log.class, UserDatabase.class})
public class TransactionPresenterTest {
    private TransactionPresenter presenter;
    private TransactionView view;
    private UserDatabase fakeDB;
    private Transaction fakeModel;

    @Before
    public void setUp() {
        fakeModel = Mockito.mock(Transaction.class);
        presenter = new TransactionPresenter(fakeModel);
        view = Mockito.mock(TransactionView.class);
        mockStatic(Log.class);
        presenter.bindView(view);
    }

    @Test
    public void testUpdateView_noModels_doesNotSetText() {
        verify(view, never()).setText(Mockito.anyString());
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
