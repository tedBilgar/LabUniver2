package com.tedbilgar.labuniver2;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class ListInterview extends AppCompatActivity {

    TextView username, tasklists;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_interview);

        username = (TextView) findViewById(R.id.devusername);
        tasklists = (TextView) findViewById(R.id.tasklists);

        Bundle arguments = getIntent().getExtras();
        username.append(arguments.get("username").toString());

        SQLiteDatabase database = dbHelper.getWritableDatabase();
        Cursor cursor = database.query(DBHelper.CUSTOMER_DEVELOPER,null,null,null,null,null,null);

      /*  if(cursor.moveToFirst()){
            int aimIndex = cursor.getColumnIndex(DBHelper.KEY_AIM);
            int auditIndex = cursor.getColumnIndex(DBHelper.KEY_AUDIT);
            int funcIndex = cursor.getColumnIndex(DBHelper.KEY_FUNC);
            int platIndex = cursor.getColumnIndex(DBHelper.KEY_PLATF);
            do {
                tasklists.append(cursor.getString(aimIndex));
            } while (cursor.moveToNext());
        }else Log.d("mLog", "0 rows");*/
        cursor.close();
    }
}
