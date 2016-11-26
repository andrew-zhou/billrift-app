package com.BillRift;

import android.util.Log;

import com.BillRift.models.Group;
import com.BillRift.presenters.GroupPresenter;
import com.BillRift.views.GroupView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.mockito.Matchers.anyDouble;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.mockStatic;

@RunWith(PowerMockRunner.class)
@PrepareForTest({Log.class})
public class GroupPresenterTest {
    private GroupPresenter presenter;
    private GroupView view;
    private Group fakeModel;

    @Before
    public void setUp() {
        presenter = new GroupPresenter();
        view = Mockito.mock(GroupView.class);
        mockStatic(Log.class);
        fakeModel = Mockito.mock(Group.class);
        presenter.bindView(view);
        presenter.setModel(fakeModel);
    }

    @Test
    public void testUpdateView_setsGroupName() {
        verify(view).setGroupName(anyString());
    }

    @Test
    public void testUpdateView_setsPersonalBalance() {
        verify(view).setPersonalBalance(anyDouble());
    }
}
