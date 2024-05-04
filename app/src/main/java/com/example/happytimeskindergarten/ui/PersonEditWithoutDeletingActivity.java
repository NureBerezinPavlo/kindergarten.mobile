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
import java.io.Serial;
import java.io.Serializable;

public class PersonEditWithoutDeletingActivity extends AppCompatActivity implements View.OnClickListener
{
    private static final int PICK_IMAGE_REQUEST = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_edit_without_deleting);

        View changeAvatarButton = findViewById(R.id.changeAvatarButton);
        changeAvatarButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, PICK_IMAGE_REQUEST);
            }
        });

        View exitButton = findViewById(R.id.exitButton);
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        View saveDataButton = findViewById(R.id.saveDataButton);
        saveDataButton.setOnClickListener(this);

        Bundle arguments = getIntent().getExtras();
        if (arguments == null) return;

        Person person = (Person) arguments.getSerializable(Person.class.getSimpleName());

        ((EditText) findViewById(R.id.fullNameEditText)).setText(person.fullName);
        ((EditText) findViewById(R.id.emailEditText)).setText(person.email);
        ((EditText) findViewById(R.id.phoneNumberEditText)).setText(person.phoneNumber);
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
        Person person = new Person(fullNameEditText.getText().toString(),
                emailEditText.getText().toString(), phoneNumberEditText.getText().toString());
        intent.putExtra("parent", person);

        setResult(RESULT_OK, intent);
        finish();
    }
}