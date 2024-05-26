package com.example.happytimeskindergarten.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.happytimeskindergarten.R;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ClassItemAdapter extends RecyclerView.Adapter<ClassViewHolder>
{
    List<ClassItem> classesArraylist;
    //OnItemListener onItemListener;

    public ClassItemAdapter(List<ClassItem> classesArraylist/*, OnItemListener onItemListener*/)
    {
        this.classesArraylist = classesArraylist;
        //this.onItemListener = onItemListener;
    }

    @NonNull
    @Override
    public ClassViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.class_cell, parent, false);
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.height = 260;

        return new ClassViewHolder (view/*, onItemListener*/);
    }

    @Override
    public void onBindViewHolder(@NonNull ClassViewHolder holder, int i)
    {
        holder.subjectNameTextView.setText(classesArraylist.get(i).subjectName);
        holder.timeFromTextView.setText(classesArraylist.get(i).timeFrom);
        holder.timeToTextView.setText(classesArraylist.get(i).timeTo);
    }

    @Override
    public int getItemCount() { return classesArraylist.size(); }

    public interface OnItemListener
    {
        void onItemClick(int position, String subjectName, String timeFrom, String timeTo);
    }

    public void loadLessons(ArrayList<ClassItem> lessons){
        this.classesArraylist = lessons;
        notifyDataSetChanged();
    }
}