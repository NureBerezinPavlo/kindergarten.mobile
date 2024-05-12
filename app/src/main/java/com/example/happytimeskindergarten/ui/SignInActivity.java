package com.example.happytimeskindergarten.ui;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.happytimeskindergarten.R;
import com.google.android.material.textfield.TextInputEditText;

import java.io.Console;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
public class SignInActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        View signOutButton = findViewById(R.id.signInButton);
        TextInputEditText email = findViewById(R.id.email);
        TextInputEditText password = findViewById(R.id.password);
        signOutButton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {

                Request.requestUser.login(email.getText().toString(),password.getText().toString(),"asasas").enqueue(new Callback<Auth>() {
                    @Override
                    public void onResponse(Call<Auth> call, Response<Auth> response) {
                        User.setToken(response.body().token);
                        User.setId(response.body().id);
                        User.setFamily_account_id(response.body().family_account_id);

                        // тут надо прописать условие, при котором осуществится переход в соответствующий аккаунт
                        Intent intent = new Intent(SignInActivity.this, MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    }

                    @Override
                    public void onFailure(Call<Auth> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "Некоректні логін або пароль", Toast.LENGTH_SHORT).show();
                    }
                });


            }
        });
    }
}