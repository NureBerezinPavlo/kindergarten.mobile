package com.example.happytimeskindergarten.ui;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface Requestattendances {
    @FormUrlEncoded
    @POST("api/attendances")
    @Headers("Accept: application/json;charset=UTF-8")
    Call<attendancesData> pushattendance(@Field("date") String date, @Field("reason") String reason, @Field("child_profile_id") String child_profile_id, @Header("Authorization") String token);

}
