package com.example.happytimeskindergarten.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.view.View;
import com.example.happytimeskindergarten.R;
import java.util.ArrayList;

public class TeachersActivity extends AppCompatActivity implements TeacherAdapter.OnItemListener
{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teachers);

        ArrayList<Person> personsList = new ArrayList<Person>() {{
            add(new Person("Рубаненко Марія", "mashaagent007@gmail.com", "+380-963-12-34"));
            add(new Person("Бистрицька Настя", "kei_rin0@gmail.com", "+123-456-78-90"));
            add(new Person("Голоха Нікіта", "nikitagoloha@mail.com", "++111-222-33-44"));
        }};

        RecyclerView allTeachersRecyclerView = findViewById(R.id.teachersRecyclerView);
        TeacherAdapter adapter = new TeacherAdapter(personsList, this);
        RecyclerView.LayoutManager layoutManager =
                new GridLayoutManager(getApplicationContext(), 1);
        allTeachersRecyclerView.setLayoutManager(layoutManager);
        allTeachersRecyclerView.setAdapter(adapter);

        View exitButton = findViewById(R.id.exitButton);
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Override
    public void onItemClick(int position, String fullName, String email, String phoneNumber)
    {
        // здесь можно указать, что будет, если пользователь нажмёт на элемент из recyclerView
    }
}