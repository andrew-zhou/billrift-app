package com.BillRift;

import android.util.Log;

import com.BillRift.presenters.LoginPresenter;
import com.BillRift.views.LoginView;
import com.google.android.gms.common.api.GoogleApiClient;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.mockStatic;

@RunWith(PowerMockRunner.class)
@PrepareForTest({Log.class})
public class LoginPresenterTest {
    private LoginPresenter presenter;
    private LoginView view;

    @Before
    public void setUp() {
        presenter = new LoginPresenter();
        view = Mockito.mock(LoginView.class);
        mockStatic(Log.class);
        presenter.bindView(view);
    }

    @Test
    public void testOnLoginButtonClicked_startsGoogleLogin() {
        presenter.onLoginButtonClicked();
        verify(view).startGoogleLogin(any(GoogleApiClient.class));
    }
}
