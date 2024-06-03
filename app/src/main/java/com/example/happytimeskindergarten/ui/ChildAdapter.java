package com.example.happytimeskindergarten.ui;

import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.example.happytimeskindergarten.R;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
        holder.fullNameTextView.setText(childrenArraylist.get(i).getFullName());
        holder.child = childrenArraylist.get(i);
        if (holder.child.getImage_data() != null){
            System.out.println("image data" + holder.child.getImage_data());
            holder.profileImage.setImageBitmap(Base64image.decode_image(childrenArraylist.get(i).getImage_data()));
        }
        else{
            if(childrenArraylist.get(i).getGender() == Child.Gender.FEMALE)
            {
                // Аватарка девочки по умолчанию
                Drawable drawable = ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.childphotodefault_female2);
                holder.profileImage.setImageDrawable(drawable);
            }
        }
        // !!! Здесь должна быть проверка, есть ли фото в базе данных.
        // Если фото нет, оставляем фото по умолчанию.

    }

    @Override
    public int getItemCount() { return childrenArraylist.size(); }

    public interface OnItemListener
    {
        void onItemClick(int position, Child child);
    }

    public void loadChildren(ArrayList<Child> childrenArrayList) {
        this.childrenArraylist = childrenArrayList;
        notifyDataSetChanged();
    }
}
