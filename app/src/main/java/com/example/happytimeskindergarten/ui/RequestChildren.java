package com.example.happytimeskindergarten.ui;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface RequestChildren {
    @GET("api/child_profiles/{id}")
    @Headers("Accept: application/json;charset=UTF-8")
    Call<ChildData> getChildrens(@Path("id") String id, @Header("Authorization") String token);
    @FormUrlEncoded
    @POST("api/child_profiles/{id}")
    @Headers("Accept: application/json;charset=UTF-8")
    Call<ChildData> updateallergies(@Path("id") String id, @Header("Authorization") String token, @Field("name") String name, @Field("gender") String gender, @Field("birthday") String birthday, @Field("family_account_id") String family_account_id, @Field("allergies") String allergies);
    @FormUrlEncoded
    @POST("api/child_profiles/{id}")
    @Headers("Accept: application/json;charset=UTF-8")
    Call<ChildData> updateillnesses(@Path("id") String id, @Header("Authorization") String token, @Field("name") String name, @Field("gender") String gender, @Field("birthday") String birthday, @Field("family_account_id") String family_account_id, @Field("illnesses") String illnesses);
}
