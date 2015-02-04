package com.desitum.androidscrambler;

import android.app.Activity;
import android.os.Bundle;
import android.transition.Explode;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

/**
 * Created by Jason on 2/3/2015.
 */
public class ScrambledActivity extends Activity {

    //Declares the UserInterface Objects
    private TextView scrambledTextField;

    //Declares the class data members
    private String answer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrambled);

        //Instantiates the UserInterace Objects
        scrambledTextField = (TextView)findViewById(R.id.scrambledTextField);

        //Gets the Intent Extra sent from the main class
        Bundle extras = getIntent().getExtras();
        answer = extras.getString("scrambledAnswer").toString();

        //Sets the Text Field to the Scrambled Message
        scrambledTextField.setText(answer);

        //Set Exit Animation
        getWindow().setExitTransition(new Explode());

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
}

