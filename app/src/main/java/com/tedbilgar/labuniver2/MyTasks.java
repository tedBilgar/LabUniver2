package com.tedbilgar.labuniver2;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MyTasks extends AppCompatActivity {
    DBHelper dbHelper;
    String my_username;
    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_tasks);

        Bundle arguments = getIntent().getExtras();
        my_username = arguments.get("username").toString();

        dbHelper = new DBHelper(this);
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();

        linearLayout = (LinearLayout) findViewById(R.id.layoutTasks);


        Cursor cursor = sqLiteDatabase.query(DBHelper.CUSTOMER_DEVELOPER,null,null,null,null,null,null);

        if(cursor.moveToFirst()){
            int IDIndex = cursor.getColumnIndex(DBHelper.KEY_DEV_CUS_ID);
            int aimIndex = cursor.getColumnIndex(DBHelper.KEY_AIM);
            int auditIndex = cursor.getColumnIndex(DBHelper.KEY_AUDIT);
            int funcIndex = cursor.getColumnIndex(DBHelper.KEY_FUNC);
            int platfIndex = cursor.getColumnIndex(DBHelper.KEY_PLATF);
            int langIndex = cursor.getColumnIndex(DBHelper.KEY_LANG);
            int protoIndex = cursor.getColumnIndex(DBHelper.KEY_PROT_REQ);
            int presIndex = cursor.getColumnIndex(DBHelper.KEY_PRES_PROJ);
            int loginIndex = cursor.getColumnIndex(DBHelper.LOGIN_DEV);
            int loginCusIndex = cursor.getColumnIndex(DBHelper.LOGIN_CUS);
            int signIndex = cursor.getColumnIndex(DBHelper.DEV_SIGN);
            int comIndex = cursor.getColumnIndex(DBHelper.COMMENT);
            do {
                if (my_username.equals(cursor.getString(loginCusIndex))) {
                    TextView textView = new TextView(this);
                    textView.append(cursor.getString(aimIndex));
                    textView.setGravity(9);
                    textView.setTextSize(40);
                    textView.setTextColor(Color.rgb(247,139, 32));
                    linearLayout.addView(textView);

                    if (cursor.getString(comIndex) != null){
                        TextView textView2 = new TextView(this);
                        textView2.append("Комментарий: " + cursor.getString(comIndex));
                        textView2.setGravity(9);
                        textView2.setTextSize(20);
                        linearLayout.addView(textView2);
                    }

                    if (cursor.getInt(signIndex) != 2){
                        TextView textView3 = new TextView(this);
                        textView3.append("НЕ ПОДТВЕРЖДЕНО");
                        textView3.setGravity(9);
                        textView3.setTextSize(25);
                        textView3.setTextColor(Color.rgb(247,57, 31));
                        linearLayout.addView(textView3);
                    }else{
                        TextView textView3 = new TextView(this);
                        textView3.append("ПОДТВЕРЖДЕНО");
                        textView3.setGravity(9);
                        textView3.setTextSize(25);
                        textView3.setTextColor(Color.rgb(21,86, 48));
                        linearLayout.addView(textView3);
                    }
                }
            } while (cursor.moveToNext());
        }else Log.d("mLog", "0 rows");
        cursor.close();
    }
}
