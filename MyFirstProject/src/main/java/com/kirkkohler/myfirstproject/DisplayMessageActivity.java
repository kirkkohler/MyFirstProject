package com.kirkkohler.myfirstproject;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.TextView;

public class DisplayMessageActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.e("MyFirstProject: " + this.getClass().getSimpleName(), "onCreate lifecycle state");

        Log.e("MyFirstProject: " + this.getClass().getSimpleName(), "attempt getting message from intent");
        // This attempts getting a string from any intent that launched this Activity
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        // Create the text view
        TextView textView = new TextView(this);
        textView.setTextSize(40);
        textView.setText(message);

        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_display_message);

        // Set the text view as the activity layout
        setContentView(textView);
    }

    @Override
    protected void onResume() {
        Log.e("MyFirstProject: " + this.getClass().getSimpleName(), "onResume lifecycle state");
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.e("MyFirstProject: " + this.getClass().getSimpleName(), "onPause lifecycle state");
        super.onPause();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.display_message, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
