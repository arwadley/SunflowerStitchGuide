package com.arwapp.sittm.ui.SearchCategory;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.arwapp.sittm.R;
import com.arwapp.sittm.Stitch;
import com.arwapp.sittm.StitchListAdapter;

import java.util.List;

public class StitchListByCategoryFragment extends Fragment {

    private SearchCategoryViewModel mSearchCategoryViewModel;
    private RecyclerView mRecyclerView;
    private String mCategory;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mSearchCategoryViewModel = ViewModelProviders.of(this).get(SearchCategoryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_stitch_list_by_category, container, false);

        mRecyclerView = root.findViewById(R.id.stitch_list_category_recycler);
        final StitchListAdapter adapter = new StitchListAdapter(getContext());
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        mCategory = getArguments().getString("clicked category");
        mSearchCategoryViewModel.getStitchesByCategory(mCategory).observe(this, new Observer<List<Stitch>>() {
            @Override
            public void onChanged(@Nullable List<Stitch> list) {
                    adapter.setStitches(list);
            }
        });

        return root;
    }
}
