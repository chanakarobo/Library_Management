package com.example.chanaka.myapplogin;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

public class Logindb extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME ="contacts.db";
    private static final String TABLE_NAME ="contacts";
    private static final String COLUMEN_ID ="id";
    private static final String COLUMEN_NAME="name";
    private static final String COLUMEN_EMAIL="email";
    private static final String COLUMEN_UNAME="uname";
    private static final String COLUMEN_PASS="pass";
    SQLiteDatabase db;

    private static final String TABLE_CREATE="create table contacts (id integer primary key not null , "+"" +
            "name text not null, email text not null, uname text not null, pass text not null );";

    public Logindb(Context context){

        super(context , DATABASE_NAME , null , DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(TABLE_CREATE);
        this.db=db;
    }

    public void InsertContact(Contact c){

        db = this.getWritableDatabase();
        ContentValues Values = new ContentValues();

        String query ="select * from contacts";
        Cursor cursor = db.rawQuery(query , null);
        int count = cursor.getCount();

        Values.put(COLUMEN_ID,count);
        Values.put(COLUMEN_NAME , c.getName());
        Values.put(COLUMEN_EMAIL , c.getEmail());
        Values.put(COLUMEN_UNAME , c.getUname());
        Values.put(COLUMEN_PASS , c.getPass());

        db.insert(TABLE_NAME , null , Values);
        db.close();
    }

    public boolean seachPass(String uname,String pass){

        db = this.getReadableDatabase();
        String query = "select * from "+ TABLE_NAME + " where " + COLUMEN_UNAME + " = " + "'"+uname+"'" + " and "
                         + COLUMEN_PASS + " = " + "'"+pass+"'";

        Cursor cursor=db.rawQuery(query , null);

       cursor.moveToFirst();

        if(cursor.getCount()>0){

            return true;
        }
        cursor.close();
        db.close();

        return false;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String query = "DROP TABLE IF EXISTS"+TABLE_NAME;
        db.execSQL(query);
        this.onCreate(db);
    }
}