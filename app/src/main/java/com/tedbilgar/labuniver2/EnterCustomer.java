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

public class EnterCustomer extends AppCompatActivity implements View.OnClickListener{

    Button loginButton, registerButton;
    EditText loginNameText, loginPasText, regNameText, regPasText;
    DBHelper dbHelper;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_customer);

        loginButton = (Button) findViewById(R.id.login);
        loginButton.setOnClickListener(this);

        registerButton = (Button) findViewById(R.id.registration);
        registerButton.setOnClickListener(this);

        loginNameText = (EditText) findViewById(R.id.loginName);
        loginPasText = (EditText) findViewById(R.id.loginPas);

        regNameText = (EditText) findViewById(R.id.regName);
        regPasText = (EditText) findViewById(R.id.regPas);

        dbHelper = new DBHelper(this);

        textView = (TextView) findViewById(R.id.textView5);
    }

    @Override
    public void onClick(View v) {
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        String loginEnterName;

        switch (v.getId()){


            case R.id.login:
                String nameLogin = loginNameText.getText().toString();
                String pasLogin = loginPasText.getText().toString();

                Cursor cursor = database.query(DBHelper.CUSTOMER_TABLE,null,null,null,null,null,null);
                String final_vuew = new String();
                boolean isRight = false;
                if(cursor.moveToFirst()){
                    int idIndex = cursor.getColumnIndex(DBHelper.KEY_CUS_ID);
                    int nameIndex = cursor.getColumnIndex(DBHelper.KEY_CUS_NAME);
                    int pasIndex = cursor.getColumnIndex(DBHelper.KEY_CUS_PAS);
                    do {
                        if (nameLogin.equals(cursor.getString(nameIndex)) && pasLogin.equals(cursor.getString(pasIndex))) {
                            isRight = true;
                            loginEnterName = nameLogin;
                            textView.append("YEESSS");
                        }
                    } while (cursor.moveToNext());
                    if (isRight == false) textView.append("NOOOO");
                }else Log.d("mLog", "0 rows");
                cursor.close();
                break;


            case R.id.registration:
                String nameReg = regNameText.getText().toString();
                String pasReg = regPasText.getText().toString();

                contentValues.put(DBHelper.KEY_CUS_NAME, nameReg);
                contentValues.put(DBHelper.KEY_CUS_PAS,pasReg);

                database.insert(DBHelper.CUSTOMER_TABLE, null, contentValues);
                //TODO Переключение
                /*Intent intent = new Intent(MainActivity.this, EnterCustomer.class);
                startActivity(intent);*/
                break;
        }
    }
}
