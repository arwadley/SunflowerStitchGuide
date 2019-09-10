package com.arwapp.sittm.ui.home;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;

import com.arwapp.sittm.R;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    private TextView mTitleTextView;
    private TextView mCategoryTextView;
    private Button mCategoryButton;
    private TextView mAlphabeticalTextView;
    private Button mAlphabeticalButton;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        mTitleTextView = root.findViewById(R.id.home_title);
        mCategoryTextView= root.findViewById(R.id.home_search_category);
        mCategoryButton= root.findViewById(R.id.search_category_button);
        mCategoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.nav_searchByCategory);
            }
        });
        mAlphabeticalTextView = root.findViewById(R.id.home_search_alphabetical);
        mAlphabeticalButton = root.findViewById(R.id.search_alphabetical_button);
        mAlphabeticalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.nav_searchAlphabetical);
            }
        });
        return root;
    }
}