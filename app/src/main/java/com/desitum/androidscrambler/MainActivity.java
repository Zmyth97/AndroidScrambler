package com.desitum.androidscrambler;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.transition.Explode;
import android.transition.Fade;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;


public class MainActivity extends Activity {

    //Declares the UserInterface Objects
    private Button scrambleButton;
    private EditText userInputField;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS); //Part of transition animation
        setContentView(R.layout.activity_main); //Sets up the layout as defined in the xml

        //Instantiates the UserInterface Objects
        scrambleButton = (Button)findViewById(R.id.scrambleButton);
        userInputField = (EditText)findViewById(R.id.userInputField);

        //Sets up the Button Listener
        setupListeners();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Scrambles Each Word Given to it
     * @param input the word to be scrambled
     * @return the word once it is scrambled
     */
    private String scramble(String input)
    {
        String part1 = "";
        String part2 = "";
        String part3 = "";

        if(input.length() > 2)
        {
            part1 = input.substring(0, 3);
            if(input.length() > 6)
            {
                part2 = input.substring(4, 7);
                if(input.length() > 10)
                {
                    part3 = input.substring(8, 11);
                }
            }
            input = part3 + part1 + part2;
        }
        else
        {
            part1 = input;
            input = part1;
        }


        return input;

    }

    /**
     * Takes the UserInput, divides it into individual words, scrambles them, and puts them back together.
     * @return The scrambled message
     */
    private CharSequence scrambler()
    {
        CharSequence scrambledMessage = userInputField.getText();
        CharSequence finalMessage = " ";

        if(scrambledMessage != null && scrambledMessage.length() >= 3 ) {
            for (int count = 0; count < TextUtils.split(scrambledMessage.toString(), " ").length; count++) {
                String[] words = TextUtils.split(scrambledMessage.toString(), " ");

                System.out.println(scrambledMessage + words[count]);
                finalMessage = finalMessage + " " + scramble(words[count]);
            }
        }
        else
        {
            finalMessage = " You Need to Enter a Message To Scramble";
        }
        System.out.println(finalMessage);
        return finalMessage;
    }

    /**
     * Sets up the button listener and page transition animation
     */
    private void setupListeners()
    {
        scrambleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View currentView) {
                getWindow().setExitTransition(new Explode());
                Intent myIntent = new Intent(currentView.getContext(), ScrambledActivity.class);
                System.out.println("Sent: " + scrambler());
                myIntent.putExtra("scrambledAnswer", scrambler());
                startActivityForResult(myIntent, 0);
            }
        });
    }
}
