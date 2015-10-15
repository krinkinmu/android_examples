package edu.spbau.android.examples;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SMSOpenHelper extends SQLiteOpenHelper {

    private static final int VERSION = 1;

    // names must be unique within an application
    private static final String DATABASE_NAME = "sms_example";

    private static final String SMS_TABLE = "sms";
    private static final String SMS_ORIGIN = "origin";
    private static final String SMS_TEXT = "text";

    private static final String CREATE_TABLE = "CREATE TABLE " + SMS_TABLE + " ("
            + SMS_ORIGIN + " TEXT NOT NULL, " + SMS_TEXT + " TEXT NOT NULL);";
    private static final String DROP_TABLE = "DROP TABLE IF EXISTS " + SMS_TABLE + ";";

    private static final String ADD_SMS = "INSERT INTO " + SMS_TABLE + "(" + SMS_ORIGIN + ", "
            + SMS_TEXT + ")" +" VALUES (?, ?);";
    private static final String GET_SMS_FROM = "SELECT " + SMS_ORIGIN + ", " + SMS_TEXT
            + " FROM " + SMS_TABLE + " WHERE " + SMS_ORIGIN + " = ?;";

    public SMSOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE);
        onCreate(db);
    }

    public void addSms(String origin, String text) {
        getWritableDatabase().execSQL(ADD_SMS, new String[] { origin, text });
    }

    public Cursor getSmsFrom(String origin) {
        return getReadableDatabase().rawQuery(GET_SMS_FROM, new String[] { origin });
    }
}
