package com.example.happytimeskindergarten.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.happytimeskindergarten.R;
import com.google.android.material.imageview.ShapeableImageView;

import java.io.IOException;

public class OnePersonEditActivity extends AppCompatActivity implements View.OnClickListener
{
    private static final int PICK_IMAGE_REQUEST = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_edit);

        View changeAvatarButton = findViewById(R.id.changeAvatarButton);
        changeAvatarButton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, PICK_IMAGE_REQUEST);
            }
        });

        View exitButton = findViewById(R.id.exitButton);
        exitButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                onBackPressed();
            }
        });

        View saveDataButton = findViewById(R.id.saveDataButton);
        saveDataButton.setOnClickListener(this);

        Bundle arguments = getIntent().getExtras();

        String full_name = arguments.getString("full_name");
        String email = arguments.getString("email");
        String phone_number = arguments.getString("phone_number");

        TextView fullNameTextView = findViewById(R.id.fullNameEditText);
        TextView emailTextView = findViewById(R.id.emailEditText);
        TextView phoneNumberTextView = findViewById(R.id.phoneNumberEditText);

        fullNameTextView.setText(full_name);
        emailTextView.setText(email);
        phoneNumberTextView.setText(phone_number);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri uri = data.getData();
            try {
                ShapeableImageView imageView = findViewById(R.id.profileImage);
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                imageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onClick(View v) {
        EditText fullNameEditText = findViewById(R.id.fullNameEditText);
        EditText emailEditText = findViewById(R.id.emailEditText);
        EditText phoneNumberEditText = findViewById(R.id.phoneNumberEditText);

        Intent intent = new Intent();
        // добавить валидацию введённых данных
        intent.putExtra("full_name", fullNameEditText.getText().toString());
        intent.putExtra("email", emailEditText.getText().toString());
        intent.putExtra("phone_number", phoneNumberEditText.getText().toString());

        setResult(RESULT_OK, intent);
        finish();
    }
}