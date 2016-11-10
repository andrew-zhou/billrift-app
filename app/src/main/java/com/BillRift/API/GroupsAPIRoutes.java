package com.BillRift.API;

import com.BillRift.models.Group;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Andrew on 11/9/2016.
 */

public interface GroupsAPIRoutes {
    @GET("/groups")
    Call<List<Group>> groups();
}
