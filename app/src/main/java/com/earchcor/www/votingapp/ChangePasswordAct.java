package com.earchcor.www.votingapp;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ChangePasswordAct extends AppCompatActivity {

    EditText etPass , etConfPass;
    MyDB mdb;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        etPass = (EditText) findViewById(R.id.etEnterPass);
        etConfPass = (EditText) findViewById(R.id.etConfPass);

    }

    public void changePass(View view){

        mdb = new MyDB(this);
        db = mdb.getWritableDatabase();

        if(etPass.getText().toString().equals(etConfPass.getText().toString())) {
            db.execSQL("Update users set password=? where username=?",
                    new String[]{etPass.getText().toString(), MyDB.username});

            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        } else
            Toast.makeText(this , "NOT MATCH PASSWORDS" , Toast.LENGTH_SHORT).show();

    }
}
