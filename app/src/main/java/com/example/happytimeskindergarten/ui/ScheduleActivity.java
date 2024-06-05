package com.example.happytimeskindergarten.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.example.happytimeskindergarten.R;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ScheduleActivity extends AppCompatActivity implements ClassItemAdapter.OnItemListener
{
    private TextView monthYearText; // месяц, год
    private LinearLayout scheduleLinearLayout; // внутри — 7 RecyclerView'ов в качестве дней недели
    private LinearLayout numbersOfDaysLinearLayout; // внутри — 7 TextView'ов для нумерации дней в текущей неделе
    private LocalDate localDate;
    //private FragmentScheduleBinding binding;


    private String MonthYearFromDate()
    {
        // получаем номер месяца (начиная с 0)
        int month = localDate.getMonthValue();
        // Массив с названиями месяцев
        String[] monthNames =
                { "Січень", "Лютий", "Березень", "Квітень", "Травень", "Червень",
                        "Липень", "Серпень", "Вересень", "Жовтень", "Листопад", "Грудень" };

        return monthNames[month - 1] + ", " + localDate.getYear() + " р.";
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);
        int groupid = getIntent().getIntExtra("groupid", -1);
        scheduleLinearLayout = findViewById(R.id.scheduleLinearLayout);
        numbersOfDaysLinearLayout = findViewById(R.id.numbersOfDaysLinearLayout);
        monthYearText = findViewById(R.id.monthYearTextView);

        localDate = LocalDate.now();
        DayOfWeek dayOfWeek = localDate.getDayOfWeek();

        // Если сегодня понедельник, то понедельник на этой неделе — это текущая дата
        // Иначе находим первый день недели (понедельник), итерируясь назад по дням
        LocalDate monday;
        if (dayOfWeek == DayOfWeek.MONDAY) {
            monday = localDate;
        } else {
            monday = localDate.minusDays(localDate.getDayOfWeek().getValue() - 1);
        }
        for (int i = 0; i < 7; i++) {
            ((TextView) numbersOfDaysLinearLayout.getChildAt(i))
                    .setText(String.valueOf(monday.getDayOfMonth()));
            monday = monday.plusDays(1);
        }

        monthYearText.setText(MonthYearFromDate());

        List<List<ClassItem>> classesJaggedList = new ArrayList<List<ClassItem>>();

        for (int i = 0; i < 7; i++) {
            final int day = i;
            ClassItemAdapter adapter = new ClassItemAdapter(classesJaggedList.get(i)/*, this*/);
            RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 1);

            RecyclerView classesColumnRecyclerView = (RecyclerView) scheduleLinearLayout.getChildAt(i);
            classesColumnRecyclerView.setLayoutManager(layoutManager);
            classesColumnRecyclerView.setAdapter(adapter);
            Request.requestLessons.getLessons(String.valueOf(groupid), User.getToken()).enqueue(new Callback<lessonsData>() {
                @Override
                public void onResponse(Call<lessonsData> call, Response<lessonsData> response) {
                    ArrayList<ClassItem> lessons = new ArrayList<ClassItem>();
                    switch(day){
                        case 0:
                            for(lessonsData.Data day: response.body().getMonday()){
                                lessons.add(new ClassItem(day.getAction_name(), day.getStart_time(), day.getEnd_time()));
                            }
                            break;
                        case 1:
                            for(lessonsData.Data day: response.body().getTuesday()){
                                lessons.add(new ClassItem(day.getAction_name(), day.getStart_time(), day.getEnd_time()));
                            }
                            break;
                        case 2:
                            for(lessonsData.Data day: response.body().getWednesday()){
                                lessons.add(new ClassItem(day.getAction_name(), day.getStart_time(), day.getEnd_time()));
                            }
                            break;
                        case 3:
                            for(lessonsData.Data day: response.body().getThursday()){
                                lessons.add(new ClassItem(day.getAction_name(), day.getStart_time(), day.getEnd_time()));
                            }
                            break;
                        case 4:
                            for(lessonsData.Data day: response.body().getFriday()){
                                lessons.add(new ClassItem(day.getAction_name(), day.getStart_time(), day.getEnd_time()));
                            }
                            break;
                        case 5:
                            for(lessonsData.Data day: response.body().getSaturday()){
                                lessons.add(new ClassItem(day.getAction_name(), day.getStart_time(), day.getEnd_time()));
                            }
                            break;
                        case 6:
                            for(lessonsData.Data day: response.body().getSunday()){
                                lessons.add(new ClassItem(day.getAction_name(), day.getStart_time(), day.getEnd_time()));
                            }
                            break;
                    }
                    adapter.loadLessons(lessons);
                }

                @Override
                public void onFailure(Call<lessonsData> call, Throwable t) {

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

    @Override
    public void onItemClick(int position, String subjectName, String timeFrom, String timeTo)
    {
        // пока оставлю пустым
    }
}