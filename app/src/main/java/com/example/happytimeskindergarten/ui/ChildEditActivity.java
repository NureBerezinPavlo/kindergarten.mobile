package com.example.happytimeskindergarten.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.happytimeskindergarten.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.time.LocalTime;

public class ChildEditActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child_edit);

        // Кнопка перехода обратно в главное меню с детьми
        View exitButton = findViewById(R.id.exitButton);
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        // Кнопка, которая открывает окно для указания аллергий
        View allergiesEditButton = findViewById(R.id.allergiesEditButton);
        allergiesEditButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                    public void onClick(View v) {
                        myDialog.cancel();
                    }
                });

                Button safeButton = dialogBinding.findViewById(R.id.safeButton);
                safeButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        allergiesTextView.setText(editText.getText());
                        myDialog.cancel();
                    }
                });
            }
        });

        // Кнопка, которая открывает окно для указания болезней
        View diseasesEditButton = findViewById(R.id.diseasesEditButton);
        diseasesEditButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                    public void onClick(View v) {
                        myDialog.cancel();
                    }
                });

                Button safeButton = dialogBinding.findViewById(R.id.safeButton);
                safeButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        diseasesTextView.setText(editText.getText());
                        myDialog.cancel();
                    }
                });
            }
        });

        // Кнопка для редиректа на список педагогов
        View teachersButton = findViewById(R.id.teachersButton);
        teachersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent teachersIntent = new Intent(ChildEditActivity.this, TeachersActivity.class);
                startActivity(teachersIntent);
            }
        });

        // Кнопка для редиректа на расписание
        View scheduleButton = findViewById(R.id.scheduleButton);
        scheduleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent scheduleIntent = new Intent(ChildEditActivity.this, ScheduleActivity.class);
                // передаём в интент данные из текущего активити
                startActivity(scheduleIntent);
            }
        });

        // Кнопка, которая открывает окно для указания причины сегодняшнего отсутствия ребёнка
        Button noticeOfAbsenceButton = (Button) findViewById(R.id.noticeOfAbsenceButton);
        LocalTime currentTime = LocalTime.now();

        noticeOfAbsenceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentTime.getHour() < 10) {
                    View dialogBinding = getLayoutInflater().inflate(R.layout.edit_text_dialog_window, null);
                    Dialog myDialog = new Dialog(ChildEditActivity.this);
                    myDialog.setContentView(dialogBinding);
                    myDialog.setCancelable(true);

                    EditText editText = myDialog.findViewById(R.id.editText);
                    TextView titleTextView = myDialog.findViewById(R.id.titleTextView);
                    titleTextView.setText("Вкажіть причину");

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

                    Button safeButton = dialogBinding.findViewById(R.id.safeButton);
                    safeButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            // сохраняем где-то текст с причиной отсутствия (он в editText)
                            myDialog.cancel();
                        }
                    });
                } else {
                    Toast.makeText(getApplicationContext(), "Час для повідомлення про відсутність сплинув.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}