package com.arwapp.sittm.ui.SearchCategory;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.arwapp.sittm.R;
import com.arwapp.sittm.StitchCategoryAdapter;

import java.util.ArrayList;
import java.util.List;

public class SearchCategoryFragment extends Fragment {

    private SearchCategoryViewModel mSearchCategoryViewModel;
    private RecyclerView mRecyclerView;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mSearchCategoryViewModel = ViewModelProviders.of(this).get(SearchCategoryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_search_category, container, false);

        mRecyclerView = root.findViewById(R.id.category_recycler);
        final StitchCategoryAdapter adapter = new StitchCategoryAdapter(getContext());
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        mSearchCategoryViewModel.findAllCategories().observe(this, new Observer<List<String>>() {
            @Override
            public void onChanged(@Nullable List<String> list) {
                List<String> categories = new ArrayList<>();
                for(String each: list){
                    if(!categories.contains(each)){
                        categories.add(each);
                    }
                }
                adapter.setCategories(categories);
            }
        });

        return root;
    }
}