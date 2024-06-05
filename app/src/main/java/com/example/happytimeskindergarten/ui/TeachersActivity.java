package com.example.happytimeskindergarten.ui;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.view.View;
import com.example.happytimeskindergarten.R;
import java.util.ArrayList;
import java.util.function.Predicate;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TeachersActivity extends AppCompatActivity implements TeacherAdapter.OnItemListener
{
    ArrayList<Person> personsList = new ArrayList<Person>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teachers);
        int childid = getIntent().getIntExtra("childid", -1);

        Person teacher1 = new Person();
        teacher1.setFullName("Рубаненко Марія");
        teacher1.setEmail("mashaagent007@gmail.com");
        teacher1.setPhoneNumber("+380-963-12-34");
        //personsList.add(teacher1);

        Person teacher2 = new Person();
        teacher2.setFullName("Бистрицька Настя");
        teacher2.setEmail("kei_rin0@gmail.com");
        teacher2.setPhoneNumber("+123-456-78-90");
        //personsList.add(teacher2);

        Person teacher3 = new Person();
        teacher3.setFullName("Голоха Нікіта");
        teacher3.setEmail("nikitagoloha@mail.com");
        teacher3.setPhoneNumber("++111-222-33-44");
        //personsList.add(teacher3);

        RecyclerView allTeachersRecyclerView = findViewById(R.id.teachersRecyclerView);
        TeacherAdapter adapter = new TeacherAdapter(personsList, this);
        Request.requestStaffsChildren.getStaffsChildren(String.valueOf(childid), User.getToken()).enqueue(new Callback<staffsData>() {
            @Override
            public void onResponse(Call<staffsData> call, Response<staffsData> response) {
                personsList = new ArrayList<Person>();
                for(staffsData.Data staff: response.body().getData()){
                    Person teacher = new Person();
                    teacher.setFullName(staff.getName());
                    teacher.setEmail(staff.getEmail());
                    teacher.setPhoneNumber(staff.getPhone());
                    teacher.setImageData(staff.getImageData());
                    personsList.add(teacher);
                    adapter.loadTeachers(personsList);
                }
            }

            @Override
            public void onFailure(Call<staffsData> call, Throwable t) {

            }
        });
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
    public void onItemClick(int position, Person person)
    {
        // здесь можно указать, что будет, если пользователь нажмёт на элемент из recyclerView
    }
}