package com.BillRift.API;

import com.BillRift.models.Balance;
import com.BillRift.models.Transaction;
import com.BillRift.models.User;

import java.util.List;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface GroupAPIRoutes {
    @GET("/group/{id}/balances")
    Call<List<Balance>> balances(@Path("id") Integer groupId);

    @GET("/group/{id}/transactions")
    Call<List<Transaction>> transactions(@Path("id") Integer groupId);

    @GET("/group/{id}/users")
    Call<List<User>> users(@Path("id") Integer groupId);

    @POST("/group/")
    @FormUrlEncoded
    Call<ResponseBody> group(@Field("name") String name);

    @POST("/group/{id}/user")
    @FormUrlEncoded
    Call<ResponseBody> user(@Path("id") Integer groupId, @Field("email") String email);

    @POST("/group/{id}/transaction")
    @FormUrlEncoded
    Call<ResponseBody> transaction(@Path("id") Integer groupId, @FieldMap(encoded=true) Map<String, String> transaction);
}
