package com.arwapp.sittm.ui.SearchAlphabetical;

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
import com.arwapp.sittm.Stitch;
import com.arwapp.sittm.StitchListAdapter;

import java.util.List;

public class SearchAlphabeticalFragment extends Fragment {

    private SearchAlphabeticalViewModel mAlphabeticalViewModel;
    private RecyclerView mRecyclerView;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        mAlphabeticalViewModel = ViewModelProviders.of(this).get(SearchAlphabeticalViewModel.class);
        View root = inflater.inflate(R.layout.fragment_search_alphabetical, container, false);

        mRecyclerView = root.findViewById(R.id.individual_stitch_recycler);
        final StitchListAdapter adapter = new StitchListAdapter(getContext());
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemViewCacheSize(20);
        mRecyclerView.setDrawingCacheEnabled(true);
        mRecyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);


        mAlphabeticalViewModel.getStitches().observe(this, new Observer<List<Stitch>>() {
            @Override
            public void onChanged(@Nullable List<Stitch> list) {
                adapter.setStitches(list);
            }
        });
        return root;

    }


}