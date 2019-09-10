package com.arwapp.sittm.ui.StitchInformationalView;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.arwapp.sittm.R;
import com.arwapp.sittm.Stitch;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class StitchInformationalViewFragment extends Fragment {

    private StitchInformationalViewModel stitchInformationalViewModel;
    private TextView mNameTextView;
    private TextView mInstructionTextView;
    private TextView mInstructionTitle;
    private TextView mDescription;
    private TextView mDescriptionTitle;
    private ImageView mFininishedStitchImageView;
    private ImageView mInstructionCollage;
    private Stitch mStitch;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        stitchInformationalViewModel =
                ViewModelProviders.of(this).get(StitchInformationalViewModel.class);

        View root = inflater.inflate(R.layout.fragment_stitch_informational_view, container, false);
        mNameTextView = (TextView)root.findViewById(R.id.stitch_title_iv);

        mInstructionTextView = root.findViewById(R.id.stitch_instructions);

        mInstructionTitle = root.findViewById(R.id.stitch_instruction_title);

        mDescription = root.findViewById(R.id.stitch_decsription);

        mDescriptionTitle = root.findViewById(R.id.stitch_description_title);

        mInstructionCollage = root.findViewById(R.id.instructions_collage);

        mFininishedStitchImageView=root.findViewById(R.id.stitch_final_iv);
        String name = getArguments().getString("clicked stitch name");
        stitchInformationalViewModel.findStitchWithName(name).observe(this, new Observer<Stitch>() {
                    @Override
                    public void onChanged(@Nullable Stitch stitch) {
                        mStitch = stitch;
                        mNameTextView.setText(mStitch.getName());
                        mDescription.setText(mStitch.getDescription());
                        mInstructionTextView.setText(mStitch.getInstructions());
                        if(mStitch.getFinPhotoID()!=0){
                            Picasso.get().load(mStitch.getFinPhotoID()).fit().into(mFininishedStitchImageView);
                        }
                        if(mStitch.getPhotoInstructionID() != 0){
                            Picasso.get().load(mStitch.getPhotoInstructionID()).fit().into(mInstructionCollage);
                        }
                    }
                });

        return root;
    }
}