package com.example.happytimeskindergarten.ui;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

interface RequestUser {
    @FormUrlEncoded
    @POST("api/login_token")
    Call<Auth> login(@Field("email") String email, @Field("password") String password, @Field("device_name") String device_name);
}



public class Request {

    static String token;

    public static String getToken() {
        return token;
    }

    public static void setToken(String token) {
        Request.token = token;
    }

    static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8000")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    static public RequestUser requestUser = retrofit.create(RequestUser.class);
}



