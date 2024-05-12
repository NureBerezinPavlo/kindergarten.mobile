package com.example.happytimeskindergarten.ui.children;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
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
import android.widget.LinearLayout;
import android.widget.Toast;
import com.example.happytimeskindergarten.ui.*;
import com.example.happytimeskindergarten.ui.Child;
import com.example.happytimeskindergarten.ui.ChildAdapter;
import com.example.happytimeskindergarten.ui.ChildEditActivity;
import com.example.happytimeskindergarten.R;
import com.example.happytimeskindergarten.ui.SignInActivity;
import com.example.happytimeskindergarten.ui.TrustedPersonAdapter;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChildenFragment extends Fragment implements ChildAdapter.OnItemListener {

    private ChildenViewModel mViewModel;

    public static ChildenFragment newInstance() {
        return new ChildenFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        Request.requestfamily.getfamily(User.getFamily_account_id()[0], User.getToken()).enqueue(new Callback<family_accountData>() {
            @Override
            public void onResponse(Call<family_accountData> call, Response<family_accountData> response) {

                User.setFamily_account(response.body());
                System.out.println(response.body().getData().getChild_profiles().length);
                for(int i = 0; i < response.body().getData().getChild_profiles().length; i++){
                    Request.requestChildren.getChildrens(String.valueOf(response.body().getData().getChild_profiles()[i]), User.getToken()).enqueue(new Callback<ChildData>() {
                        @Override
                        public void onResponse(Call<ChildData> call1, Response<ChildData> response1) {
                            childrenArrayList.add(new Child(response1.body().getData().getName(), response1.body().getData().getGender() == "male" ? Child.Gender.MALE : Child.Gender.FEMALE));
                        }

                        @Override
                        public void onFailure(Call<ChildData> call1, Throwable t1) {

                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<family_accountData> call, Throwable t) {
                Log.e("Error","Errror",t);
                System.out.println("Error");
            }
        });
        return inflater.inflate(R.layout.fragment_childen, container, false);
    }
    ArrayList<Child> childrenArrayList = new ArrayList<Child>()
    {{
        add(new Child("Петро", Child.Gender.MALE));
        add(new Child("Марія", Child.Gender.FEMALE));
        add(new Child("Олександр", Child.Gender.MALE));
    }};
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        // вырубаем тёмную тему во всём приложении
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ChildenViewModel.class);
        // TODO: Use the ViewModel



        RecyclerView childrenRecyclerView =
                getActivity().findViewById(R.id.childrenRecyclerView);

        ChildAdapter adapter = new ChildAdapter(childrenArrayList, this);
        RecyclerView.LayoutManager layoutManager =
                new GridLayoutManager(requireContext(), 1);

        childrenRecyclerView.setLayoutManager(layoutManager);
        childrenRecyclerView.setAdapter(adapter);

        View signOutButton = getView().findViewById(R.id.signOutButton);
        signOutButton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), SignInActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onItemClick(int position, String fullName, Child.Gender gender)
    {
        Intent childEditIntent = new Intent(getActivity(), ChildEditActivity.class);
        childEditIntent.putExtra("full_name", fullName);
        //Toast.makeText(getContext(), "" + gender, Toast.LENGTH_SHORT).show();
        childEditIntent.putExtra("gender", gender.toString());
        startActivity(childEditIntent);
    }
}