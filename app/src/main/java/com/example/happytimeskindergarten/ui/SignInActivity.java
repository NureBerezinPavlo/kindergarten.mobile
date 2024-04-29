package com.example.happytimeskindergarten.ui;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.happytimeskindergarten.R;

import java.io.Console;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
public class SignInActivity extends AppCompatActivity {

    interface RequestUser {
        @GET("/api/users/{uid}")
        Call<UserData> getUser(@Path("uid") String uid);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8000")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RequestUser requestUser = retrofit.create(RequestUser.class);
        View signOutButton = findViewById(R.id.signInButton);
        signOutButton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {
                requestUser.getUser("2").enqueue(new Callback<UserData>() {
                    @Override
                    public void onResponse(Call<UserData> call, Response<UserData> response) {
                        // тут надо прописать условие, при котором осуществится переход в соответствующий аккаунт
                        Intent intent = new Intent(SignInActivity.this, MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    }

                    @Override
                    public void onFailure(Call<UserData> call, Throwable t) {
                        System.out.println("skdjskdjskdjskdjdjskdskdj");
                    }
                });

            }
        });
    }
}