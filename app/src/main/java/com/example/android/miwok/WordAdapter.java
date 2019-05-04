package com.example.android.miwok;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

public class WordAdapter extends ArrayAdapter<Word> {

    /** Resource ID for background color */
    private int mColorResourceId;

    public WordAdapter(Activity context, ArrayList<Word> numbers, int colorResourceId){
        super (context,0,numbers);
        mColorResourceId = colorResourceId;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        Word currentWord = getItem(position);

        // Find the TextView in the list_item.xml layout with the ID default text view
        TextView defaultTextView = (TextView) listItemView.findViewById(R.id.default_text_view);

        // Get the default translation from the current WordAdapter object and
        // set this text on the default TextView
        defaultTextView.setText(currentWord.getDefaultTranslation());

        // Find the TextView in the list_item.xml layout with the ID miwok text view
        TextView miwokTextView = (TextView) listItemView.findViewById(R.id.miwok_text_view);

        // Get the default translation from the current WordAdapter object and
        // set this text on the miwok TextView
        miwokTextView.setText(currentWord.getMiwokTranslation());

        // Find the image in the list_item.xml layout with the ID default image view
        ImageView imageView = (ImageView) listItemView.findViewById(R.id.image);

        if (currentWord.hasImage()){
            //Set the imageview to the image resource specified in the current word
            imageView.setImageResource(currentWord.getmImageResourceId());

            //Set the visibility of the image to be visible
            imageView.setVisibility(View.VISIBLE);
        }else {
            //set the visibility of the ImageView to be gone
            imageView.setVisibility(View.GONE);
        }

        //Set the theme color for the list item
        View textContainer = listItemView.findViewById(R.id.container);

        //Find the color that the resource ID maps to
        int color = ContextCompat.getColor(getContext(), mColorResourceId);

        //Set he background color for the text container View
        textContainer.setBackgroundColor(color);



        return listItemView;


    }
}
