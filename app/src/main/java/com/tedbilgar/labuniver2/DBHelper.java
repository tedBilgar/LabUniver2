package com.tedbilgar.labuniver2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 18;
    public static final String DATABASE_NAME = "InterviewApp";

    public static final String CUSTOMER_TABLE = "customer";
    public static final String KEY_CUS_ID = "_id";
    public static final String KEY_CUS_NAME = "_name";
    public static final String KEY_CUS_PAS = "_password";

    public static final String DEVELOPER_TABLE = "developer";
    public static final String KEY_DEV_ID = "_id";
    public static final String KEY_DEV_NAME = "_name";
    public static final String KEY_DEV_PAS = "_password";

    public static final String CUSTOMER_DEVELOPER = "customerdeveloper";
    public static final String KEY_DEV_CUS_ID = "_id";
    public static final String KEY_AIM = "_aim";
    public static final String KEY_AUDIT = "_audit";
    public static final String KEY_FUNC = "_func";
    public static final String KEY_PLATF = "_plat";
    public static final String KEY_LANG = "_lang";
    public static final String KEY_PROT_REQ = "_protreq";
    public static final String KEY_PRES_PROJ = "_presproj";
    public static final String LOGIN_DEV = "_logindev";
    public static final String LOGIN_CUS = "_logincus";
    public static final String DEV_SIGN = "_devsign";
    public static final String COMMENT = "_comment";

    public DBHelper( Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + CUSTOMER_TABLE + "(" + KEY_CUS_ID
                    + " integer primary key," + KEY_CUS_NAME + " text," + KEY_CUS_PAS + " text" + ")");

        db.execSQL("create table " + DEVELOPER_TABLE + "(" + KEY_DEV_ID
                + " integer primary key," + KEY_DEV_NAME + " text," + KEY_DEV_PAS + " text" + ")");

        db.execSQL("create table "
                + CUSTOMER_DEVELOPER + " ("
                + KEY_DEV_CUS_ID + " integer primary key, "
                + KEY_AIM + " text ,"
                + KEY_AUDIT + " text,"
                + KEY_FUNC + " text,"
                + KEY_PLATF + " text,"
                + KEY_LANG + " text,"
                + KEY_PROT_REQ + " text,"
                + KEY_PRES_PROJ + " text,"
                + LOGIN_DEV + " text,"
                + LOGIN_CUS + " text,"
                + DEV_SIGN + " integer,"
                + COMMENT + " text" + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + CUSTOMER_TABLE);
        db.execSQL("drop table if exists " + DEVELOPER_TABLE);
        db.execSQL("drop table if exists " + CUSTOMER_DEVELOPER);

        onCreate(db);
    }
}
