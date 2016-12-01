/*
    GroupsAPIRoutes.java
    Endpoint Service Factory Component
    Reference Number: 3
 */

package com.BillRift.API;

import com.BillRift.models.Group;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GroupsAPIRoutes {
    @GET("/groups")
    Call<List<Group>> groups();
}
