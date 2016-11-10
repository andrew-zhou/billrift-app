package com.BillRift.API;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Andrew on 11/9/2016.
 */

public interface UserAPIRoutes {
    @POST("/user/login")
    Call<ResponseBody> sendIdToken(@Query("idToken") String idToken);
}
