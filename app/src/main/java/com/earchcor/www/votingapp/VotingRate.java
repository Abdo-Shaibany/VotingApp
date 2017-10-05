package com.earchcor.www.votingapp;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

public class VotingRate extends AppCompatActivity {

    ListView lv;
    Integer[] photos = {R.drawable.bill , R.drawable.mark , R.drawable.steve};

    MyDB mdb;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voting_rate);

        lv = (ListView) findViewById(R.id.vote_list);
        mdb = new MyDB(this);
        db = mdb.getReadableDatabase();

        Cursor cursor = db.rawQuery("select * from stars" , null);
        String[] ids = new String[cursor.getCount()];
        String[] types = new String[cursor.getCount()];
        String[] vote = new String[cursor.getCount()];

        int x = 0;
        cursor.moveToFirst();
        while(cursor.isAfterLast() == false){
            ids[x] = cursor.getString(0);
            types[x] = cursor.getString(1);
            vote[x] = cursor.getString(2);

            x++; cursor.moveToNext();
        }

        CustomVote myAdapter = new CustomVote(this , R.layout.my_row , ids , types , vote , photos);
        lv.setAdapter(myAdapter);
//        Toast.makeText(this , "done" + x , Toast.LENGTH_LONG).show();
    }
}
