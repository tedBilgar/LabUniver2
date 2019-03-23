package com.tedbilgar.labuniver2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "InterviewApp";

    public static final String CUSTOMER_TABLE = "customer";
    public static final String KEY_CUS_ID = "_id";
    public static final String KEY_CUS_NAME = "_name";
    public static final String KEY_CUS_PAS = "_password";

    public static final String DEVELOPER_TABLE = "developer";
    public static final String KEY_DEV_ID = "_id";
    public static final String KEY_DEV_NAME = "_name";
    public static final String KEY_DEV_PAS = "_password";

    public DBHelper( Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + CUSTOMER_TABLE + "(" + KEY_CUS_ID
                    + " integer primary key," + KEY_CUS_NAME + " text," + KEY_CUS_PAS + " text" + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + CUSTOMER_TABLE);

        onCreate(db);
    }
}
