package com.tedbilgar.labuniver2;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class EnterDeveloper extends AppCompatActivity implements View.OnClickListener {

    Button loginButton, registerButton;
    EditText loginNameText, loginPasText, regNameText, regPasText;
    DBHelper dbHelper;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_developer);

        loginButton = (Button) findViewById(R.id.loginDev);
        loginButton.setOnClickListener(this);

        registerButton = (Button) findViewById(R.id.regDev);
        registerButton.setOnClickListener(this);

        loginNameText = (EditText) findViewById(R.id.loginDevName);
        loginPasText = (EditText) findViewById(R.id.loginDevPas);

        regNameText = (EditText) findViewById(R.id.regDevName);
        regPasText = (EditText) findViewById(R.id.regDevPas);

        dbHelper = new DBHelper(this);

        textView = (TextView) findViewById(R.id.textView2);
    }

    @Override
    public void onClick(View v) {
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        String loginEnterName = "Default value";

        switch (v.getId()){


            case R.id.loginDev:
                String nameLogin = loginNameText.getText().toString();
                String pasLogin = loginPasText.getText().toString();

                Cursor cursor = database.query(DBHelper.DEVELOPER_TABLE,null,null,null,null,null,null);
                boolean isRight = false;

                if(cursor.moveToFirst()){
                    int idIndex = cursor.getColumnIndex(DBHelper.KEY_DEV_ID);
                    int nameIndex = cursor.getColumnIndex(DBHelper.KEY_DEV_NAME);
                    int pasIndex = cursor.getColumnIndex(DBHelper.KEY_DEV_PAS);
                    do {
                        if (nameLogin.equals(cursor.getString(nameIndex)) && pasLogin.equals(cursor.getString(pasIndex))) {
                            isRight = true;
                            loginEnterName = nameLogin;
                             Intent intent = new Intent(EnterDeveloper.this, ListInterview.class);
                             intent.putExtra("username", loginEnterName);
                             startActivity(intent);
                            break;
                        }
                    } while (cursor.moveToNext());
                }else Log.d("mLog", "0 rows");
                cursor.close();

                if (isRight == false) {
                    textView.append("NO");
                }

                break;


            case R.id.regDev:
                String nameReg = regNameText.getText().toString();
                String pasReg = regPasText.getText().toString();

                contentValues.put(DBHelper.KEY_DEV_NAME, nameReg);
                contentValues.put(DBHelper.KEY_DEV_PAS,pasReg);

                database.insert(DBHelper.DEVELOPER_TABLE, null, contentValues);

                Intent intent1 = new Intent(EnterDeveloper.this, ListInterview.class);
                intent1.putExtra("username", nameReg);
                startActivity(intent1);
                break;
        }
    }
}
