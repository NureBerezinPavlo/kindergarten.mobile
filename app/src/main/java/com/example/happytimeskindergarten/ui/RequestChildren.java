package com.example.happytimeskindergarten.ui;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface RequestChildren {
    @GET("api/child_profiles/{id}")
    @Headers("Accept: application/json;charset=UTF-8")
    Call<ChildData> getChildrens(@Path("id") String id, @Header("Authorization") String token);
}
