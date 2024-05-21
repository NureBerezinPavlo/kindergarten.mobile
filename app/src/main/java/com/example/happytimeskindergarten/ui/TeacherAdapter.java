package com.example.happytimeskindergarten.ui;

import android.content.ClipData;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.content.ClipboardManager;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.happytimeskindergarten.R;

import java.util.ArrayList;

public class TeacherAdapter extends RecyclerView.Adapter<TeacherViewHolder>
{
    public ArrayList<Person> personsArraylist;
    TeacherAdapter.OnItemListener onItemListener;

    public TeacherAdapter(ArrayList<Person> personsArraylist, TeacherAdapter.OnItemListener onItemListener)
    {
        this.personsArraylist = personsArraylist;
        this.onItemListener = onItemListener;
    }

    @NonNull
    @Override
    public TeacherViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View layout = layoutInflater.inflate(R.layout.person_data_block, parent, false);

        View photoAndFullnameView = layout.findViewById(R.id.photoAndFullname);
        TextView emailTextView = layout.findViewById(R.id.emailTextView);
        TextView phoneNumberTextView = layout.findViewById(R.id.phoneNumberTextView);

        int totalHeight = measureView(photoAndFullnameView)
                + measureView(emailTextView)
                + measureView(phoneNumberTextView);

        ViewGroup.LayoutParams layoutParams = layout.getLayoutParams();
        layoutParams.height = totalHeight;

        // Копирование имейла и телефона в буфер обмена при нажатии на соответствующие TextView
        emailTextView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ClipboardManager clipboard = (ClipboardManager)
                        parent.getContext().getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("label", emailTextView.getText());
                clipboard.setPrimaryClip(clip);

                Toast.makeText(parent.getContext(),
                        "Імейл скопійовано до буферу обміну", Toast.LENGTH_SHORT).show();
            }
        });
        phoneNumberTextView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ClipboardManager clipboard = (ClipboardManager)
                        parent.getContext().getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("label", phoneNumberTextView.getText());
                clipboard.setPrimaryClip(clip);

                Toast.makeText(parent.getContext(),
                        "Телефон скопійовано до буферу обміну", Toast.LENGTH_SHORT).show();
            }
        });

        return new TeacherViewHolder(layout, onItemListener, personsArraylist.get(viewType));
    }
    private int measureView(View view) {
        view.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        return view.getMeasuredHeight() + view.getPaddingTop() + view.getPaddingBottom();
    }
    @Override
    public void onBindViewHolder(@NonNull TeacherViewHolder holder, int i)
    {
        holder.fullNameTextView.setText(personsArraylist.get(i).getFullName());
        holder.emailTextView.setText(personsArraylist.get(i).getEmail());
        holder.phoneNumberTextView.setText(personsArraylist.get(i).getPhoneNumber());
    }

    @Override
    public int getItemCount() { return personsArraylist.size(); }

    public interface OnItemListener
    {
        void onItemClick(int position, Person person);
    }
}