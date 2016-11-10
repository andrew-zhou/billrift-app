package com.BillRift.API;

import com.BillRift.models.Balance;
import com.BillRift.models.Transaction;
import com.BillRift.models.User;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by Andrew on 11/9/2016.
 */

public interface GroupAPIRoutes {
    @GET("/group/{id}/balances")
    Call<List<Balance>> balances(@Path("id") String groupId);

    @GET("/group/{id}/transactions")
    Call<List<Transaction>> transactions(@Path("id") String groupId);

    @GET("/group/{id}/users")
    Call<List<User>> users(@Path("id") String groupId);

    @POST("/group")
    Call<ResponseBody> group(@Body String name);

    @POST("/group/{id}/user")
    Call<ResponseBody> user(@Body String email);

    @POST("/group/{id}/transaction")
    Call<ResponseBody> transaction(@Body Transaction transaction);
}
