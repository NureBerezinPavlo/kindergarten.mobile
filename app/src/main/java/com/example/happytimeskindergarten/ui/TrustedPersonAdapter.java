package com.example.happytimeskindergarten.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.happytimeskindergarten.R;

import java.util.ArrayList;
import java.util.List;

public class TrustedPersonAdapter extends RecyclerView.Adapter<TrustedPersonViewHolder>
{
    public List<View> itsLayouts;
    public ArrayList<Person> personsArraylist;
    OnItemListener onItemListener;

    public TrustedPersonAdapter(ArrayList<Person> personsArraylist, OnItemListener onItemListener)
    {
        itsLayouts = new ArrayList<>();
        this.personsArraylist = personsArraylist;
        this.onItemListener = onItemListener;
    }

    @NonNull
    @Override
    public TrustedPersonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itsLayout = layoutInflater.inflate(R.layout.person_data_block, parent, false);
        itsLayouts.add(itsLayout);

        View photoAndFullnameView = itsLayout.findViewById(R.id.photoAndFullname);
        View emailTextView = itsLayout.findViewById(R.id.emailTextView);
        View phoneNumberTextView = itsLayout.findViewById(R.id.phoneNumberTextView);

        int totalHeight = measureView(photoAndFullnameView)
                + measureView(emailTextView)
                + measureView(phoneNumberTextView);

        ViewGroup.LayoutParams layoutParams = itsLayout.getLayoutParams();
        layoutParams.height = totalHeight;


        return new TrustedPersonViewHolder(itsLayout, onItemListener);
    }
    private int measureView(View view) {
        view.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        return view.getMeasuredHeight() + view.getPaddingTop() + view.getPaddingBottom();
    }
    @Override
    public void onBindViewHolder(@NonNull TrustedPersonViewHolder holder, int i)
    {
        holder.fullNameTextView.setText(personsArraylist.get(i).fullName);
        holder.emailTextView.setText(personsArraylist.get(i).email);
        holder.phoneNumberTextView.setText(personsArraylist.get(i).phoneNumber);
    }

    @Override
    public int getItemCount() { return personsArraylist.size(); }

    public interface OnItemListener
    {
        void onItemClick(int position, String fullName, String email, String phoneNumber);
    }

    public void loadTrustedPersons(ArrayList<Person> personsArraylist) {
        this.personsArraylist = personsArraylist;
        notifyDataSetChanged();
    }
}
