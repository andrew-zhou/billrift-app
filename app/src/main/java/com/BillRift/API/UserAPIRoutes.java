/*
    UserAPIRoutes.java
    Endpoint Service Factory Component
    Reference Number: 3
 */

package com.BillRift.API;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface UserAPIRoutes {
    @POST("/user/login")
    Call<ResponseBody> sendIdToken();
}
