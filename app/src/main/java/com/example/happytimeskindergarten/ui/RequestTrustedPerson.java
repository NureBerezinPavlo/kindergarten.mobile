package com.example.happytimeskindergarten.ui;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface RequestTrustedPerson {
    @GET("api/trusted_persons/{id}")
    @Headers("Accept: application/json;charset=UTF-8")
    Call<TrustedPersonData> getTrustedPerson(@Path("id") String id, @Header("Authorization") String token);

    @FormUrlEncoded
    @POST("api/trusted_persons")
    @Headers("Accept: application/json")
    Call<TrustedPersonData> createTrustedPerson(@Field("name") String name,@Field("email") String email,@Field("phone") String phone,@Field("family_account_id") String family_account_id,@Field("image") String image, @Header("Authorization") String token);

    @FormUrlEncoded
    @POST("api/trusted_persons/{id}")
    @Headers("Accept: application/json")
    Call<TrustedPersonData> updateTrustedPerson(@Path("id") String id,@Field("name") String name,@Field("email") String email,@Field("phone") String phone,@Field("family_account_id") String family_account_id,@Field("image") String image,@Header("Authorization") String token, @Field("_method") String method);


}
