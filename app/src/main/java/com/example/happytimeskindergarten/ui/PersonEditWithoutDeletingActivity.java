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
import java.io.Serial;
import java.io.Serializable;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PersonEditWithoutDeletingActivity extends AppCompatActivity implements View.OnClickListener
{
    Person person;
    EditText fullNameEditText;
    EditText emailEditText;
    EditText phoneNumberEditText;

    Boolean create;

    private static final int PICK_IMAGE_REQUEST = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_edit_without_deleting);
        create = getIntent().getBooleanExtra("create", true);
        fullNameEditText = findViewById(R.id.fullNameEditText);
        emailEditText = findViewById(R.id.emailEditText);
        phoneNumberEditText = findViewById(R.id.phoneNumberEditText);

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

        person = (Person) arguments.getSerializable(Person.class.getSimpleName());

        fullNameEditText.setText(person.getFullName());
        emailEditText.setText(person.getEmail());
        phoneNumberEditText.setText(person.getPhoneNumber());
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

    public static boolean AnyFieldContainsText(String... strs) {
        if(strs == null) return false;

        for (String str : strs) {
            if (str != null && !str.trim().isEmpty())
                return true;
        }
        return false;
    }
    @Override
    public void onBackPressed() {
        String fullName = fullNameEditText.getText().toString();
        String email = emailEditText.getText().toString();
        String phoneNumber = phoneNumberEditText.getText().toString();

        boolean openForCreating= AnyFieldContainsText(fullName, email, phoneNumber) && person == null;
        boolean openForEditing = person != null &&
                (!fullName.equals(person.getFullName()) ||
                !email.equals(person.getEmail()) ||
                !phoneNumber.equals(person.getPhoneNumber()));

        if (openForCreating || openForEditing) {
            View dialogBinding = getLayoutInflater().inflate(R.layout.comfirmation_dialog_block, null);
            Dialog myDialog = new Dialog(PersonEditWithoutDeletingActivity.this);
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
                    PersonEditWithoutDeletingActivity.super.onBackPressed();
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


        Intent intent = new Intent();
        // добавить валидацию введённых данных
        Person person = new Person();
        person.setFullName(fullNameEditText.getText().toString());
        person.setEmail(emailEditText.getText().toString());
        person.setPhoneNumber(phoneNumberEditText.getText().toString());
        if(create){
            Request.requestTrustedPerson.createTrustedPerson(fullNameEditText.getText().toString(),emailEditText.getText().toString(),phoneNumberEditText.getText().toString(),User.getFamily_account_id()[0], User.getToken()).enqueue(new Callback<TrustedPersonData>() {
                @Override
                public void onResponse(Call<TrustedPersonData> call, Response<TrustedPersonData> response) {

                }

                @Override
                public void onFailure(Call<TrustedPersonData> call, Throwable t) {

                }
            });
        }
        else{
            Request.requestTrustedPerson.updateTrustedPerson(String.valueOf(person.getId()),fullNameEditText.getText().toString(),emailEditText.getText().toString(),phoneNumberEditText.getText().toString(),User.getFamily_account_id()[0],User.getToken(),"PUT").enqueue(new Callback<TrustedPersonData>() {
                @Override
                public void onResponse(Call<TrustedPersonData> call, Response<TrustedPersonData> response) {

                }

                @Override
                public void onFailure(Call<TrustedPersonData> call, Throwable t) {

                }
            });
        }
        intent.putExtra("parent", person);

        setResult(RESULT_OK, intent);
        finish();
    }
}