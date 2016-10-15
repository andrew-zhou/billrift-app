package com.BillRift.API;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Server {
    private static final String API_URL = "http://localhost:3000";
    private static APIRoutes apiRoutes;

    public static APIRoutes getAPIRoutes() {
        if (apiRoutes == null) {
            Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
            apiRoutes = retrofit.create(APIRoutes.class);
        }
        return apiRoutes;
    }

    public interface APIRoutes {
        // TODO
    }

}
