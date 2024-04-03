package com.example.newsports.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.newsports.R;

public class ReplacerFragment extends Fragment {

    private static final String TAG_LOGIN_FRAGMENT = "login_fragment";
    private static final String TAG_COMMENT_FRAGMENT = "comment_fragment";
    private FrameLayout frameLayout;

    public ReplacerFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_replacer, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        frameLayout = view.findViewById(R.id.frameLayout);

        if (savedInstanceState == null) {
            showInitialFragment();
        }
    }

    private void showInitialFragment() {
        boolean isComment = requireActivity().getIntent().getBooleanExtra("isComment", false);
        if (isComment) {
            showFragment(new CommentFragment(), TAG_COMMENT_FRAGMENT);
        } else {
            showFragment(new LoginFragment(), TAG_LOGIN_FRAGMENT);
        }
    }

    private void showFragment(Fragment fragment, String tag) {
        FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        transaction.replace(frameLayout.getId(), fragment, tag);
        transaction.addToBackStack(null); // Add transaction to back stack for fragment navigation
        transaction.commit();
    }
}
