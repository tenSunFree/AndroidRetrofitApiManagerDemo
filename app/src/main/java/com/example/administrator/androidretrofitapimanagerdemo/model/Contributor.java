package com.example.administrator.androidretrofitapimanagerdemo.model;

import com.google.gson.annotations.SerializedName;

public class Contributor {

    /** 取得3項資料: login, contributions, avatar_url */
    @SerializedName("login")
    private String login;
    @SerializedName("contributions")
    private int contributions;
    @SerializedName("avatar_url")
    private String avatar_url;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public int getContributions() {
        return contributions;
    }

    public void setContributions(int contributions) {
        this.contributions = contributions;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }
}
