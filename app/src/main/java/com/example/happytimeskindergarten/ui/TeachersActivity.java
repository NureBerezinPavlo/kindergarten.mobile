package com.example.happytimeskindergarten.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.Context;
import android.content.ClipboardManager;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.happytimeskindergarten.R;

public class TeachersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teachers);

        LinearLayout allTeachers = findViewById(R.id.teachersLinearLayout);
        int childrenQuantity = allTeachers.getChildCount();

        for (int i = 0; i < childrenQuantity; i++) {
            View oneTeacher = allTeachers.getChildAt(i);

            TextView emailTextView = oneTeacher.findViewById(R.id.emailTextView);
            TextView phoneNumberTextView = oneTeacher.findViewById(R.id.phoneNumberTextView);

            // Копирование имейла и телефона в буфер обмена при нажатии на соответствующие TextView
            emailTextView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                    ClipData clip = ClipData.newPlainText("label", emailTextView.getText());
                    clipboard.setPrimaryClip(clip);

                    Toast.makeText(getApplicationContext(),
                            "Імейл скопійовано до буферу обміну", Toast.LENGTH_SHORT).show();
                }
            });
            phoneNumberTextView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                    ClipData clip = ClipData.newPlainText("label", phoneNumberTextView.getText());
                    clipboard.setPrimaryClip(clip);

                    Toast.makeText(getApplicationContext(), "Телефон скопійовано до буферу обміну", Toast.LENGTH_SHORT).show();
                }
            });
        }
        View exitButton = findViewById(R.id.exitButton);
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}