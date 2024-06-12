package com.example.happytimeskindergarten.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.happytimeskindergarten.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.imageview.ShapeableImageView;

import org.w3c.dom.Text;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import org.json.simple.JSONObject;
public class ChildEditActivity extends AppCompatActivity {

    Child child;
    private static final int PICK_IMAGE_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child_edit);

        // Достаём переданные данные
        Bundle arguments = getIntent().getExtras();
        TextView fullNameTextView = findViewById(R.id.fullNameTextView);
        TextView groupNameTextView = findViewById(R.id.groupNameTextView);
        TextView dateOfBirthTextView = findViewById(R.id.dateOfBirthTextView);
        TextView diseasesTextView = findViewById(R.id.diseasesText).findViewById(R.id.contentTextView);
        TextView allergiesTextView = findViewById(R.id.allergiesText).findViewById(R.id.contentTextView);
        ShapeableImageView avatar = findViewById(R.id.profileImage);
        child = (Child)arguments.getSerializable(Child.class.getSimpleName());

        fullNameTextView.setText(child.getFullName());
        String verbFormByGender = child.getGender() == Child.Gender.FEMALE? "Народилася " : "Народився ";
        String dateOfBirthString = verbFormByGender + child.getBirthday();
        dateOfBirthTextView.setText(dateOfBirthString);
        groupNameTextView.setText("Група: " + child.getGroup_name());
        diseasesTextView.setText(child.getIllnesses());
        allergiesTextView.setText(child.getAllergies());
        Request.requestChildren.getChildrens(String.valueOf(child.getId()), User.getToken()).enqueue(new Callback<ChildData>() {
            @Override
            public void onResponse(Call<ChildData> call, Response<ChildData> response) {
                if(response.body().getData().getImage_data() != null){
                    avatar.setImageBitmap(Base64image.decode_image(response.body().getData().getImage_data()));
                }
            }

            @Override
            public void onFailure(Call<ChildData> call, Throwable t) {

            }
        });
        // аватарка
        ShapeableImageView profileImage = findViewById(R.id.profileImage);
        if(child.getImage_data() != null){
            profileImage.setImageBitmap(Base64image.decode_image(child.getImage_data()));
        }


        // Кнопка перехода обратно в главное меню с детьми
        View exitButton = findViewById(R.id.exitButton);
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
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
                        child.setAllergies(editText.getText().toString());
                        Request.requestChildren.updateallergies(String.valueOf(child.getId()), User.getToken(), child.getFullName(), child.getGender() == Child.Gender.MALE ? "male" : "female", child.getBirthday(), User.getFamily_account_id()[0],editText.getText().toString(), "PUT").enqueue(new Callback<ChildData>() {
                            @Override
                            public void onResponse(Call<ChildData> call, Response<ChildData> response) {

                            }

                            @Override
                            public void onFailure(Call<ChildData> call, Throwable t) {

                            }
                        });
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
                        child.setIllnesses(editText.getText().toString());
                        Request.requestChildren.updateillnesses(String.valueOf(child.getId()), User.getToken(), child.getFullName(), child.getGender() == Child.Gender.MALE ? "male" : "female", child.getBirthday(), User.getFamily_account_id()[0],editText.getText().toString(), "PUT").enqueue(new Callback<ChildData>() {
                            @Override
                            public void onResponse(Call<ChildData> call, Response<ChildData> response) {

                            }

                            @Override
                            public void onFailure(Call<ChildData> call, Throwable t) {

                            }
                        });
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
                teachersIntent.putExtra("childid", child.getId());
                startActivity(teachersIntent);
            }
        });

        // Кнопка для редиректа на расписание
        View scheduleButton = findViewById(R.id.scheduleButton);
        scheduleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent scheduleIntent = new Intent(ChildEditActivity.this, ScheduleActivity.class);
                scheduleIntent.putExtra("groupid", child.getGroup_id());
                // передаём в интент данные из текущего активити
                startActivity(scheduleIntent);
            }
        });

        // Кнопка, которая открывает окно для указания причины сегодняшнего отсутствия ребёнка
        Button noticeOfAbsenceButton = (Button) findViewById(R.id.noticeOfAbsenceButton);
        LocalTime currentTime = LocalTime.now();

        // Кнопка уведомления об отсутствии ребёнка
        noticeOfAbsenceButton.setOnClickListener(new View.OnClickListener() {
            LocalDate date = LocalDate.now();
            @Override
            public void onClick(View v) {
                if (currentTime.getHour() < 10) {
                    View dialogBinding = getLayoutInflater().inflate(R.layout.edit_text_dialog_window, null);
                    Dialog myDialog = new Dialog(ChildEditActivity.this);
                    myDialog.setContentView(dialogBinding);
                    myDialog.setCancelable(true);

                    EditText editText = myDialog.findViewById(R.id.editText);
                    TextView titleTextView = myDialog.findViewById(R.id.titleTextView);
                    titleTextView.setText("Вкажіть причину на сьогодні");

                    if (myDialog.getWindow() != null) {
                        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    }
                    myDialog.show();
                    // Кнопка закрытия диалогового окна без сохранения текста из поля ввода
                    FloatingActionButton closeButton = dialogBinding.findViewById(R.id.closeButton);
                    closeButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            myDialog.cancel();
                        }
                    });
                    // Кнопка сохранения текста из диалогового окна
                    Button safeButton = dialogBinding.findViewById(R.id.safeButton);
                    safeButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v)
                        {
                            View confirmDialogBinding = getLayoutInflater().inflate(R.layout.comfirmation_dialog_block, null);
                            Dialog confirmDialog = new Dialog(ChildEditActivity.this);
                            confirmDialog.setContentView(confirmDialogBinding);
                            confirmDialog.setCancelable(true);

                            if (confirmDialog.getWindow() != null) {
                                confirmDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                            }
                            confirmDialog.show();

                            TextView messageTextView = confirmDialog.findViewById(R.id.messageTextView);
                            messageTextView.setText("Чи дійсно підтверджуєте відсутність дитини?");

                            FloatingActionButton closeButton = confirmDialogBinding.findViewById(R.id.closeButton);
                            closeButton.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    confirmDialog.cancel();
                                }
                            });

                            Button yesButton = confirmDialogBinding.findViewById(R.id.yesButton);
                            yesButton.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    // сохраняем где-то текст с причиной отсутствия (он в editText)

                                    Request.requestattendances.pushattendance(date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")), editText.getText().toString(), User.getFamily_account_id()[0], User.getToken()).enqueue(new Callback<JSONObject>() {
                                        @Override
                                        public void onResponse(Call<JSONObject> call, Response<JSONObject> response) {

                                        }

                                        @Override
                                        public void onFailure(Call<JSONObject> call, Throwable t) {

                                        }
                                    });
                                    confirmDialog.cancel();
                                    myDialog.cancel();
                                }
                            });
                            Button noButton = confirmDialogBinding.findViewById(R.id.noButton);
                            noButton.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    confirmDialog.cancel();
                                }
                            });
                        }
                    });
                } else {
                    View dialogBinding = getLayoutInflater().inflate(R.layout.edit_text_dialog_window, null);
                    Dialog myDialog = new Dialog(ChildEditActivity.this);
                    myDialog.setContentView(dialogBinding);
                    myDialog.setCancelable(true);

                    EditText editText = myDialog.findViewById(R.id.editText);
                    TextView titleTextView = myDialog.findViewById(R.id.titleTextView);
                    titleTextView.setText("Вкажіть причину на завтра");

                    if (myDialog.getWindow() != null) {
                        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    }
                    myDialog.show();
                    // Кнопка закрытия диалогового окна без сохранения текста из поля ввода
                    FloatingActionButton closeButton = dialogBinding.findViewById(R.id.closeButton);
                    closeButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            myDialog.cancel();
                        }
                    });
                    // Кнопка сохранения текста из диалогового окна
                    Button safeButton = dialogBinding.findViewById(R.id.safeButton);
                    safeButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v)
                        {
                            View confirmDialogBinding = getLayoutInflater().inflate(R.layout.comfirmation_dialog_block, null);
                            Dialog confirmDialog = new Dialog(ChildEditActivity.this);
                            confirmDialog.setContentView(confirmDialogBinding);
                            confirmDialog.setCancelable(true);

                            if (confirmDialog.getWindow() != null) {
                                confirmDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                            }
                            confirmDialog.show();

                            TextView messageTextView = confirmDialog.findViewById(R.id.messageTextView);
                            messageTextView.setText("Чи дійсно підтверджуєте відсутність дитини?");

                            FloatingActionButton closeButton = confirmDialogBinding.findViewById(R.id.closeButton);
                            closeButton.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    confirmDialog.cancel();
                                }
                            });

                            Button yesButton = confirmDialogBinding.findViewById(R.id.yesButton);
                            yesButton.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    // сохраняем где-то текст с причиной отсутствия (он в editText)
                                    date = date.plusDays(1);
                                    Request.requestattendances.pushattendance(date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")), editText.getText().toString(), User.getFamily_account_id()[0], User.getToken()).enqueue(new Callback<JSONObject>() {
                                        @Override
                                        public void onResponse(Call<JSONObject> call, Response<JSONObject> response) {
                                            if(response.errorBody() != null){
                                                Toast.makeText(getApplicationContext(), "Відсутність вже відмічено", Toast.LENGTH_SHORT).show();
                                                confirmDialog.cancel();

                                            }
                                            else {
                                                Toast.makeText(getApplicationContext(), "Відсутність відмічено", Toast.LENGTH_SHORT).show();
                                                confirmDialog.cancel();
                                                myDialog.cancel();

                                            }
                                        }

                                        @Override
                                        public void onFailure(Call<JSONObject> call, Throwable t) {

                                        }
                                    });
                                }
                            });
                            Button noButton = confirmDialogBinding.findViewById(R.id.noButton);
                            noButton.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    confirmDialog.cancel();
                                }
                            });
                        }
                    });
                }
            }
        });
        View changeAvatarButton = findViewById(R.id.changeAvatarButton);
        changeAvatarButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, PICK_IMAGE_REQUEST);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri uri = data.getData();
            try {
                ShapeableImageView imageView = findViewById(R.id.profileImage);
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);

                // Никит, переиначиваешь запросы так, чтобы ава обновлялась у ребёнка.
                // Этот коммент потом сотрёшь.
                // И не забудь изменить фотку для child (поле в самом верху)

               Request.requestChildren.updateimage(String.valueOf(child.getId()), User.getToken(), child.getFullName(), child.getGender() == Child.Gender.MALE ? "male" : "female", child.getBirthday(), User.getFamily_account_id()[0], Base64image.encode_image(bitmap), "PUT" ).enqueue(new Callback<ChildData>() {
                   @Override
                   public void onResponse(Call<ChildData> call, Response<ChildData> response) {

                   }

                   @Override
                   public void onFailure(Call<ChildData> call, Throwable t) {

                   }
               });
               imageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent();
        intent.putExtra(Child.class.getSimpleName(), child);
        setResult(RESULT_OK, intent);
        finish();
        super.onBackPressed();
    }
}