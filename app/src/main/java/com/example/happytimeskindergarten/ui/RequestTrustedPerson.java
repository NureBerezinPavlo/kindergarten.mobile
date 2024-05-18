package com.example.happytimeskindergarten.ui;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface RequestTrustedPerson {
    @GET("api/trusted_persons/{id}")
    @Headers("Accept: application/json;charset=UTF-8")
    Call<TrustedPersonData> getTrustedPerson(@Path("id") String id, @Header("Authorization") String token);
}
