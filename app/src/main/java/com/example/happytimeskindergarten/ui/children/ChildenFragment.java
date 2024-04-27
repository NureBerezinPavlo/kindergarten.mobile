package com.example.happytimeskindergarten.ui.children;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.view.View;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.happytimeskindergarten.ui.ChildEditActivity;
import com.example.happytimeskindergarten.R;
import com.example.happytimeskindergarten.ui.SignInActivity;

public class ChildenFragment extends Fragment {

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

        LinearLayout childrenLinearLayout = getView().findViewById(R.id.childrenLinearLayout);

        int childrenQuantity = childrenLinearLayout.getChildCount();

        for (int i = 0; i < childrenQuantity; i++)
        {
            View childView = childrenLinearLayout.getChildAt(i);

            childView.setOnClickListener(new View.OnClickListener()
            {
                public void onClick(View view) {
                    Intent childEditIntent = new Intent(getActivity(), ChildEditActivity.class);
                    startActivity(childEditIntent);
                }
            });
        }

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
}