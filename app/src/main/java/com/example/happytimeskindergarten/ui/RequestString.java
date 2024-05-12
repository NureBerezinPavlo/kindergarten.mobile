package com.example.happytimeskindergarten.ui;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RequestString {
    @GET("api/family_accounts/{id}")
//    @Headers("Accept: application/json")
    Call<String> getString(@Path("id") String id);
}
