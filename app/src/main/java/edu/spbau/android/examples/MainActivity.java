package edu.spbau.android.examples;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {

    private static final String SAVED_STRING_KEY = "a_saved_string";
    private static final String TAG = MainActivity.class.getSimpleName();

    /**
     * This method is called right after Activity is created
     *
     * @param savedInstanceState - saved state, you can save state of an activity (current index
     *                           in a list, content of text fields and so on) to restore it after
     *                           activity has been destroyed. Android can destroy activity if there
     *                           is no enough resources (when Android is short of memory).
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "Create");
        setContentView(R.layout.activity_main);

        TextView view = (TextView) findViewById(R.id.hello_text_view);

        /**
         * If activity is being reinitialized, then savedInstanceState isn't null and
         * we can get saved data from it
         */
        if (savedInstanceState != null) {
            view.setText(savedInstanceState.getString(SAVED_STRING_KEY));
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle state) {
        super.onSaveInstanceState(state);
        Log.d(TAG, "Save instance state");
        state.putString(SAVED_STRING_KEY, "Activity has been destroyed");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "Started");
    }

    /**
     * This is a good place to acquire system resources, start animations and so on
     */
    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "Resumed");
    }

    /**
     * You should save any persistent state here, also you should stop animations, release all
     * resources and so on, because onStop may never be called under memory pressure
     */
    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "Paused");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "Stopped");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (isFinishing()) {
            /**
             * Someone called finish - we have done everything we want and going to finish
             */
            Log.d(TAG, "Activity is finishing");
        } else {
            /**
             * Android is short of memory and going to kill our activity to free some
             */
            Log.d(TAG, "Activity is being destroyed");
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
