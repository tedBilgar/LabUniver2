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

import java.util.ArrayList;
import java.util.List;

public class Interview extends AppCompatActivity implements View.OnClickListener {
    TextView textView, selection,textView2;
    Spinner spinner;
    DBHelper dbHelper;
    Button buttonSend, button5;
    List<String> developers = new ArrayList<>();
    EditText aimtext, audittext, functext, platftext, langtext, prototext, prestext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interview);

        dbHelper = new DBHelper(this);

        SQLiteDatabase database = dbHelper.getWritableDatabase();
        Cursor cursor = database.query(DBHelper.DEVELOPER_TABLE,null,null,null,null,null,null);

        if(cursor.moveToFirst()){
            int idIndex = cursor.getColumnIndex(DBHelper.KEY_DEV_ID);
            int nameIndex = cursor.getColumnIndex(DBHelper.KEY_DEV_NAME);
            int pasIndex = cursor.getColumnIndex(DBHelper.KEY_DEV_PAS);
            do {
                developers.add(cursor.getString(nameIndex));
            } while (cursor.moveToNext());
        }else Log.d("mLog", "0 rows");
        cursor.close();

        //делаем спиннер
        spinner = (Spinner) findViewById(R.id.spinner);
        // Создаем адаптер ArrayAdapter с помощью массива строк и стандартной разметки элемета spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, developers);
        // Определяем разметку для использования при выборе элемента
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Применяем адаптер к элементу spinner
        spinner.setAdapter(adapter);

        AdapterView.OnItemSelectedListener itemSelectedListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                // Получаем выбранный объект
                String item = (String)parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        };
        spinner.setOnItemSelectedListener(itemSelectedListener);


        Bundle arguments = getIntent().getExtras();
        textView = (TextView) findViewById(R.id.UserNameView);
        textView.append(arguments.get("username").toString());

        buttonSend = (Button) findViewById(R.id.sendtodev);
        buttonSend.setOnClickListener(this);
        button5 = (Button) findViewById(R.id.button5);
        button5.setOnClickListener(this);
        aimtext = (EditText) findViewById(R.id.aim);
        audittext = (EditText) findViewById(R.id.audit);
        functext = (EditText) findViewById(R.id.func);
        platftext = (EditText) findViewById(R.id.platf);
        langtext = (EditText) findViewById(R.id.lang);
        prototext = (EditText) findViewById(R.id.proto);
        prestext = (EditText) findViewById(R.id.pres);
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.sendtodev:
                SQLiteDatabase database = dbHelper.getWritableDatabase();
                ContentValues contentValues = new ContentValues();

                contentValues.put(DBHelper.KEY_AIM, aimtext.getText().toString());
                contentValues.put(DBHelper.KEY_AUDIT, audittext.getText().toString());
                contentValues.put(DBHelper.KEY_FUNC, functext.getText().toString());
                contentValues.put(DBHelper.KEY_PLATF, platftext.getText().toString());
                contentValues.put(DBHelper.KEY_LANG, langtext.getText().toString());
                contentValues.put(DBHelper.LOGIN_DEV, selection.getText().toString());
                contentValues.put(DBHelper.KEY_PROT_REQ, prototext.getText().toString());
                contentValues.put(DBHelper.KEY_PRES_PROJ, prestext.getText().toString());

                database.insert(DBHelper.CUSTOMER_DEVELOPER, null, contentValues);
                break;
            case R.id.button5:
                Intent intent = new Intent(Interview.this, MyTasks.class);
                startActivity(intent);
                break;
        }
    }
}
