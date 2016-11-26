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
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.easymock.EasyMock.expect;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;

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
        PowerMockito.mockStatic(Log.class);
        presenter.bindView(view);
    }

    @Test
    public void testUpdateView_noModels_doesNotSetText() {
        verify(view, never()).setText(Mockito.anyString());
    }

    @Test
    public void testUpdateView_withModel_setsText() {
        PowerMockito.mockStatic(UserDatabase.class);
        fakeDB = Mockito.mock(UserDatabase.class);

        reset(view);

        expect(UserDatabase.getInstance()).andReturn(fakeDB);

        presenter.bindView(view);


        expect(fakeDB.getUser("1")).andReturn(new User("Fake", "Fake", "1"));
        expect(fakeDB.getUser("2")).andReturn(new User("Fake", "Fake", "2"));
        expect(fakeModel.getFrom()).andReturn("1");
        expect(fakeModel.getTo()).andReturn("2");
        verify(view).setText(Mockito.anyString());
    }
}
