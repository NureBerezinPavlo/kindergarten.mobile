package com.example.happytimeskindergarten.ui;

import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.happytimeskindergarten.R;

public class TrustedPersonViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
{
    public final TextView fullNameTextView, emailTextView, phoneNumberTextView;
    private final TrustedPersonAdapter.OnItemListener onItemListener;

    public TrustedPersonViewHolder(@NonNull View itemView, TrustedPersonAdapter.OnItemListener onItemListener) {
        super(itemView);
        fullNameTextView = itemView.findViewById(R.id.fullNameTextView);
        emailTextView = itemView.findViewById(R.id.emailTextView);
        phoneNumberTextView = itemView.findViewById(R.id.phoneNumberTextView);
        this.onItemListener = onItemListener;
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        onItemListener.onItemClick(getAdapterPosition(),
                (String)fullNameTextView.getText(),
                (String)emailTextView.getText(),
                (String)phoneNumberTextView.getText());
    }
}