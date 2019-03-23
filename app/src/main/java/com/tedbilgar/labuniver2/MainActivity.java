package com.tedbilgar.labuniver2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button customer, developer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        customer = (Button) findViewById(R.id.buttonCust);
        customer.setOnClickListener(this);

        developer = (Button) findViewById(R.id.buttonDev);
        developer.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttonCust:
                Intent intent = new Intent(MainActivity.this, EnterCustomer.class);
                startActivity(intent);
                break;
            case R.id.buttonDev:
                //TODO Переключение
                /*Intent intent = new Intent(MainActivity.this, EnterCustomer.class);
                startActivity(intent);*/
                break;
        }
    }
}
