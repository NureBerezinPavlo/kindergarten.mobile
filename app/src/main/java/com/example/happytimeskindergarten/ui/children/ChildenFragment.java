package com.example.happytimeskindergarten.ui.children;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.lifecycle.ViewModelProvider;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.View;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.happytimeskindergarten.ui.*;
import com.example.happytimeskindergarten.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChildenFragment extends Fragment implements ChildAdapter.OnItemListener {

    private ChildenViewModel mViewModel;
    private RecyclerView childrenRecyclerView;
    private int selectedChildId;
    ArrayList<Child> childrenArrayList = new ArrayList<Child>();

    public static ChildenFragment newInstance() {
        return new ChildenFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_childen, container, false);
    }

    public void UpdateRecyclerView(){
        childrenRecyclerView = getActivity().findViewById(R.id.childrenRecyclerView);

        ChildAdapter adapter = new ChildAdapter(childrenArrayList, this);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(requireContext(), 1);

        childrenRecyclerView.setLayoutManager(layoutManager);
        childrenRecyclerView.setAdapter(adapter);

        Request.requestfamily.getfamily(User.getFamily_account_id()[0], User.getToken()).enqueue(new Callback<family_accountData>() {
            @Override
            public void onResponse(Call<family_accountData> call, Response<family_accountData> response) {
                if (response.isSuccessful()) {
                    User.setFamily_account(response.body());
                    for (int i = 0; i < response.body().getData().getChild_profiles().length; i++) {
                        String childProfileId = String.valueOf(response.body().getData().getChild_profiles()[i]);
                        Request.requestChildren.getChildrens(childProfileId, User.getToken()).enqueue(new Callback<ChildData>() {
                            @Override
                            public void onResponse(Call<ChildData> call, Response<ChildData> response) {
                                if (response.isSuccessful()) {
                                    ChildData.Data childData = response.body().getData();
                                    Child child = new Child();
                                    child.setId(childData.getId());
                                    child.setFullName(childData.getName());
                                    child.setBirthday(childData.getBirthday());
                                    child.setGender(childData.getGender().equals("male") ? Child.Gender.MALE : Child.Gender.FEMALE);
                                    child.setAllergies(childData.getAllergies());
                                    child.setIllnesses(childData.getIllnesses());
                                    child.setImage_data(childData.getImage_data());
                                    child.setGroup_name(childData.getGroup_name());
                                    child.setGroup_id(childData.getGroup_id());
                                    childrenArrayList.add(child);
                                    adapter.notifyDataSetChanged();
                                } else {
                                    Log.e("RequestError", "Failed to fetch child data: " + response.message());
                                }
                            }

                            @Override
                            public void onFailure(Call<ChildData> call, Throwable t) {
                                Log.e("RequestError", "Failed to fetch child data", t);
                            }
                        });
                    }
                } else {
                    Log.e("RequestError", "Failed to fetch family data: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<family_accountData> call, Throwable t) {
                Log.e("RequestError", "Failed to fetch family data", t);
            }
        });
    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        // вырубаем тёмную тему во всём приложении
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ChildenViewModel.class);

        childrenArrayList = new ArrayList<>();
        UpdateRecyclerView();

        View signOutButton = getView().findViewById(R.id.signOutButton);
        signOutButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), SignInActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

                startActivity(intent);
            }
        });

    }

    @Override
    public void onItemClick(int position, Child child) {
        selectedChildId = position;
        Intent childEditIntent = new Intent(getActivity(), ChildEditActivity.class);
        //Toast.makeText(getContext(), "" + gender, Toast.LENGTH_SHORT).show();
        childEditIntent.putExtra(Child.class.getSimpleName(), child);
        startActivityForResult(childEditIntent, 1);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (selectedChildId == -1) return;
        childrenArrayList = new ArrayList<Child>();
        UpdateRecyclerView();
    }

}