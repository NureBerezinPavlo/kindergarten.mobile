package com.example.happytimeskindergarten.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.example.happytimeskindergarten.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.imageview.ShapeableImageView;
import java.io.IOException;

public class OnePersonEditActivity extends AppCompatActivity implements View.OnClickListener
{
    Person trustedPerson;
    private static final int PICK_IMAGE_REQUEST = 1;
    private static final int DELETE_REQUEST = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_trusted_person_edit);

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

        View deleteDataButton = findViewById(R.id.deleteDataButton);
        deleteDataButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                Intent intent = new Intent();
                intent.putExtra("is_deleted", true);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        View saveDataButton = findViewById(R.id.saveDataButton);
        saveDataButton.setOnClickListener(this);

        Bundle arguments = getIntent().getExtras();

        trustedPerson = (Person)arguments.getSerializable(Person.class.getSimpleName());

        TextView fullNameTextView = findViewById(R.id.fullNameEditText);
        TextView emailTextView = findViewById(R.id.emailEditText);
        TextView phoneNumberTextView = findViewById(R.id.phoneNumberEditText);

        fullNameTextView.setText(trustedPerson.getFullName());
        emailTextView.setText(trustedPerson.getEmail());
        phoneNumberTextView.setText(trustedPerson.getPhoneNumber());
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
    public void onBackPressed() {
        String fullName = ((EditText)findViewById(R.id.fullNameEditText)).getText().toString();
        String email = ((EditText)findViewById(R.id.emailEditText)).getText().toString();
        String phoneNumber = ((EditText)findViewById(R.id.phoneNumberEditText)).getText().toString();

        if (!trustedPerson.getFullName().equals(fullName) || !trustedPerson.getEmail().equals(email) || !trustedPerson.getPhoneNumber().equals(phoneNumber)) {
            View dialogBinding = getLayoutInflater().inflate(R.layout.comfirmation_dialog_block, null);
            Dialog myDialog = new Dialog(this);
            myDialog.setContentView(dialogBinding);
            myDialog.setCancelable(true);

            if (myDialog.getWindow() != null) {
                myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            }
            myDialog.show();

            FloatingActionButton closeButton = dialogBinding.findViewById(R.id.closeButton);
            closeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    myDialog.cancel();
                }
            });

            Button yesButton = dialogBinding.findViewById(R.id.yesButton);
            yesButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    OnePersonEditActivity.super.onBackPressed();
                }
            });
            Button noButton = dialogBinding.findViewById(R.id.noButton);
            noButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    myDialog.cancel();
                }
            });
        }
        else super.onBackPressed();
    }


    @Override
    public void onClick(View v) {
        // добавить валидацию введённых данных

        EditText fullNameEditText = findViewById(R.id.fullNameEditText);
        EditText emailEditText = findViewById(R.id.emailEditText);
        EditText phoneNumberEditText = findViewById(R.id.phoneNumberEditText);

        trustedPerson.setFullName(fullNameEditText.getText().toString());
        trustedPerson.setEmail(emailEditText.getText().toString());
        trustedPerson.setPhoneNumber(phoneNumberEditText.getText().toString());

        Intent intent = new Intent();
        intent.putExtra(Person.class.getSimpleName(), trustedPerson);

        setResult(RESULT_OK, intent);
        finish();
    }
}