package com.example.happytimeskindergarten.ui;

import com.google.gson.GsonBuilder;


import okhttp3.*;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class Request {
    static HttpLoggingInterceptor logging = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);



    static OkHttpClient httpClient = new OkHttpClient.Builder()
            .addInterceptor(logging)
            .build();
    static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://70f9-185-67-252-126.ngrok-free.app")
            .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().setLenient().create()))
            .client(httpClient)
            .build();
    static public RequestUser requestUser = retrofit.create(RequestUser.class);
    static public RequestFamily requestfamily = retrofit.create(RequestFamily.class);
    static public RequestString requeststring = retrofit.create(RequestString.class);
    static public RequestChildren requestChildren = retrofit.create(RequestChildren.class);
    static public RequestTrustedPerson requestTrustedPerson = retrofit.create(RequestTrustedPerson.class);

    static public RequestStaffsChildren requestStaffsChildren = retrofit.create(RequestStaffsChildren.class);

    static public RequestLessons requestLessons = retrofit.create(RequestLessons.class);

    static public Requestattendances requestattendances = retrofit.create(Requestattendances.class);

}



