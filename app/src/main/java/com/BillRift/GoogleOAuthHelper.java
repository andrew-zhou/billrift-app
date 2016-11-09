package com.BillRift;

import com.BillRift.models.User;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

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

}
