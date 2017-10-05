package com.earchcor.www.votingapp;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ResultAct extends AppCompatActivity {

    EditText rus;
    MyDB myDB;
    SQLiteDatabase sqLiteDatabase;
    SQLiteDatabase sqLiteDatabase1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        rus = (EditText) findViewById(R.id.etRuslt);
    }

    public void send(View view){
        myDB = new MyDB(this);
        sqLiteDatabase = myDB.getReadableDatabase();
        sqLiteDatabase1 = myDB.getWritableDatabase();

        sqLiteDatabase1.execSQL("insert into voting values(?,?)" , new String[]{"2" , "771402072"});

        Cursor cursor = sqLiteDatabase.rawQuery("select * from stars" , null);
        cursor.moveToFirst();
        String msg = rus.getText().toString() + "\n";

        while(cursor.isAfterLast() == false){
            msg += cursor.getString(1) + " has got " + cursor.getString(2) + "\n";
            cursor.moveToNext();
        }

        cursor = sqLiteDatabase.rawQuery("select mobile from voting" , null);
        cursor.moveToFirst();

        SmsManager manager = SmsManager.getDefault();

        while(cursor.isAfterLast() == false){
            manager.sendTextMessage(cursor.getString(0) , null , msg , null , null);
            cursor.moveToNext();
        }
    }
}
