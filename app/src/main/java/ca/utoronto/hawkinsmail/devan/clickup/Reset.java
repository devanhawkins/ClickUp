package ca.utoronto.hawkinsmail.devan.clickup;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;


public class Reset extends ActionBarActivity {
    //Message = high score.
    public final static String EXTRA_MESSAGE = "ca.utoronto.hawkinsmail.devan.clickup.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset);

        //Get intent extra message.
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);//main
        String message2 = intent.getStringExtra(MainActivity.EXTRA_MESSAGE2);//high score

        //Set the main number.
        //TextView textView = new TextView(this);
        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setText(message);

        //Update high score, if necessary.
        TextView textView3 = (TextView) findViewById(R.id.textView3);
        /* String currHigh = textView3.getText().toString();
        int curr = Integer.parseInt(currHigh); */
        int curr = Integer.parseInt(message2);
        int latest = Integer.parseInt(message);
        if(latest > curr){
            String newHigh = Integer.toString(latest);
            textView3.setText(newHigh);
        }else{
            textView3.setText(message2);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_reset, menu);
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

    public void restartGame(View view){
        //Gets the high score value to send as extra.
        TextView tv3 = (TextView) findViewById(R.id.textView3);
        String message = tv3.getText().toString();

        //Returns to the MainActivity activity to replay the game.
        Intent intent = new Intent(this, TimedActivity.class); //old: MainActivity
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
        finish();
    }

    public void saveHighScore(View view){
        //Saves the high score to the Key-Value Sets to be reused when the app is reopened.

        //Get the current high score value
        TextView tv3 = (TextView) findViewById(R.id.textView3);
        String message;
        message = tv3.getText().toString();
        final int highScore = Integer.parseInt(message);

        TextView tv = (TextView) findViewById(R.id.textView);
        String currScore;
        currScore = tv.getText().toString();
        final int curr = Integer.parseInt(currScore);

        //Check if high score is still the highest.
        //Alert the User of the save.
        if(highScore <= curr){
            Toast.makeText(Reset.this, "Save Completed", Toast.LENGTH_SHORT).show();
            message = currScore;
            tv3.setText(currScore);
        }else{
            Toast.makeText(Reset.this, "Score Too Low", Toast.LENGTH_SHORT).show();
        }

        //Save to the file.
        String filename = "highScore";
        File savFile = new File(getApplicationContext().getFilesDir(), filename);
        FileOutputStream outStream;

        try{
            outStream = openFileOutput(filename, Context.MODE_PRIVATE);
            outStream.write(message.getBytes());
            outStream.close();
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    public void delHighScore(View view){
        //Confirm if the user is sure.
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Deleting save file: Are you sure?");

        alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                Toast.makeText(Reset.this, "Save file deleted", Toast.LENGTH_LONG).show();
                //Delete save file.
                getApplicationContext().deleteFile("highScore");

                //Change the score to show 0.
                TextView tv3 = (TextView) findViewById(R.id.textView3);
                tv3.setText("0");
            }
        });

        alertDialogBuilder.setNegativeButton("No",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Do nothing.
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

}
