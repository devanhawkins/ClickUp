package ca.utoronto.hawkinsmail.devan.clickup;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class TitleActivity extends ActionBarActivity {
    //public static final String PREFS_NAME = "AOP_PREFS";
    //public static final String PREFS_KEY = "AOP_PREFS_String";

    //Message 3 is the High score value.
    public final static String EXTRA_MESSAGE3 = "ca.utoronto.hawkinsmail.devan.clickup.MESSAGE3";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_title);

        //Load in high score value from save file.
        String filename = "highScore";
        File file = new File(getApplicationContext().getFilesDir(), filename);
        String text = new String();
        //StringBuilder

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

            while ((line = br.readLine()) != null) {
                //text.append(line);
                text += line;
            }
            br.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        //If there is no high score file, set to 0.
        if(text.equals("")){
            text = "0";
        }
        //Set the high score in the .xml file.
        TextView tv3 = (TextView) findViewById(R.id.textView3);
        //String message = Integer.toString(highScore);
        tv3.setText(text);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_title, menu);
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

    //Called when the User pushes the Wander Mode button.
    public void beginTut(View view){
        Intent intent = new Intent(this, Tutorial.class);
        TextView tv3 = (TextView) findViewById(R.id.textView3);
        String message3 = tv3.getText().toString();
        intent.putExtra(EXTRA_MESSAGE3, message3);
        startActivity(intent);
    }

    //Called when the Marathon Mode button.
    public void beginTimedTut(View view){
        Intent intent = new Intent(this, TimedTutActivity.class);
        TextView tv3 = (TextView) findViewById(R.id.textView3);
        String message3 = tv3.getText().toString();
        intent.putExtra(EXTRA_MESSAGE3, message3);
        startActivity(intent);
    }

    public void beginAdventure(View view){
        //Coming Soon!
        Toast.makeText(TitleActivity.this, "Coming Soon!", Toast.LENGTH_SHORT).show();
    }

    public void beginPedometer(View view){
        //Is an intent needed? Yes, to set destination.
        Intent intent = new Intent(this, Pedometer.class);
        startActivity(intent);
    }
}
