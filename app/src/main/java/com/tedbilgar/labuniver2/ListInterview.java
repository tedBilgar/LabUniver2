package com.tedbilgar.labuniver2;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ListInterview extends AppCompatActivity {

    TextView username, tasklists;
    DBHelper dbHelper;
    String usernameString;
    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_interview);

        username = (TextView) findViewById(R.id.devusername);
        tasklists = (TextView) findViewById(R.id.tasklists);

        Bundle arguments = getIntent().getExtras();
        usernameString = arguments.get("username").toString();
        username.append(usernameString);

        dbHelper = new DBHelper(this);
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        Cursor cursor = database.query(DBHelper.CUSTOMER_DEVELOPER,null,null,null,null,null,null);

        if(cursor.moveToFirst()){
            int aimIndex = cursor.getColumnIndex(DBHelper.KEY_AIM);
            int auditIndex = cursor.getColumnIndex(DBHelper.KEY_AUDIT);
            int funcIndex = cursor.getColumnIndex(DBHelper.KEY_FUNC);
            int platfIndex = cursor.getColumnIndex(DBHelper.KEY_PLATF);
            int langIndex = cursor.getColumnIndex(DBHelper.KEY_LANG);
            int protoIndex = cursor.getColumnIndex(DBHelper.KEY_PROT_REQ);
            int presIndex = cursor.getColumnIndex(DBHelper.KEY_PRES_PROJ);
            int loginIndex = cursor.getColumnIndex(DBHelper.LOGIN_DEV);
            do {
                if (cursor.getString(loginIndex).equals(usernameString)) {
                    tasklists.append(cursor.getString(aimIndex));
                }
            } while (cursor.moveToNext());
        }else Log.d("mLog", "0 rows");
        cursor.close();
    }
}
