/**
 * To implement, test and deploy a Guess A Number Android App
 *
 * @author Paul Harvey (harv0116@algonquinlive.com)
 */

package com.algonquincollege.harv0116.guessanumber;

import android.app.Activity;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends Activity {

    private static int theNumber;
    private static int counter = 1;
    private static final String ABOUT_DIALOG_TAG;

    static {
        ABOUT_DIALOG_TAG = "About Dialog";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        theNumber = randomNumber();

        //Display a static text prompt to guess a number called userGuess

        Button guessButton = (Button) findViewById(R.id.guessButton);

        guessButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //Validation TEST - is the value entered a good number
                EditText userGuessText = (EditText) findViewById(R.id.userGuessText);

                int userGuess;
                try {
                    userGuess = Integer.parseInt(userGuessText.getText().toString());
                } catch (NumberFormatException e) {
                    Toast.makeText(getApplicationContext(), "Your Guess must be a number from 1 to 1000!!", Toast.LENGTH_LONG).show();
                    userGuessText.requestFocus();
                    return;
                }

                //Validation TEST - is the number between 1 and 1000

                if (userGuess >= 1 && userGuess <= 1000) {
                    // in the range we want

                    //Logic TEST - is userGuess = the Number
                    // if yes then display winning Toast Message
                    // determine what kind of win

                    if (userGuess == theNumber) {
                        if (counter <= 5) {
                            Toast.makeText(getApplicationContext(), "Superior Win! - 2 Paws UP", Toast.LENGTH_LONG).show();
                            Toast.makeText(getApplicationContext(), "Please Reset and Try Again", Toast.LENGTH_SHORT).show();

                        } else if (counter > 5 && counter <= 10) {
                            Toast.makeText(getApplicationContext(), "Excellent Win!", Toast.LENGTH_LONG).show();
                            Toast.makeText(getApplicationContext(), "Please Reset and Try Again", Toast.LENGTH_SHORT).show();

                        } else if (counter > 10) {
                            Toast.makeText(getApplicationContext(), "Please Reset!", Toast.LENGTH_LONG).show();

                        }
                    } else {

                        //Counter TEST
                        // determine if too high or too low

                        // if counter is at 11 player lost
                        //Display Toast message Please Reset

                        if (counter < 10) {
                            //test for too high
                            if (userGuess > theNumber) {
                                Toast.makeText(getApplicationContext(), "The Guess Was Too High", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getApplicationContext(), "The Guess Was Too Low", Toast.LENGTH_SHORT).show();
                            }
                            counter++;
                            userGuessText.setText("");

                        } else if (counter >= 10) {
                            Toast.makeText(getApplicationContext(), "Please Reset!", Toast.LENGTH_LONG).show();
                            counter++;
                            userGuessText.setText("");
                        }

                    }


                } else {

                    //Error Message for bad entry
                    Toast.makeText(getApplicationContext(), "Your Guess must be a number from 1 to 1000!!", Toast.LENGTH_LONG).show();
                    userGuessText.requestFocus();
                    return;
                }
            }
        });
        //Reset Button
        // if onclick reset game, pick new number

        Button resetButton = (Button) findViewById(R.id.resetButton);

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                theNumber = randomNumber();
                counter = 1;
            }
        });

        // if long click
        //  Toast message to show the number

        resetButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(getApplicationContext(), "The number is: " + theNumber, Toast.LENGTH_LONG).show();
                return true;
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //Make an About Dialog
        //Displays full name, and username.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        if (id == R.id.action_about) {
            DialogFragment newFragment = new AboutDialogFragment();
            newFragment.show(getFragmentManager(), ABOUT_DIALOG_TAG);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private int randomNumber() {

        //Generate random number between 1 and 1000 called theNumber

        Random rand = new Random();
        int randNumber = rand.nextInt(1000) + 1;
        return randNumber;
    }
}
