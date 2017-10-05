package com.earchcor.www.votingapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText etUser , etPassword;
    MyDB mydb;
    SQLiteDatabase dbWork;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etUser = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);

    }

    public void login(View v){

        mydb = new MyDB(this);
        dbWork = mydb.getReadableDatabase();

        Cursor cursor = dbWork.rawQuery("select * from users where username=? and password=?" ,
                            new String[]{etUser.getText().toString() , etPassword.getText().toString()});

        if (cursor.getCount() == 0) {
            Toast.makeText(this, "invalid user", Toast.LENGTH_SHORT).show();
            etPassword.setText("");
            etPassword.requestFocus();
        }
        else {
            MyDB.username = etUser.getText().toString();
            Intent intent = new Intent(this , ProfileAct.class);
            startActivity(intent);
        }
    }

    public void goContacts(View view){
        Intent intent = new Intent(this , ContactAct.class);
        startActivity(intent);
    }
}
