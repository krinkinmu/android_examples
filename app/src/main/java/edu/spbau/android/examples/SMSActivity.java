package edu.spbau.android.examples;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class SMSActivity extends ActionBarActivity {

    public static final String ORIGIN_KEY = "sms_origin";
    public static final String TEXT_KEY = "sms_text";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);

        TextView sms = (TextView) findViewById(R.id.sms_text_view);
        Intent intent = getIntent();

        if (intent.hasExtra(ORIGIN_KEY) && intent.hasExtra(TEXT_KEY)) {
            String origin = intent.getStringExtra(ORIGIN_KEY);
            String text = intent.getStringExtra(TEXT_KEY);
            sms.setText("Message from " + origin + ": " + text);
        }
    }

}
