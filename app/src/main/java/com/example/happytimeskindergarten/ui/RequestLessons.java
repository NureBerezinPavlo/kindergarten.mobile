package com.example.happytimeskindergarten.ui;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface RequestLessons {

    @GET("api/index_by_group_for_week/{id}")
    @Headers("Accept: application/json;charset=UTF-8")
    Call<lessonsData> getLessons(@Path("id") String id, @Header("Authorization") String token);
}
