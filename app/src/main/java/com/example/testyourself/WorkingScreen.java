package com.example.testyourself;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WorkingScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_working_screen);

        EditText answerBox = (EditText) findViewById(R.id.editTextAnswer);
        Button checkButton = (Button) findViewById(R.id.buttonCheck);
        Button backButton = (Button) findViewById(R.id.buttonBack);
        TextView questionBox = (TextView) findViewById(R.id.textViewFront);

        //Getting deck from DeckMakingPage
        Intent i = getIntent();
        Deck deck = i.getParcelableExtra("DECK2");

        //List of all random numbers used
        List<Integer> used = new ArrayList<Integer>();

        final int[] currentQuestionIndex = {randomIndex(deck)};
        int newQuestionIndex = 0;
        displayRandom(questionBox, deck, currentQuestionIndex[0]);

        //onClick to check if the answer is right then generate a new number and use that new numbers card
        checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                String answer = answerBox.getText().toString();
                check(answer, deck, currentQuestionIndex[0], used);
                used.add(currentQuestionIndex[0]);
                currentQuestionIndex[0] = randomIndex(deck);
                displayRandom(questionBox, deck, currentQuestionIndex[0]);
            }
        });

        //onClick to open the MainDeckPage again
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                openMainDeckPage();
            }
        });
    }

    //Method to open MainDeckPage activity
    public void openMainDeckPage()
    {
        Intent intent = new Intent(this, MainDeckPage.class);
        startActivity(intent);
    }

    //Method to display the current card
    public void displayRandom(TextView questionBox, Deck deck, int currentQuestionIndex)
    {
        questionBox.setText(deck.front.get(currentQuestionIndex));
    }

    //Method that checks if the given answer is right
    public void check(String answer, Deck deck, int currentQuestionIndex, List<Integer> used)
    {
        if (answer.equals(deck.back.get(currentQuestionIndex)));
        {
            Toast.makeText(getBaseContext(), "Well done you got it right :)", Toast.LENGTH_SHORT).show();
            User.xp = User.xp + 50;
            User.levelUpCheck();
        }
    }

    //Gets a random index from the list of cards
    //used from example https://www.codegrepper.com/code-examples/java/java+random+between+two+numbers
    public int randomIndex(Deck deck)
    {
        Random r = new Random();
        int l = 0;
        int h = deck.front.size();
        int result = r.nextInt(h - l) + l;

        return result;
    }
}