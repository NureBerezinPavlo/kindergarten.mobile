package com.example.happytimeskindergarten.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.happytimeskindergarten.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ChildEditActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child_edit);

        View exitButton = findViewById(R.id.exitButton);
        exitButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                onBackPressed();
            }
        });

        View allergiesEditButton = findViewById(R.id.allergiesEditButton);
        allergiesEditButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                View dialogBinding = getLayoutInflater().inflate(R.layout.edit_text_dialog_window, null);
                Dialog myDialog = new Dialog(ChildEditActivity.this);
                myDialog.setContentView(dialogBinding);
                myDialog.setCancelable(true);

                View allergiesBlock = findViewById(R.id.allergiesText);
                TextView allergiesTextView = allergiesBlock.findViewById(R.id.contentTextView);
                EditText editText = myDialog.findViewById(R.id.editText);

                if (myDialog.getWindow() != null) {
                    myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                }
                myDialog.show();
                editText.setText(allergiesTextView.getText());

                FloatingActionButton closeButton = dialogBinding.findViewById(R.id.closeButton);
                closeButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v)
                    {
                        myDialog.cancel();
                    }
                });

                Button safeButton = dialogBinding.findViewById(R.id.safeButton);
                safeButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v)
                    {
                        allergiesTextView.setText(editText.getText());
                        myDialog.cancel();
                    }
                });
            }
        });

        View diseasesEditButton = findViewById(R.id.diseasesEditButton);
        diseasesEditButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                View dialogBinding = getLayoutInflater().inflate(R.layout.edit_text_dialog_window, null);
                Dialog myDialog = new Dialog(ChildEditActivity.this);
                myDialog.setContentView(dialogBinding);
                myDialog.setCancelable(true);

                View diseasesBlock = findViewById(R.id.diseasesText);
                TextView diseasesTextView = diseasesBlock.findViewById(R.id.contentTextView);
                EditText editText = myDialog.findViewById(R.id.editText);

                if (myDialog.getWindow() != null) {
                    myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                }
                myDialog.show();
                editText.setText(diseasesTextView.getText());

                // Змінюємо текст заголовку діалогового вікна
                TextView title = dialogBinding.findViewById(R.id.titleTextView);
                title.setText("Опис хвороб");

                FloatingActionButton closeButton = dialogBinding.findViewById(R.id.closeButton);
                closeButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v)
                    {
                        myDialog.cancel();
                    }
                });

                Button safeButton = dialogBinding.findViewById(R.id.safeButton);
                safeButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v)
                    {
                        diseasesTextView.setText(editText.getText());
                        myDialog.cancel();
                    }
                });
            }
        });
    }
}