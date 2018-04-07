package com.example.administrator.androidretrofitapimanagerdemo.api;

import android.app.Activity;
import android.widget.Toast;

import com.example.administrator.androidretrofitapimanagerdemo.interfaces.OnResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by rifatullah on 3/22/18.
 */

public class ApiManager {

    public static <T> void requestToServer(
            final Activity activity, Call<T> call, final OnResponse serverResponse) {
        call.enqueue(new Callback<T>() {

            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                if (response.isSuccessful()) {
                    serverResponse.onSuccess(response);
                } else {
                    serverResponse.onFailure(response);
                }
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                Toast.makeText(activity.getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
