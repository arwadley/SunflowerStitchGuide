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

import com.squareup.picasso.Picasso;

import java.util.List;


public class StitchListAdapter extends RecyclerView.Adapter<StitchListAdapter.StitchViewHolder> {

        class StitchViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            private final TextView stitchItemView;
            private final ImageView stitchFinalSmall;

            private StitchViewHolder(View itemView) {
                super(itemView);
                stitchItemView = itemView.findViewById(R.id.stitch_label);
                stitchFinalSmall = itemView.findViewById(R.id.stitch_final_small);
                itemView.setOnClickListener(this);
            }
            @Override
            public void onClick(View view){
                Bundle bundle = new Bundle();
                String current = mStitches.get(getAdapterPosition()).getName();
                bundle.putString("clicked stitch name", current);
                Navigation.findNavController(view).navigate(R.id.nav_stitch_informational_view, bundle);
            }
        }

        private final LayoutInflater mInflater;
        private List<Stitch> mStitches;

        public StitchListAdapter(Context context) { mInflater = LayoutInflater.from(context); }

        @Override
        public StitchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = mInflater.inflate(R.layout.list_item_stitch, parent, false);
            return new StitchViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(StitchViewHolder holder, int position) {
            if (mStitches != null) {
                Stitch current = mStitches.get(position);
                holder.stitchItemView.setText(current.getName());
                int currentImage = current.getFinPhotoID();
                if(currentImage != 0) {
                    Picasso.get().load(currentImage).fit().into(holder.stitchFinalSmall);
                }
            } else {
                // Covers the case of data not being ready yet.
                holder.stitchItemView.setText("Did not match any stitches");
            }
        }

        public void setStitches(List<Stitch> stitches){
            mStitches = stitches;
            notifyDataSetChanged();
        }

        @Override
        public int getItemCount() {
            if (mStitches != null)
                return mStitches.size();
            else return 0;
        }
}
