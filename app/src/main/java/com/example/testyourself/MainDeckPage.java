package com.example.testyourself;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainDeckPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_deck_page);

        //Use user object to display username
        TextView usernameDisplay = (TextView) findViewById(R.id.textViewUsername);
        usernameDisplay.setText(User.username);

        //Displaying the user xp and level
        TextView xpDisplay = (TextView) findViewById(R.id.textViewUserXP);
        xpDisplay.setText("Current XP: " + Integer.toString(User.xp));

        TextView levelDisplay = (TextView) findViewById(R.id.textViewLevel);
        levelDisplay.setText("Current Level: " + Integer.toString(User.level));

        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBarXp);
        progressBar.setMax(100);
        progressBar.setProgress(User.xp);

        //Displaying user icon
        ImageView icon = (ImageView) findViewById(R.id.imageViewMainIcon);
        if(User.icon.equals("Cat"))
        {
            icon.setImageResource(R.drawable.cat);
        }
        else if(User.icon.equals("Banana"))
        {
            icon.setImageResource(R.drawable.banana);
        }
        else if(User.icon.equals("Brain"))
        {
            icon.setImageResource(R.drawable.brain);
        }

        //Creating a list of all decks of cards that are being stored
        List<Deck> deckList = new ArrayList<Deck>();
        List<String> deckNames = new ArrayList<String>();

        //Populating list view
        ListView listView = (ListView) findViewById(R.id.listView);
        Button createButton = (Button) findViewById(R.id.buttonCreateDeck);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, deckNames);
        listView.setAdapter(arrayAdapter);

        //Listener for create new deck button
        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addDeck(deckList, arrayAdapter);
            }
        });

        //Adding on click listener for list view to access decks
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Deck temp = new Deck();
                temp = deckList.get(i);

                openDeckCreatePage(temp);
            }
        });
    }

    //Method for creating a new deck
    public void addDeck(List<Deck> deckList, ArrayAdapter<String> arrayAdapter) {
        EditText editText = (EditText) findViewById(R.id.editTextDeckName);
        String name = editText.getText().toString();

        for (Deck deck : deckList)
        {
            if(deck.name != name)
            {
                Toast.makeText(getApplicationContext(), "Gunga ginga", Toast.LENGTH_SHORT).show();
            }
        }

        //Setting variables for the deck display
        Deck temp = new Deck();
        temp.name = name;
        deckList.add(temp);
        arrayAdapter.add(temp.name);
        editText.setText("");
    }

    //Method for opening the deck customisation screen
    public void openDeckCreatePage(Deck deck){
        Intent intent = new Intent(this, DeckMakingPage.class);
        intent.putExtra("DECK", (Parcelable) deck);
        startActivity(intent);

    }
}