package com.example.happytimeskindergarten.ui.feedback;

import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.happytimeskindergarten.R;

import java.util.List;

public class FeedbackFragment extends Fragment {

    private FeedbackViewModel mViewModel;

    public static FeedbackFragment newInstance() {
        return new FeedbackFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_feedback, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(FeedbackViewModel.class);

        String[] urlsArray = {"https://www.facebook.com/", "https://www.youtube.com/",
                "https://web.telegram.org", "https://www.viber.com/ua/"};
        LinearLayout socialMediaLinearLayout = getActivity().findViewById(R.id.socialMediaLinearLayout);
        for (int i = 0; i < urlsArray.length; i++) {
            String url = urlsArray[i];
            socialMediaLinearLayout.getChildAt(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(url));
                    getContext().startActivity(intent);
                }
            });
        }
    }
}