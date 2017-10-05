package com.earchcor.www.votingapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by root on 05/10/17.
 */

public class MyDB extends SQLiteOpenHelper {


    public static String username;
    public MyDB(Context context) {
        super(context, "voting.db", null , 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("create table users(username varchar(20), password varchar(20));");

        sqLiteDatabase.execSQL("insert into users values('admin' , 'admin');");

        sqLiteDatabase.execSQL("create table stars(id number(2) , name varchar(50) , votes number(4));");
        sqLiteDatabase.execSQL("create table voting (id number(2) , mobile varchar(20));");

        sqLiteDatabase.execSQL("insert into stars values(1 , 'Bill' , 0);");
        sqLiteDatabase.execSQL("insert into stars values(2 , 'Mark' , 0);");
        sqLiteDatabase.execSQL("insert into stars values(3 , 'Steve' , 0);");


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
