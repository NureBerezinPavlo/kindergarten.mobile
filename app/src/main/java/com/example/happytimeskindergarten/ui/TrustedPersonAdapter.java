package com.example.happytimeskindergarten.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.happytimeskindergarten.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

        return new TrustedPersonViewHolder(itsLayout, onItemListener, personsArraylist.get(viewType));
    }

    @Override
    public void onBindViewHolder(@NonNull TrustedPersonViewHolder holder, int i)
    {
        holder.person = personsArraylist.get(i);
        holder.fullNameTextView.setText(personsArraylist.get(i).getFullName());
        holder.emailTextView.setText(personsArraylist.get(i).getEmail());
        holder.phoneNumberTextView.setText(personsArraylist.get(i).getPhoneNumber());
        Request.requestTrustedPerson.getTrustedPerson(String.valueOf(holder.person.getId()), User.getToken()).enqueue(new Callback<TrustedPersonData>() {
            @Override
            public void onResponse(Call<TrustedPersonData> call, Response<TrustedPersonData> response) {
                if(response.body().getData().getImage_data() != null){
                    holder.avatar.setImageBitmap(Base64image.decode_image(response.body().getData().getImage_data()));
                }
            }

            @Override
            public void onFailure(Call<TrustedPersonData> call, Throwable t) {

            }
        });

    }

    @Override
    public int getItemCount() { return personsArraylist.size(); }

    public interface OnItemListener
    {
        void onItemClick(int position, Person person);
    }

    public void loadTrustedPersons(ArrayList<Person> personsArraylist) {
        this.personsArraylist = personsArraylist;
        notifyDataSetChanged();
    }
}
