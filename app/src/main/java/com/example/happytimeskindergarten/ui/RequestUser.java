package com.example.happytimeskindergarten.ui;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface RequestUser {
    @FormUrlEncoded
    @POST("api/login_token")
    Call<Auth> login(@Field("email") String email, @Field("password") String password, @Field("device_name") String device_name);
}
