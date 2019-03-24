package com.tedbilgar.labuniver2;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;

public class ListInterview extends AppCompatActivity implements View.OnClickListener {

    TextView username, tasklists;
    DBHelper dbHelper;
    String usernameString;
    LinearLayout linearLayout;
    TextView testView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_interview);
        linearLayout = findViewById(R.id.layout);

        username = (TextView) findViewById(R.id.devusername);
        testView = (TextView) findViewById(R.id.testView);

        Bundle arguments = getIntent().getExtras();
        usernameString = arguments.get("username").toString();
        username.append(usernameString);

        dbHelper = new DBHelper(this);
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        Cursor cursor = database.query(DBHelper.CUSTOMER_DEVELOPER,null,null,null,null,null,null);

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

             do {
                if (cursor.getString(loginIndex).equals(usernameString)) {
                    TextView textView1 = new TextView(this);
                    textView1.append("Предназначение: " + cursor.getString(aimIndex));
                    textView1.setGravity(9);
                    textView1.setTextSize(16);
                    linearLayout.addView(textView1);

                    TextView textView2 = new TextView(this);
                    textView2.append("Аудитория: " + cursor.getString(auditIndex));
                    textView2.setGravity(9);
                    textView2.setTextSize(16);
                    linearLayout.addView(textView2);

                    TextView textView3 = new TextView(this);
                    textView3.append("Функционал: " + cursor.getString(funcIndex));
                    textView3.setGravity(9);
                    textView3.setTextSize(16);
                    linearLayout.addView(textView3);

                    TextView textView4 = new TextView(this);
                    textView4.append("Платформа: " + cursor.getString(platfIndex));
                    textView4.setGravity(9);
                    textView4.setTextSize(16);
                    linearLayout.addView(textView4);

                    TextView textView5 = new TextView(this);
                    textView5.append("Используемые технологии: " + cursor.getString(langIndex));
                    textView5.setGravity(9);
                    textView5.setTextSize(16);
                    linearLayout.addView(textView5);

                    TextView textView6 = new TextView(this);
                    textView6.append("Нужен ли прототип: " + cursor.getString(protoIndex));
                    textView6.setGravity(9);
                    textView6.setTextSize(16);
                    linearLayout.addView(textView6);

                    TextView textView7 = new TextView(this);
                    textView7.append("Нужена ли презентация: " + cursor.getString(presIndex));
                    textView7.setGravity(9);
                    textView7.setTextSize(16);
                    linearLayout.addView(textView7);

                    EditText editText = new EditText(this);
                    editText.setId(cursor.getInt(IDIndex) + 200);
                    editText.setHint("Ваш комментарий");
                    editText.setGravity(9);
                    editText.setTextSize(16);
                    linearLayout.addView(editText);

                    Button button = new Button(this);
                    button.setId(cursor.getInt(IDIndex));
                    button.setText("Дать комментарий");
                    button.setOnClickListener(this);
                    linearLayout.addView(button);

                    Button button2 = new Button(this);
                    button2.setText("Подтвердить работу");
                    button2.setId(cursor.getInt(IDIndex) + 100);
                    button2.setOnClickListener(this);
                    linearLayout.addView(button2);

                    testView.append(cursor.getString(loginCusIndex));
                }
            } while (cursor.moveToNext());
        }else Log.d("mLog", "0 rows");
        cursor.close();
    }

    @Override
    public void onClick(View v) {

        SQLiteDatabase database = dbHelper.getWritableDatabase();

        if ((v.getId()) < 100){
            EditText editText = (EditText) findViewById(v.getId()+200);
            String value = editText.getText().toString();

            ContentValues contentValues = new ContentValues();
            contentValues.put(DBHelper.COMMENT, value);

            database.insert(DBHelper.CUSTOMER_DEVELOPER, null, contentValues);
            username.append(value);
        }
    }
}
