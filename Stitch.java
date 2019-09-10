package com.arwapp.sittm;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


import java.util.Objects;

@Entity(tableName = "stitch_table")
public class Stitch implements Comparable<Stitch>{
    @PrimaryKey
    @NonNull
    @ColumnInfo(name="stitch_name")
    private String mName;
    @NonNull
    @ColumnInfo(name="stitch_category")
    private String mCategory;
    @ColumnInfo(name="stitch_description")
    private String mDescription;
    @ColumnInfo(name="stitch_instructions")
    private String mInstructions;
    @ColumnInfo(name="stitch_photo_instruction_ID")
    private int mPhotoInstructionID;
    @ColumnInfo(name="stitch_fin_photo_ID")
    private int mFinPhotoID;


    public Stitch(String name, String category, String description, String instructions, int photoInstructionID, int finPhotoID){
        mName = name;
        mCategory = category;
        mDescription = description;
        mInstructions = instructions;
        mPhotoInstructionID = photoInstructionID;
        mFinPhotoID = finPhotoID;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public String getCategory() {
        return mCategory;
    }

    public void setCategory(String category) {
        this.mCategory = category;
    }

    public int getPhotoInstructionID() {
        return mPhotoInstructionID;
    }

    public void setPhotoInstructionID(int mPhotoInstructionID) {
        this.mPhotoInstructionID = mPhotoInstructionID;
    }

    public int getFinPhotoID() {
        return mFinPhotoID;
    }

    public void setFinPhotoID(int mFinPhotoID) { this.mFinPhotoID = mFinPhotoID; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Stitch stitch = (Stitch) o;
        return getName().equals(stitch.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public String getInstructions() {
        return mInstructions;
    }

    public void setInstructions(String instructions) {
        mInstructions = instructions;
    }

    @Override
    public int compareTo(Stitch stitch){
        return this.mName.compareToIgnoreCase(stitch.getName());
    }
}
