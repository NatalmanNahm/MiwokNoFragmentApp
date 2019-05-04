/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.miwok;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView numbers = (TextView) findViewById(R.id.numbers);
        TextView colors = (TextView) findViewById(R.id.colors);
        TextView phrases = (TextView) findViewById(R.id.phrases);
        TextView familiMembers = (TextView) findViewById(R.id.family);

            // Set a click listener on that View
            numbers.setOnClickListener(new View.OnClickListener() {
                // The code in this method will be executed when the numbers View is clicked on.
                @Override
                public void onClick(View view) {
                    Intent numbersIntent = new Intent(MainActivity.this, NumbersActivity.class);
                    startActivity(numbersIntent);
                }
            });

            colors.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent colorIntent = new Intent(MainActivity.this, ColorsActivity.class);
                    startActivity(colorIntent);
                }
            });

            phrases.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    Intent phraseIntent = new Intent(MainActivity.this, PhrasesActivity.class);
                    startActivity(phraseIntent);
                }
            });

            familiMembers.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    Intent familyIntent = new Intent(MainActivity.this, FamilyMembersActivity.class);
                    startActivity(familyIntent);
                }
            });
    }

}
