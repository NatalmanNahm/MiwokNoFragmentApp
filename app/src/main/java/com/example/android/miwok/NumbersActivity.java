package com.example.android.miwok;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity {

    private MediaPlayer mMediaPlayer;

    private MediaPlayer.OnCompletionListener mComplitionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();
        }
    };

    private AudioManager mAudioManager;

    AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener =
            new AudioManager.OnAudioFocusChangeListener() {
                @Override
                public void onAudioFocusChange(int focusChange) {
                    if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                            focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK){
                        //Pause playback and reset player to the start of the file. that way
                        // We can play the word from the start when resume playback
                        mMediaPlayer.pause();
                        mMediaPlayer.seekTo(0);
                    } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                        //This means we gain back focus and can resume playback
                        mMediaPlayer.start();
                    } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS){
                        //Stop playback and cleanup resources
                        releaseMediaPlayer();
                    }
                }
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        //Create and setup the {@link AudioManager} to request audio focus
        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);


        //Create a string Array List of English numbers
        final ArrayList<Word> numbers = new ArrayList<Word>();

        numbers.add(new Word("one", "Lutti", R.drawable.number_one, R.raw.number_one));
        numbers.add(new Word("Two", "Otiiko", R.drawable.number_two, R.raw.number_two));
        numbers.add(new Word("Three", "Tolookosu", R.drawable.number_three, R.raw.number_three));
        numbers.add(new Word("Four", "Oyyisa", R.drawable.number_four, R.raw.number_four));
        numbers.add(new Word("Five", "Massokka", R.drawable.number_five, R.raw.number_five));
        numbers.add(new Word("Six", "Temmokka", R.drawable.number_six, R.raw.number_six));
        numbers.add(new Word("Seven","Kenekaku", R.drawable.number_seven, R.raw.number_seven));
        numbers.add(new Word("Eight", "Kawinta", R.drawable.number_eight, R.raw.number_eight));
        numbers.add(new Word("Nine", "Wo'e", R.drawable.number_nine, R.raw.number_nine));
        numbers.add(new Word("Ten", "na'aaha", R.drawable.number_ten, R.raw.number_ten));


        //Find the root view of the whole layout

        WordAdapter itemsAdapter =
                new WordAdapter(this, numbers, R.color.category_numbers);

        ListView listView = (ListView) findViewById(R.id.list);

        listView.setAdapter(itemsAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Word word = numbers.get(position);

                //Release the media player in case it something is being played and the user
                //Clicked on a new song
                releaseMediaPlayer();

                //Request audio focus for playback
                int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener,
                        //use the music Stream
                        AudioManager.STREAM_MUSIC,
                        //Request permanent focus
                        AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED){

                    //We have audio focus now

                    // Create and setup the {@link MediaPlayer} for the audio resource associated
                    // with the current word
                    mMediaPlayer = MediaPlayer.create(NumbersActivity.this, word.getmAudiosourceId());
                    mMediaPlayer.start();

                    //Release the media player resources
                    mMediaPlayer.setOnCompletionListener(mComplitionListener);

                }


            }
        });

    }


    @Override
    protected void onStop() {
        super.onStop();

        //stop playing audio file and release media player resource
        //When user leaves the app
        releaseMediaPlayer();
    }

    /**
     * Clean up the media player by releasing its resources.
     */
    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mMediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mMediaPlayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mMediaPlayer = null;

            //abandon audio focus when playback complete
            mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
        }
    }
}
