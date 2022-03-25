package com.example.testyourself;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public String selection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Creating an onClickListener for the button that will open the main page of the program
        Button button = (Button) findViewById(R.id.buttonStart);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMainDeckPage();
            }
        });

        //Buttons for profile picture selection
        View.OnClickListener radioGroupClick = new View.OnClickListener() {
            public void onClick(View view) {
                RadioButton rb = (RadioButton) view;

                selection = rb.getText().toString();
                Toast.makeText(getBaseContext(), selection, Toast.LENGTH_SHORT).show();
            }
        };

        RadioButton rbCat = (RadioButton) findViewById(R.id.radioButtonCat);
        rbCat.setOnClickListener(radioGroupClick);

        RadioButton rbBanana = (RadioButton) findViewById(R.id.radioButtonBanana);
        rbBanana.setOnClickListener(radioGroupClick);

        RadioButton rbBrain = (RadioButton) findViewById(R.id.radioButtonBrain);
        rbBrain.setOnClickListener(radioGroupClick);


    }

    //This method will take the text from the username box and will pass it to the main
    //program page when it also opens it
    public void openMainDeckPage(){
        //Getting text from username textbox
        EditText editText = (EditText) findViewById(R.id.editTextUsername);
        String username = editText.getText().toString();

        //Populating the user class
        User.username = username;
        User.xp = 0;
        User.level = 1;

        //Checking if there was a username input
        if(editText.getText().toString().trim().length() > 0)
        {
            if(selection != null)
            {
                //Opening new activity
                Intent intent = new Intent(this, MainDeckPage.class);
                startActivity(intent);
                User.icon = selection;
            }
            else
            {
                Toast.makeText(getBaseContext(), "Please select a profile picture", Toast.LENGTH_LONG).show();
            }
        }
        else
        {
            Toast.makeText(getBaseContext(), "Please enter a username", Toast.LENGTH_LONG).show();
        }
    }
}