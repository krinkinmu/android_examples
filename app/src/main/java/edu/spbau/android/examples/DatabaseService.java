package edu.spbau.android.examples;

import android.app.IntentService;
import android.content.Intent;
import android.database.Cursor;
import android.util.Log;

public class DatabaseService extends IntentService {

    private static final String TAG = DatabaseService.class.getSimpleName();

    private SMSOpenHelper dbHelper;

    public DatabaseService() {
        super("DatabaseService");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        dbHelper = new SMSOpenHelper(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        dbHelper.close();
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.d(TAG, "Intent received");

        if (!intent.hasExtra(SMSActivity.ORIGIN_KEY) || !intent.hasExtra(SMSActivity.TEXT_KEY))
            return;

        String origin = intent.getStringExtra(SMSActivity.ORIGIN_KEY);
        String text = intent.getStringExtra(SMSActivity.TEXT_KEY);

        dbHelper.addSms(origin, text);

        Cursor cursor = dbHelper.getSmsFrom(origin);
        if (cursor == null)
            return;

        while (cursor.moveToNext()) {
            String sender = cursor.getString(0);
            String message = cursor.getString(1);
            Log.d(TAG, sender + ": " + message);
        }
        cursor.close();
    }

}
