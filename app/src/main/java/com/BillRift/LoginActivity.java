package com.BillRift;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.Toast;

import com.BillRift.models.User;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.api.GoogleApiClient;

public class LoginActivity extends FragmentActivity implements LoginFragment.Listener {
    private static final int RC_SIGN_IN = 9001;

    private ProgressDialog progressDialog;
    private LoginFragment loginFragment;

    interface LoginHandler {
        void onLoginSuccess();
        void onLoginFailed(String message);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginFragment = (LoginFragment) getFragmentManager().findFragmentById(R.id.login_fragment);
    }

    @Override
    public void goToGoogleLogin(GoogleApiClient googleApiClient) {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);

        // Responsiveness: Show progress dialog on login
        showProgressDialog();
    }

    @Override
    public void goToGroupsView() {
        // Responsiveness: Hide progress dialog
        hideProgressDialog();

        // Navigate to group activity
        Intent groupListIntent = new Intent(LoginActivity.this, GroupListActivity.class);
        startActivity(groupListIntent);
    }

    @Override
    public void showGroupErrorMessage(String message) {
        // Responsiveness: Hide progress dialog
        hideProgressDialog();
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);

            if (result.isSuccess()) {
                // Signed in successfully, show authenticated UI.
                GoogleSignInAccount acct = result.getSignInAccount();

                final User curUser = GoogleOAuthHelper.getUserFrom(acct);

                // Send token to server and validate server-side
                GoogleOAuthHelper.sendIdToken(TokenManager.getToken(), new LoginHandler() {
                    @Override
                    public void onLoginSuccess() {
                        loginFragment.getUserGroups(curUser);
                    }

                    @Override
                    public void onLoginFailed(String message) {
                        // Responsiveness: Hide progress dialog and show error
                        hideProgressDialog();
                        Toast.makeText(LoginActivity.this, message, Toast.LENGTH_LONG).show();
                    }
                });

                Log.w("GoogleSignIn", "Login success: " + acct);
            } else {
                Log.w("GoogleSignIn", "Login failure");

                // Responsiveness: Hide progress dialog and show error
                hideProgressDialog();
                Toast.makeText(this, result.getStatus().getStatusMessage(), Toast.LENGTH_LONG).show();
            }
        }
    }

    private void showProgressDialog() {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(this);
            progressDialog.setMessage(getString(R.string.authenticating));
            progressDialog.setIndeterminate(true);
        }

        progressDialog.show();
    }

    private void hideProgressDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.hide();
        }
    }
}