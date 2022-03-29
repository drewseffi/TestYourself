package com.example.testyourself;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

//This is the class for the popup
//Help was used from this online tutorial https://www.youtube.com/watch?v=-iLG0oucUI4&ab_channel=MarcoWagner
public class Popup extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup);
    }
}