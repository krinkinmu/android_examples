package edu.spbau.android.examples;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

public class SMSReceiver extends BroadcastReceiver {

    private static final String TAG = SMSReceiver.class.getSimpleName();
    private static final String PDUS_KEY = "pdus";

    public SMSReceiver() {
    }

    private void showToast(Context context, String origin, String text) {
        Toast.makeText(context, "Message from " + origin + ": " + text,
                Toast.LENGTH_LONG).show();
    }

    private void showActivity(Context context, String origin, String text) {
        Intent intent = new Intent(context, SMSActivity.class);
        intent.putExtra(SMSActivity.ORIGIN_KEY, origin);
        intent.putExtra(SMSActivity.TEXT_KEY, text);
        // this flag required to start an activity from broadcast receiver, if you call
        // startActivity from another activity it's not strictly necessary
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    private void saveToDatabse(Context context, String origin, String text) {
        Intent intent = new Intent(context, DatabaseService.class);
        intent.putExtra(SMSActivity.ORIGIN_KEY, origin);
        intent.putExtra(SMSActivity.TEXT_KEY, text);
        context.startService(intent);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "SMS received");

        Bundle bundle = intent.getExtras();
        if (bundle == null)
            return;

        byte pdus[][] = (byte[][])bundle.get(PDUS_KEY);
        StringBuilder builder = new StringBuilder();
        String origin = null;
        for (int i = 0; i != pdus.length; ++i) {
            SmsMessage sms = SmsMessage.createFromPdu(pdus[i]);
            String text = sms.getDisplayMessageBody();
            builder.append(text);
            origin = sms.getDisplayOriginatingAddress();
        }

        String text = builder.toString();
        //showToast(context, origin, text);
        saveToDatabse(context, origin, text);
        showActivity(context, origin, text);
    }
}
