package com.example.happytimeskindergarten.ui;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface RequestFamily {

    @GET("api/family_accounts_mobile/{id}")
    @Headers("Accept: application/json;charset=UTF-8")
    Call<family_accountData> getfamily(@Path("id") String id, @Header("Authorization") String token);
}
