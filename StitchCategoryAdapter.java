package com.arwapp.sittm;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class StitchCategoryAdapter extends RecyclerView.Adapter<StitchCategoryAdapter.StitchCategoryViewHolder> {

    class StitchCategoryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final TextView stitchCategoryView;

        private StitchCategoryViewHolder(View itemView) {
            super(itemView);
            stitchCategoryView = itemView.findViewById(R.id.category_label);
            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View view){
            Bundle bundle = new Bundle();
            String current = mCategories.get(getAdapterPosition());
            bundle.putString("clicked category", current);
                    Navigation.findNavController(view).navigate(R.id.nav_list_by_category, bundle);
        }
    }


    private final LayoutInflater mInflater;
    private List<String> mCategories;
    private String mCurrent;


    public StitchCategoryAdapter(Context context) { mInflater = LayoutInflater.from(context); }


    @Override
    public StitchCategoryAdapter.StitchCategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.list_item_category, parent, false);
        return new StitchCategoryAdapter.StitchCategoryViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(StitchCategoryAdapter.StitchCategoryViewHolder holder, int position) {
        if (mCategories != null) {
            mCurrent = mCategories.get(position);
            holder.stitchCategoryView.setText(mCurrent);
        } else {
            // Covers the case of data not being ready yet.
            holder.stitchCategoryView.setText("Did not match any categories");
        }
    }

    public void setCategories(List<String> categories){
        mCategories = categories;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (mCategories != null)
            return mCategories.size();
        else return 0;
    }
}
