package com.example.happytimeskindergarten.ui;

import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.happytimeskindergarten.R;
import com.google.android.material.imageview.ShapeableImageView;

public class TrustedPersonViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
{
    Person person;
    public final TextView fullNameTextView, emailTextView, phoneNumberTextView;
    private final TrustedPersonAdapter.OnItemListener onItemListener;
    public final ShapeableImageView avatar;
    public TrustedPersonViewHolder(@NonNull View itemView, TrustedPersonAdapter.OnItemListener onItemListener, Person person) {
        super(itemView);
        fullNameTextView = itemView.findViewById(R.id.fullNameTextView);
        emailTextView = itemView.findViewById(R.id.emailTextView);
        phoneNumberTextView = itemView.findViewById(R.id.phoneNumberTextView);
        this.onItemListener = onItemListener;
        this.avatar = itemView.findViewById(R.id.profileImage_0);
        this.person = person;
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        onItemListener.onItemClick(getAdapterPosition(), person);
    }
}