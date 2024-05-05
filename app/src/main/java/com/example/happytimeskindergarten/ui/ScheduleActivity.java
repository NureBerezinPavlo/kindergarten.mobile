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

        List<List<ClassItem>> classesJaggedList = new ArrayList<List<ClassItem>>() {{
            add(new ArrayList<ClassItem>() {{
                add(new ClassItem("Математика", "10:00", "10:30"));
                add(new ClassItem("Литература", "10:50", "11:20"));
            }});
            add(new ArrayList<ClassItem>() {{
                add(new ClassItem("Физика", "12:00", "13:00"));
                add(new ClassItem("Математика", "13:20", "13:50"));
                add(new ClassItem("Математика", "14:10", "15:00"));
            }});
            add(new ArrayList<ClassItem>() {{
                add(new ClassItem("Биология", "8:25", "5:30"));
            }});
            add(new ArrayList<ClassItem>() {{
                add(new ClassItem("Музыка", "00:00", "00:00"));
                add(new ClassItem("Английский", "00:15", "5:55"));
                add(new ClassItem("Информатика", "6:10", "8:10"));
                add(new ClassItem("История", "10:30", "15:16"));
            }});
            add(new ArrayList<ClassItem>() {
            });
            add(new ArrayList<ClassItem>() {{
                add(new ClassItem("Физика", "17:18", "19:20"));
            }});
            add(new ArrayList<ClassItem>() {{
                add(new ClassItem("География", "13:10", "13:09"));
                add(new ClassItem("Литература", "69:69", "2:28"));
            }});
        }};

        for (int i = 0; i < 7; i++) {
            ClassItemAdapter adapter = new ClassItemAdapter(classesJaggedList.get(i)/*, this*/);
            RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 1);

            RecyclerView classesColumnRecyclerView = (RecyclerView) scheduleLinearLayout.getChildAt(i);
            classesColumnRecyclerView.setLayoutManager(layoutManager);
            classesColumnRecyclerView.setAdapter(adapter);
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