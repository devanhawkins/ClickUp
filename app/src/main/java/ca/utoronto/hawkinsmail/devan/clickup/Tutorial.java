package ca.utoronto.hawkinsmail.devan.clickup;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


public class Tutorial extends ActionBarActivity {

    public final static String EXTRA_MESSAGE3 = "ca.utoronto.hawkinsmail.devan.clickup.MESSAGE3";
    String message3 = new String();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);

        //Get intent extra message. (high score)
        Intent intent = getIntent();
        message3 = intent.getStringExtra(TitleActivity.EXTRA_MESSAGE3);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tutorial, menu);
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

    //Call when user pushes 'OK' after reading tutorial.
    public void beginGame(View view){
        Intent intent = new Intent(this, MainActivity.class);
        //TextView tv3 = (TextView) findViewById(R.id.textView3);
        //String message3 = tv3.getText().toString();
        intent.putExtra(EXTRA_MESSAGE3, message3);
        startActivity(intent);
        finish();
    }
}
