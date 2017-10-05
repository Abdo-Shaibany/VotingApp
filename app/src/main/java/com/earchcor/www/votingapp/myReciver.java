package com.earchcor.www.votingapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

/**
 * Created by root on 05/10/17.
 */

public class myReciver extends BroadcastReceiver {

    MyDB myDB;
    SQLiteDatabase db;

    @Override
    public void onReceive(Context context, Intent intent) {

        Bundle bundle = intent.getExtras();
        SmsMessage[] msgs = null;
        String messageReceived = "";

        if(bundle != null){
            Object[] pdus = (Object[]) bundle.get("pdus");
            msgs = new SmsMessage[pdus.length];

            for(int i = 0 ; i < msgs.length ; i++){
                msgs[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                messageReceived += msgs[i].getMessageBody().toString();
                messageReceived += "\n";
            }

            myDB = new MyDB(context);
            db = myDB.getWritableDatabase();

            db.execSQL("insert into voting values(?,?)" ,
                    new String[]{messageReceived , msgs[0].getOriginatingAddress().toString()});
            db.execSQL("update stars set votes=votes+1 where id=?" , new String[]{"1"});

        }


    }
}
