package com.BillRift;

import android.util.Log;

import com.BillRift.databases.UserDatabase;
import com.BillRift.models.Transaction;
import com.BillRift.presenters.TransactionListPresenter;
import com.BillRift.views.TransactionListView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.ArrayList;

import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

@RunWith(PowerMockRunner.class)
@PrepareForTest({Log.class, UserDatabase.class})
public class TransactionListPresenterTest {
    private TransactionListPresenter presenter;
    private TransactionListView view;

    @Before
    public void setUp() {
        presenter = new TransactionListPresenter(-1);
        view = Mockito.mock(TransactionListView.class);
        presenter.bindView(view);
    }

    @Test
    public void testUpdateView_emptyModel_showsEmpty() {
        presenter.setModel(new ArrayList<Transaction>());
        verify(view, atLeastOnce()).showEmpty();
    }

    @Test
    public void testUpdateView_withTransactions_showsTransactions() {
        Transaction t = new Transaction();
        ArrayList<Transaction> fakeModel = new ArrayList<>();
        fakeModel.add(t);
        presenter.setModel(fakeModel);
        verify(view, atLeastOnce()).showTransactions(fakeModel);
    }

    @Test
    public void testAddTransaction_goesToAddTransaction() {
        presenter.addTransaction();
        verify(view).goToAddTransaction();
    }

    @Test
    public void testShowBalances_goesToShowBalances() {
        presenter.showBalances();
        verify(view).goToShowBalances();
    }

    @Test
    public void testAddGroupMemberButtonClicked_showsDialog() {
        presenter.addGroupMemberButtonClicked();
        verify(view).showAddGroupMemberDialog();
    }
}
