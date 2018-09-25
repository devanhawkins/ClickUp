package ca.utoronto.hawkinsmail.devan.clickup;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.provider.MediaStore;
import android.provider.MediaStore.Audio;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity {
    //Message = main score, message2 = high score, message3 = old high score.
    public final static String EXTRA_MESSAGE = "ca.utoronto.hawkinsmail.devan.clickup.MESSAGE";
    public final static String EXTRA_MESSAGE2 = "ca.utoronto.hawkinsmail.devan.clickup.MESSAGE2";
    public final static String EXTRA_MESSAGE3 = "ca.utoronto.hawkinsmail.devan.clickup.MESSAGE3";
    int last = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Get intent extra message. (high score)
        Intent intent = getIntent();
        String message = intent.getStringExtra(Reset.EXTRA_MESSAGE);
        String message3 = intent.getStringExtra(Tutorial.EXTRA_MESSAGE3);

        //Update high score value with extra data.
        TextView tv3 = (TextView) findViewById(R.id.textView3);
        try{
            message.isEmpty();
            //If this passes this point, the message is usable.
            tv3.setText(message);
        }catch(NullPointerException e) {
            //Don't use the sent value from Reset activity.
        }

        try{
            message3.isEmpty();
            //Same as above.
            tv3.setText(message3);
        }catch(NullPointerException e){
            //...
        }

        //Compare the two and choose the bigger number.
        try{
            int i = message.compareTo(message3);
            if(i > 0){
                tv3.setText(message);
            }else{
                tv3.setText(message3);
            }
        }catch(NullPointerException e){
            //...
        }

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

    public int rand(){
        //Generates a random number between between 1 and 2.
        final int max = 2;
        final int min = 1;
        int num = (int) (Math.random() * max + min);
        return num;
    }

    public void nextNum(View view){
        //Find the main numbers element.
        TextView textView = (TextView) findViewById(R.id.textView);
        TextView tv3 = (TextView) findViewById(R.id.textView3);

        //Play lower boop sound.
        MediaPlayer mp1 = MediaPlayer.create(getApplicationContext(), R.raw.boop1);
        mp1.start();

        //Convert to string.
        String message = textView.getText().toString();
        String message2 = tv3.getText().toString();

        //Check if User lost by pushing the wrong increment button:
        //Pushed "+1" when num incremented by 2.
        if(last == 2){
            mp1.stop();
            mp1.release();

            //Since this is the practice mode, it just restarts itself when User loses.
            Intent intent = new Intent(this, MainActivity.class); //not Reset.class anymore.
            intent.putExtra(EXTRA_MESSAGE, message);
            intent.putExtra(EXTRA_MESSAGE2, message2);
            startActivity(intent);
            finish();
        }

        //Increase the main number by 1 or 2.
        int plus = rand();
        int curr = Integer.parseInt(message);
        int next = plus + curr;

        //Assign next to the number being viewed.
        message = Integer.toString(next);
        textView.setText(message);

        //Update the 'last' variable that holds the value of the previous random number.
        last = plus;
        //mp1.reset();
        //mp1.release();
    }

    public void nextNum2(View view){
        //Find the main numbers element.
        TextView textView = (TextView) findViewById(R.id.textView);
        TextView tv3 = (TextView) findViewById(R.id.textView3);

        //Play higher boop sound.
        MediaPlayer mp2 = MediaPlayer.create(getApplicationContext(), R.raw.boop2);
        mp2.start();

        //Convert to string.
        String message = textView.getText().toString();
        String message2 = tv3.getText().toString();

        //Check if User lost by pushing the wrong increment button:
        //Pushed "Skip" when num only incremented by 1.
        if(last == 1){
            mp2.stop();
            mp2.release();

            //Since this is the practice mode, it just restarts itself when User loses.
            Intent intent = new Intent(this, MainActivity.class); //not Reset.class anymore.
            intent.putExtra(EXTRA_MESSAGE, message);
            intent.putExtra(EXTRA_MESSAGE2, message2);
            startActivity(intent);
            finish();
        }

        //Increase the main number by 1 or 2.
        int plus = rand();
        int curr = Integer.parseInt(message);
        int next = plus + curr;

        //Assign next to the number being viewed.
        message = Integer.toString(next);
        textView.setText(message);

        //Update the 'last' variable that holds the value of the previous random number.
        last = plus;
        //mp2.reset();
        //mp2.release();
    }
}
