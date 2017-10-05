package com.earchcor.www.votingapp;

import android.content.pm.PackageManager;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.jar.Manifest;

public class ContactAct extends AppCompatActivity {

    ListView ls;
    int p = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        ls = (ListView) findViewById(R.id.list_contacts);

    }

    public void show(View view){
        try {
            readContacts();
        } catch (SecurityException e){
            ActivityCompat.requestPermissions(this , new String[] {android.Manifest.permission.READ_CONTACTS} , p);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == p){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                readContacts();
            }
        }
    }

    public void readContacts(){
        ArrayList<String> list= new ArrayList<String>();
        Cursor cur = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI
                , null , null , null , null );

        cur.moveToFirst();
        while(cur.isAfterLast() == false){
            String n = cur.getString(cur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            String m = cur.getString(cur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            list.add(n + "-" + m);
            cur.moveToNext();
        }

        ArrayAdapter<String> adt = new ArrayAdapter<String>(this , android.R.layout.simple_list_item_1 , list);
        ls.setAdapter(adt);
    }
}
