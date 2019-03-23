package com.tedbilgar.labuniver2;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Interview extends AppCompatActivity {
    TextView textView;
    Spinner spinner;
    DBHelper dbHelper;
    List<String> developers = new ArrayList<>();

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

        Bundle arguments = getIntent().getExtras();
        textView = (TextView) findViewById(R.id.UserNameView);
        textView.append(arguments.get("username").toString());
    }



}
