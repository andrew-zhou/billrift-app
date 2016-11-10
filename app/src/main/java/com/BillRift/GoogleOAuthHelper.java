package com.BillRift;

import android.util.Log;
import android.widget.Toast;

import com.BillRift.API.UserAPIRoutes;
import com.BillRift.API.Server;
import com.BillRift.databases.UserDatabase;
import com.BillRift.models.User;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Dweep on 2016-11-09.
 */

public final class GoogleOAuthHelper {
    private GoogleOAuthHelper() { }

    public static User getUserFrom(GoogleSignInAccount account) {
        String displayName = account.getDisplayName();
        String email = account.getEmail();
        String id = account.getId();
        String idToken = account.getIdToken();

        User currentUser = new User(displayName, email, id, idToken);
        UserDatabase.getInstance().saveUser(currentUser);

        return currentUser;
    }

    public static void sendIdToken(String idToken, final LoginActivity.LoginHandler handler) {
        UserAPIRoutes loginService = Server.createService(UserAPIRoutes.class);
        Call<ResponseBody> call = loginService.sendIdToken(idToken);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.d("SendToken", "Token sent succesfully");

                if (response.isSuccessful()) {
                    handler.onLoginSuccess();
                } else {
                    handler.onLoginFailed(response.code() + ": " + response.message());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d("SendToken", "Token send failed");

                // Handle failure (no internet connection)
                handler.onLoginFailed(t.getMessage());
            }
        });
    }
}
