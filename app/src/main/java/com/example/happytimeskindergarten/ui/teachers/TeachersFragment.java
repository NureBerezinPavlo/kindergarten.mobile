package com.example.happytimeskindergarten.ui.teachers;

import androidx.lifecycle.ViewModelProvider;

import android.content.ClipData;
import android.content.Context;
import android.content.ClipboardManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.happytimeskindergarten.R;

public class TeachersFragment extends Fragment {

    private TeachersViewModel mViewModel;

    public static TeachersFragment newInstance() {
        return new TeachersFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_teachers, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(TeachersViewModel.class);

        LinearLayout allTeachers = getView().findViewById(R.id.teachersLinearLayout);
        int childrenQuantity = allTeachers.getChildCount();

        for (int i = 0; i < childrenQuantity; i++)
        {
            View oneTeacher = allTeachers.getChildAt(i);

            TextView emailTextView = oneTeacher.findViewById(R.id.emailTextView);
            TextView phoneNumberTextView = oneTeacher.findViewById(R.id.phoneNumberTextView);

            // Копирование имейла и телефона в буфер обмена при нажатии на соответствующие TextView
            emailTextView.setOnClickListener(new View.OnClickListener()
            {
                public void onClick(View view) {
                    ClipboardManager clipboard = (ClipboardManager)
                            getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
                    ClipData clip = ClipData.newPlainText("label", emailTextView.getText());
                    clipboard.setPrimaryClip(clip);

                    Toast.makeText(getActivity().getApplicationContext(),
                            "Імейл скопійовано до буферу обміну", Toast.LENGTH_SHORT).show();
                }
            });
            phoneNumberTextView.setOnClickListener(new View.OnClickListener()
            {
                public void onClick(View view) {
                    ClipboardManager clipboard = (ClipboardManager)
                            getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
                    ClipData clip = ClipData.newPlainText("label", phoneNumberTextView.getText());
                    clipboard.setPrimaryClip(clip);

                    Toast.makeText(getActivity().getApplicationContext(),
                            "Телефон скопійовано до буферу обміну", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

}