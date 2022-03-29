package com.example.testyourself;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DeckMakingPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deck_making_page);

        EditText newFront = (EditText) findViewById(R.id.editTextNewFront);
        EditText newBack = (EditText) findViewById(R.id.editTextNewback);

        ListView listView = (ListView) findViewById(R.id.listView1);
        Button createButton = (Button) findViewById(R.id.buttonNewCard);
        Button studyButton = (Button) findViewById(R.id.buttonStudyCards);

        //Getting deck passed from MainDeckPage
        Intent i = getIntent();
        Deck deck = i.getParcelableExtra("DECK");

        //Setting up array list
        List<String> display = new ArrayList<String>();
        display = deck.getFront();

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getBaseContext(), android.R.layout.simple_list_item_1, display);
        listView.setAdapter(arrayAdapter);

        //onClick for adding new card to deck
        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                if (newFront.getText().toString().trim().length() > 0 && newBack.getText().toString().trim().length() > 0)
                {
                    String newFrontText = newFront.getText().toString();
                    String newBackText = newBack.getText().toString();
                    createCard(deck, arrayAdapter, newFrontText, newBackText);
                }
                else
                {
                    Toast.makeText(getBaseContext(), "Please enter a front and back text", Toast.LENGTH_LONG).show();
                }
            }
        });

        //onClick for opening the study activity
        studyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                openStudyActivity(deck);
            }
        });

    }

    //Method that adds new card to deck and to array adapter
    public void createCard(Deck deck, ArrayAdapter<String> arrayAdapter, String front, String back)
    {
        deck.front.add(front);
        deck.back.add(back);

        arrayAdapter.notifyDataSetChanged();
    }

    //Method for opening the study activity
    public void openStudyActivity(Deck deck)
    {
        Intent intent = new Intent(this, WorkingScreen.class);
        intent.putExtra("DECK2", (Parcelable) deck);
        startActivity(intent);
    }
}