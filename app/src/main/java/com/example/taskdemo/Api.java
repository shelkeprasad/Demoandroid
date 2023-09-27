package com.example.taskdemo;

import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {
    String BASE_URL = "https://itunes.apple.com/";
    @GET("search?term=jack+johnson")
    Call<JsonObject> getvideolist();
}
