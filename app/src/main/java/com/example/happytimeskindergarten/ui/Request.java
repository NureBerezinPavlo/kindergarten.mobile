package com.example.happytimeskindergarten.ui;

import com.google.gson.GsonBuilder;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Request {

    static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8000")
            .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().setLenient().create()))
            .build();
    static public RequestUser requestUser = retrofit.create(RequestUser.class);
    static public RequestFamily requestfamily = retrofit.create(RequestFamily.class);
    static public RequestString requeststring = retrofit.create(RequestString.class);
    static public RequestChildren requestChildren = retrofit.create(RequestChildren.class);
    static public RequestTrustedPerson requestTrustedPerson = retrofit.create(RequestTrustedPerson.class);

}



