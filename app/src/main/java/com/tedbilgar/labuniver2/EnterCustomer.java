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
        String loginEnterName = "Default value";

        switch (v.getId()){


            case R.id.login:
                String nameLogin = loginNameText.getText().toString();
                String pasLogin = loginPasText.getText().toString();

                Cursor cursor = database.query(DBHelper.CUSTOMER_TABLE,null,null,null,null,null,null);
                boolean isRight = false;

                if(cursor.moveToFirst()){
                    int idIndex = cursor.getColumnIndex(DBHelper.KEY_CUS_ID);
                    int nameIndex = cursor.getColumnIndex(DBHelper.KEY_CUS_NAME);
                    int pasIndex = cursor.getColumnIndex(DBHelper.KEY_CUS_PAS);
                    do {
                        if (nameLogin.equals(cursor.getString(nameIndex)) && pasLogin.equals(cursor.getString(pasIndex))) {
                            isRight = true;
                            loginEnterName = nameLogin;
                            break;
                        }
                    } while (cursor.moveToNext());
                }else Log.d("mLog", "0 rows");
                cursor.close();

                if (isRight == true) {
                    Intent intent = new Intent(EnterCustomer.this, Interview.class);
                    intent.putExtra("username", loginEnterName);
                    startActivity(intent);
                } else textView.append(" (Ошибка входа)");

                break;


            case R.id.registration:
                String nameReg = regNameText.getText().toString();
                String pasReg = regPasText.getText().toString();

                contentValues.put(DBHelper.KEY_CUS_NAME, nameReg);
                contentValues.put(DBHelper.KEY_CUS_PAS,pasReg);

                database.insert(DBHelper.CUSTOMER_TABLE, null, contentValues);

                Intent intent1 = new Intent(EnterCustomer.this, Interview.class);
                intent1.putExtra("username", nameReg);
                startActivity(intent1);
                break;
        }
    }
}
