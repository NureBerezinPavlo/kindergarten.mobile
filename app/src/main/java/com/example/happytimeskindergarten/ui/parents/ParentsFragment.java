package com.example.happytimeskindergarten.ui.parents;

import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.happytimeskindergarten.R;
import com.example.happytimeskindergarten.ui.ChildEditActivity;
import com.example.happytimeskindergarten.ui.ParentEditActivity;

public class ParentsFragment extends Fragment {

    private ParentsViewModel mViewModel;

    public static ParentsFragment newInstance() {
        return new ParentsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_parents, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ParentsViewModel.class);

        View parentEditButton = getView().findViewById(R.id.parentEditButton);
        parentEditButton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {
                Intent childEditIntent = new Intent(getActivity(), ParentEditActivity.class);
                startActivity(childEditIntent);
            }
        });

        View trustedPersonsEditButton = getView().findViewById(R.id.trustedPersonsEditButton);
        trustedPersonsEditButton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {/*
                View addButton = getView().findViewById(R.id.addTrustedPersonButton);
                addButton.setVisibility(View.VISIBLE);

                View bottomMenu = getActivity().findViewById(R.id.nav_view);
                bottomMenu.setVisibility(View.GONE);

                View parentContainer = getView().findViewById(R.id.parentContainer);
                parentContainer.setVisibility(View.GONE);

                View parentEditButton = getView().findViewById(R.id.parentEditButton);
                parentEditButton.setVisibility(View.GONE);*/
            }
        });
    }

}