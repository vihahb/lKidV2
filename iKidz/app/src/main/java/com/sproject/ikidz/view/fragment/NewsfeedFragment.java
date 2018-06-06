package com.sproject.ikidz.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sproject.ikidz.R;

public class NewsfeedFragment extends Fragment {

    public static NewsfeedFragment newInstance(){
        Bundle args = new Bundle();
        NewsfeedFragment fragment = new NewsfeedFragment();
        fragment.setArguments(args);
        switch (1){
            default:
                break;
        }
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_newsfeed, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
