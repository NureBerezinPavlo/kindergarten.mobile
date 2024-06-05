package com.example.happytimeskindergarten.ui;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface RequestFamily {

    @GET("api/family_accounts_mobile/{id}")
    @Headers("Accept: application/json;charset=UTF-8")
    Call<family_accountData> getfamily(@Path("id") String id, @Header("Authorization") String token);

    @FormUrlEncoded
    @POST("api/family_accounts/{id}")
    @Headers("Accept: application/json;charset=UTF-8")
    Call<family_accountData> updateParent(@Path("id") String id, @Header("Authorization") String token, @Field("user_id") String user_id, @Field("phone_number") String phone, @Field("image") String image, @Field("_method") String method);
}
