package com.example.android.miwok;


import android.widget.ImageView;

/**
 * {@link} represents a vocabulary word that the user wants to learn.
 * It contains the default translation and the miwok translation of the word
 */
public class Word {

    /** Default Translation of the word */
    private String mDefaultTranslation;

    /** Miwok Translation of the word */
    private String mMiwokTranslation;

    /** Default resource id image */
    private int mImageResourceId = NO_IMAGE_PROVIDED;

    /** Default resource id song */
    private int mAudiosourceId;

    private static final int NO_IMAGE_PROVIDED = -1;

    /** Creating the constructor to be used to create the word */

    public Word(String defaultTranslation, String miwokTranslation, int audiosourceId){
        mDefaultTranslation = defaultTranslation;
        mMiwokTranslation = miwokTranslation;
        mAudiosourceId = audiosourceId;
    }

    /** Creating the constructor to be used to create the word with image */
    public Word(String defaultTranslation, String miwokTranslation, int imageId, int audiosourceId){
        mDefaultTranslation = defaultTranslation;
        mMiwokTranslation = miwokTranslation;
        mImageResourceId = imageId;
        mAudiosourceId = audiosourceId;
    }

    /**
     * Get the default translation of the word.
     */
    public String getDefaultTranslation(){
        return mDefaultTranslation;
    }

    /**
     * Get the miwok translation of the word.
     */

    public String getMiwokTranslation(){
        return mMiwokTranslation;
    }

    /**
     * Get the image resource Id of the image
     */
    public int getmImageResourceId() {
        return mImageResourceId;
    }

    /**
     * Returns if there is an image for the word or not
     * @return
     */
    public boolean hasImage(){

        return mImageResourceId != NO_IMAGE_PROVIDED;
    }

    /**
     * Return Audio resource id for word
     */
    public int getmAudiosourceId(){
        return mAudiosourceId;
    }

    @Override
    public String toString() {
        return "Word{" +
                "mDefaultTranslation='" + mDefaultTranslation + '\'' +
                ", mMiwokTranslation='" + mMiwokTranslation + '\'' +
                ", mImageResourceId=" + mImageResourceId +
                ", mAudiosourceId=" + mAudiosourceId +
                '}';
    }
}
