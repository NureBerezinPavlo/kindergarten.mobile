package com.example.happytimeskindergarten.ui;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.happytimeskindergarten.R;

public class ClassViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
{
    public final TextView subjectNameTextView, timeFromTextView, timeToTextView;
    private final ClassItemAdapter.OnItemListener onItemListener;

    public ClassViewHolder(@NonNull View itemView, ClassItemAdapter.OnItemListener onItemListener) {
        super(itemView);
        subjectNameTextView = itemView.findViewById(R.id.subjectNameTextView);
        timeFromTextView = itemView.findViewById(R.id.timeFromTextView);
        timeToTextView = itemView.findViewById(R.id.timeToTextView);
        this.onItemListener = onItemListener;
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        onItemListener.onItemClick(getAdapterPosition(),
                (String)subjectNameTextView.getText(),
                (String)timeFromTextView.getText(),
                (String)timeToTextView.getText());
    }
}
