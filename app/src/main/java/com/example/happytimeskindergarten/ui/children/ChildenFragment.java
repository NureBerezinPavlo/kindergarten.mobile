package com.example.happytimeskindergarten.ui.children;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
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

import com.example.happytimeskindergarten.ui.Child;
import com.example.happytimeskindergarten.ui.ChildAdapter;
import com.example.happytimeskindergarten.ui.ChildEditActivity;
import com.example.happytimeskindergarten.R;
import com.example.happytimeskindergarten.ui.Person;
import com.example.happytimeskindergarten.ui.SignInActivity;
import com.example.happytimeskindergarten.ui.TrustedPersonAdapter;

import java.util.ArrayList;

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

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        // вырубаем тёмную тему во всём приложении
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ChildenViewModel.class);
        // TODO: Use the ViewModel

        ArrayList<Child> childrenArrayList = new ArrayList<Child>()
        {{
            add(new Child("Петро", Child.Gender.MALE));
            add(new Child("Марія", Child.Gender.FEMALE));
            add(new Child("Олександр", Child.Gender.MALE));
        }};
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