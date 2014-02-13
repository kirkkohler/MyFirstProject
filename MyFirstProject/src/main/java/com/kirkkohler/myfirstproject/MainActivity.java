package com.kirkkohler.myfirstproject;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends Activity {
    public static final String EXTRA_MESSAGE = "com.kirkkohler.myfirstproject.EXTRA_MESSAGE";
    private final String TAG = "MyFirstProject:" + this.getClass().getSimpleName();

    //Defined to start/stop mp3 file
    MediaPlayer myMediaPlayer;

    // Urls to launch
    String kirkkohlerUrl = "http://kirkkohler.com";

    // Get text from editText field
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.e(TAG, "Called on every orientation change from portrait and landscape.\n");
        Log.e(TAG, "onCreate lifecycle state");

        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }

    @Override
    protected void onStart() {
        Log.e(TAG, "onStart lifecycle state *********** ");

        //point media player to file
        myMediaPlayer = MediaPlayer.create(this, R.raw.djknysle_test2);

        // get edit text view
        editText = (EditText) findViewById(R.id.editPinkText);
        Log.e(TAG, "onStart, editText: " + editText);

        super.onStart();
    }

    @Override
    protected void onStop() {
        Log.e(TAG, "onStop lifecycle state *********** ");
        super.onStop();
    }

    @Override
    protected void onRestart() {
        Log.e(TAG, "onRestart lifecycle state *********** ");
        super.onRestart();
    }

    @Override
    protected void onDestroy() {
        Log.e(TAG, "onDestroy lifecycle state *********** ");
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        Log.e(TAG, "onResume lifecycle state");

        // Start Animation
        ImageView spaceshipImage = (ImageView) findViewById(R.id.image_spaceShip);

        /*
         needed to validate if ImageView existed.  Found that app would crash in landscape mode since spaceShip wasn't defined in fragment for landscape.

         Thus ignore animation if spaceshipImage does not exist
          */
        if (spaceshipImage != null) {
            Animation hyperspaceJumpAnimation = AnimationUtils.loadAnimation(this, R.anim.hyperspace_jump);
            spaceshipImage.startAnimation(hyperspaceJumpAnimation);
        }

        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.e(TAG, "onPause lifecycle state");
        myMediaPlayer.stop();
        super.onPause();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }
    }

    /**
     * Start/stop event for button.
     */
    public void startStopMusic(View view) {
        if (myMediaPlayer.isPlaying()) {
            myMediaPlayer.stop();
        } else {
            myMediaPlayer.start();
        }
    }

    /**
     * Launch web view with embedded site.
     */
    public void launchWebIntentEmbeddedSite(View view) {
        // create intent to launch webview
        Intent intent = new Intent(this, WebViewActivity.class);

        // start WebViewActivity activity via intent
        startActivity(intent);
    }

    /**
     * Open an intent to open a website.
     * Passing in a View object allows for access to which view called method
     */
    public void launchWebIntentSite(View view) {
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(kirkkohlerUrl));
        startActivity(i);
    }

    /**
     * Called when the user clicks the Send button
     */
    public void displayToast(View view) {
        String editTextStr = editText.getText().toString();

        // show toast with app name or value in edit text field
        if (editTextStr.trim().equals("")) {
            Toast.makeText(this.getApplicationContext(), R.string.app_name, Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this.getApplicationContext(), editTextStr, Toast.LENGTH_LONG).show();
        }
    }

    /**
     * Called when the user clicks the Send button
     */
    public void sendMessage(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, DisplayMessageActivity.class);

        // Convert to a string
        String message = editText.getText().toString();

        // place text data into intent's "extras" as a key-value pairs
        intent.putExtra(EXTRA_MESSAGE, message);

        // start DisplayMessage activity
        startActivity(intent);
    }

    /**
     * Simple fade to right to test animation
     * @param view
     */
    public void simpleAnimate(View view){
        // Create a simple animation to slide an object to the right
        Animation anim = AnimationUtils.makeOutAnimation(this.getApplicationContext(), true);
        view.startAnimation(anim);
    }
}
