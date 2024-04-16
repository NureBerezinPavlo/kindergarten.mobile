package com.example.happytimeskindergarten.ui.schedule;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.happytimeskindergarten.R;
import com.example.happytimeskindergarten.databinding.FragmentScheduleBinding;
import com.example.happytimeskindergarten.ui.ClassItem;
import com.example.happytimeskindergarten.ui.ClassItemAdapter;


public class ScheduleFragment extends Fragment implements ClassItemAdapter.OnItemListener
{
    private TextView monthYearText; // месяц, год
    private LinearLayout scheduleLinearLayout; // внутри — 7 RecyclerView'ов в качестве дней недели
    private LinearLayout numbersOfDaysLinearLayout; // внутри — 7 TextView'ов для нумерации дней в текущей неделе
    private LocalDate localDate;
    private FragmentScheduleBinding binding;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //currentDateCalendar = Calendar.getInstance();
        View view = getView();
        scheduleLinearLayout = view.findViewById(R.id.scheduleLinearLayout);
        numbersOfDaysLinearLayout = view.findViewById(R.id.numbersOfDaysLinearLayout);
        monthYearText = view.findViewById(R.id.monthYearTextView);

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
        for(int i = 0; i < 7; i++)
        {
            ((TextView)numbersOfDaysLinearLayout.getChildAt(i))
                    .setText(String.valueOf(monday.getDayOfMonth()));
            monday = monday.plusDays(1);
        }

        monthYearText.setText(MonthYearFromDate());

        List<List<ClassItem>> classesJaggedList = new ArrayList<List<ClassItem>>() {{
            add(new ArrayList<ClassItem>() {{
                add(new ClassItem("Математика", "69:69", "69:69"));
                add(new ClassItem("Литература", "69:69", "69:69"));
            }});
            add(new ArrayList<ClassItem>() {{
                add(new ClassItem("Физика", "69:69", "69:69"));
                add(new ClassItem("Математика", "69:69", "69:69"));
                add(new ClassItem("Математика", "69:69", "69:69"));
            }});
            add(new ArrayList<ClassItem>() {{
                add(new ClassItem("Биология", "69:69", "69:69"));
            }});
            add(new ArrayList<ClassItem>() {{
                add(new ClassItem("Музыка", "69:69", "69:69"));
                add(new ClassItem("Английский", "69:69", "69:69"));
                add(new ClassItem("Информатика", "69:69", "69:69"));
                add(new ClassItem("История", "69:69", "69:69"));
            }});
            add(new ArrayList<ClassItem>() {});
            add(new ArrayList<ClassItem>() {{
                add(new ClassItem("Физика", "69:69", "69:69"));
            }});
            add(new ArrayList<ClassItem>() {{
                add(new ClassItem("География", "69:69", "69:69"));
                add(new ClassItem("Литература", "69:69", "69:69"));
            }});
        }};

        for(int i = 0; i < 7; i++)
        {
            ClassItemAdapter adapter = new ClassItemAdapter(classesJaggedList.get(i), this);
            RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity().getApplicationContext(), 1);

            RecyclerView classesColumnRecyclerView = (RecyclerView)scheduleLinearLayout.getChildAt(i);
            classesColumnRecyclerView.setLayoutManager(layoutManager);
            classesColumnRecyclerView.setAdapter(adapter);
        }
    }

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

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ScheduleViewModel scheduleViewModel =
                new ViewModelProvider(this).get(ScheduleViewModel.class);

        binding = FragmentScheduleBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onItemClick(int position, String subjectName, String timeFrom, String timeTo)
    {
        // пока оставлю пустым
    }
}