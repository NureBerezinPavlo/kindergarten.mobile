package com.example.happytimeskindergarten.ui;

import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.happytimeskindergarten.R;
import com.google.android.material.imageview.ShapeableImageView;

public class ChildViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
{
    public Child child;
    public final TextView fullNameTextView;
    public final ShapeableImageView profileImage;
    private final ChildAdapter.OnItemListener onItemListener;

    public ChildViewHolder(@NonNull View itemView, ChildAdapter.OnItemListener onItemListener, Child child)
    {
        super(itemView);
        fullNameTextView = itemView.findViewById(R.id.nameTextView);
        this.onItemListener = onItemListener;
        this.child = child;
        this.profileImage = itemView.findViewById(R.id.profileImage);
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        onItemListener.onItemClick(getAdapterPosition(), child);
    }
}