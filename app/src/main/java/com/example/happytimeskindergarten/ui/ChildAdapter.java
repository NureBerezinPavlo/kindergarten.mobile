package com.example.happytimeskindergarten.ui;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.example.happytimeskindergarten.R;
import java.util.ArrayList;
import java.util.List;

public class ChildAdapter extends RecyclerView.Adapter<ChildViewHolder>
{
    public List<View> itsLayouts;
    public ArrayList<Child> childrenArraylist;
    ChildAdapter.OnItemListener onItemListener;

    public ChildAdapter(ArrayList<Child> childrenArraylist, ChildAdapter.OnItemListener onItemListener)
    {
        itsLayouts = new ArrayList<>();
        this.childrenArraylist = childrenArraylist;
        this.onItemListener = onItemListener;
    }

    @NonNull
    @Override
    public ChildViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itsLayout = layoutInflater.inflate(R.layout.child_block, parent, false);
        itsLayouts.add(itsLayout);

        ViewGroup.LayoutParams layoutParams = itsLayout.getLayoutParams();
        layoutParams.height = measureView(itsLayout);

        return new ChildViewHolder(itsLayout, onItemListener, childrenArraylist.get(viewType));
    }
    private int measureView(View view) {
        view.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        return view.getMeasuredHeight() + view.getPaddingTop() + view.getPaddingBottom();
    }
    @Override
    public void onBindViewHolder(@NonNull ChildViewHolder holder, int i)
    {
        holder.fullNameTextView.setText(childrenArraylist.get(i).fullName);
        holder.child = childrenArraylist.get(i);

        // !!! Здесь должна быть проверка, есть ли фото в базе данных.
        // Если фото нет, оставляем фото по умолчанию.
        if(childrenArraylist.get(i).gender == Child.Gender.FEMALE)
        {
            // Аватарка девочки по умолчанию
            Drawable drawable = ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.childphotodefault_female2);
            holder.profileImage.setImageDrawable(drawable);
        }
    }

    @Override
    public int getItemCount() { return childrenArraylist.size(); }

    public interface OnItemListener
    {
        void onItemClick(int position, String fullName, Child.Gender gender);
    }
}
