package com.kirkkohler.myfirstproject;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

public class WebViewActivity extends Activity {
    public WebView myWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        Log.e("MyFirstProject: " + this.getClass().getSimpleName(), "onCreate lifecycle state");

        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new webView_Fragment())
                    .commit();
        }

        myWebView = (WebView) findViewById(R.id.webViewMain);
        // add js support
        myWebView.getSettings().setJavaScriptEnabled(true);
        //allow zoom of text/site
        myWebView.getSettings().setDisplayZoomControls(true);
        // open asset/index.html file
        myWebView.loadUrl("file:///android_asset/index.html");
/*
        if(findViewById(R.layout.fragment_web_view) != null){
            View myFragView = getFragmentManager().findFragmentById(R.layout.fragment_web_view).getView();

            // find webView by id
            myWebView = (WebView) myFragView.findViewById(R.id.webView1);
            myWebView.getSettings().setJavaScriptEnabled(true);
            // open asset/index.html file
            myWebView.loadUrl("file:///android_asset/index.html");
        }
 */
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.web_view, menu);
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
    public static class webView_Fragment extends Fragment {

        public webView_Fragment() {
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            Log.e("MyFirstProject: " + this.getClass().getSimpleName(), "onCreate fragment lifecycle state");
            super.onCreate(savedInstanceState);
        }

        @Override
        /*
        * Fragment must implement onCreateView and return root view of fragment.
        * Used by Android system when it is time to draw fragment layout.
        * Layoutinflater is used to inflate view with a layout defined in an xml file. e.g. layout.fragment_web_view
         */
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {

            Log.e("MyFirstProject: " + this.getClass().getSimpleName(), "onCreateView fragment lifecycle state");

            // root view of fragment returned to activity
            // container is the parent viewgroup from activity which includes fragments
            View rootView = inflater.inflate(R.layout.fragment_web_view, container, false);
            return rootView;
        }

        @Override
        public void onPause() {
            Log.e("MyFirstProject: " + this.getClass().getSimpleName(), "onPause fragment lifecycle state");
            super.onPause();
        }
    }

}
