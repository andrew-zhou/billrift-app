package com.BillRift.API;

import com.BillRift.TokenManager;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Server {
    private static final String API_BASE_URL = "https://billrift.herokuapp.com";

    private static OkHttpClient.Builder httpClient = null;

    private static Retrofit.Builder builder =
            new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create());

    public static <S> S createService(Class<S> serviceClass) {
        if (httpClient == null) {
            httpClient = new OkHttpClient.Builder();
            httpClient.addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request request = chain.request();

                    if (TokenManager.getToken() != null) {
                        request = request.newBuilder()
                                .addHeader("auth-token", TokenManager.getToken())
                                .build();
                    }

                    return chain.proceed(request);
                }
            });
        }
        return builder.client(httpClient.build()).build().create(serviceClass);
    }
}
