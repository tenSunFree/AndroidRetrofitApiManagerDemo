package com.example.administrator.androidretrofitapimanagerdemo.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by rifatullah on 3/22/18.
 */

public class ApiClient {

    public static final String API_BASE_URL = "https://api.github.com";
    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(API_BASE_URL)                                                         // 想去哪裡取得資料
                    .addConverterFactory(GsonConverterFactory.create())                               // 使用Gson解析
                    .build();
        }
        return retrofit;
    }
}
