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
import com.example.happytimeskindergarten.ui.*;
import com.example.happytimeskindergarten.R;

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
        return inflater.inflate(R.layout.fragment_childen, container, false);
    }
    ArrayList<Child> childrenArrayList = new ArrayList<Child>();
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        // вырубаем тёмную тему во всём приложении
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ChildenViewModel.class);
        // TODO: Use the ViewModel

        childrenArrayList  = new ArrayList<Child>();


        RecyclerView childrenRecyclerView =
                getActivity().findViewById(R.id.childrenRecyclerView);

        ChildAdapter adapter = new ChildAdapter(childrenArrayList, this);
        RecyclerView.LayoutManager layoutManager =
                new GridLayoutManager(requireContext(), 1);

        childrenRecyclerView.setLayoutManager(layoutManager);
        childrenRecyclerView.setAdapter(adapter);
        Request.requestfamily.getfamily(User.getFamily_account_id()[0], User.getToken()).enqueue(new Callback<family_accountData>() {
            @Override
            public void onResponse(Call<family_accountData> call, Response<family_accountData> response) {

                User.setFamily_account(response.body());
                System.out.println(response.body().getData().getChild_profiles().length);
                childrenArrayList = new ArrayList<Child>();
                for(int i = 0; i < response.body().getData().getChild_profiles().length; i++){
                    Request.requestChildren.getChildrens(String.valueOf(response.body().getData().getChild_profiles()[i]), User.getToken()).enqueue(new Callback<ChildData>() {
                        @Override
                        public void onResponse(Call<ChildData> call, Response<ChildData> response) {
                            Child child = new Child();
                            child.setId(response.body().getData().getId());
                            child.setFullName(response.body().getData().getName());
                            child.setBirthday(response.body().getData().getBirthday());
                            child.setGender(response.body().getData().getGender() == "male" ?  Child.Gender.MALE : Child.Gender.FEMALE);
                            child.setAllergies(response.body().getData().getAllergies());
                            child.setIllnesses(response.body().getData().getIllnesses());
                            child.setImage_data(response.body().getData().getImage_data());
                            child.setGroup_name(response.body().getData().getGroup_name());
                            child.setGroup_id(response.body().getData().getGroup_id());
                            childrenArrayList.add(child);
                            adapter.loadChildren(childrenArrayList);
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
    public void onItemClick(int position, Child child)
    {
        Intent childEditIntent = new Intent(getActivity(), ChildEditActivity.class);
        //Toast.makeText(getContext(), "" + gender, Toast.LENGTH_SHORT).show();
        childEditIntent.putExtra(Child.class.getSimpleName(), child);
        startActivity(childEditIntent);
    }
}