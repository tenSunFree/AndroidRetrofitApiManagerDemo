package com.example.administrator.androidretrofitapimanagerdemo.interfaces;


import com.example.administrator.androidretrofitapimanagerdemo.model.Contributor;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by rifatullah on 3/22/18.
 */

public interface ApiService {

    /**
     * 會返回一個call類別
     * 對應到的網址: https://api.github.com/repos/bumptech/glide/contributors
     */
    @GET("/repos/{owner}/{repo}/contributors")
    Call<List<Contributor>> contributors(
            @Path("owner") String owner,
            @Path("repo") String repo);
}
