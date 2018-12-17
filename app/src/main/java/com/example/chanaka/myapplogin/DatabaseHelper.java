package com.example.chanaka.myapplogin;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {



        public static final String DATABASE_NAME="sudent.db";
        public static final String TABLE_NAME="sudent_table";
        public static final String COL_1="ID";
        public static final String COL_2="book";
        public static final String COL_3="author";
        public static final String COL_4="location";
        public static final String COL_5="catagory";

        SQLiteDatabase db;

        private static final String TABLE_CREATE="create table sudent_table(ID integer primary key autoincrement,book text,author text,location text,catagory text);";

        public DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, 1);

        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(TABLE_CREATE);
            this.db=db;

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            String query = "DROP TABLE IF EXISTS"+TABLE_NAME;
            db.execSQL(query);
            this.onCreate(db);
        }
        public boolean insertdb(String id,String name,String aut,String loc,String cat){

            SQLiteDatabase db=this.getWritableDatabase();
            ContentValues contentValues=new ContentValues();
            contentValues.put(COL_1,id);
            contentValues.put(COL_2,name);
            contentValues.put(COL_3,aut);
            contentValues.put(COL_4,loc);
            contentValues.put(COL_5,cat);

            long result =db.insert(TABLE_NAME,null,contentValues);
            if(result==-1)
                return false;

            else
                return true;


        }
        public Integer deletedb(String id){

            SQLiteDatabase db=this.getWritableDatabase();
            return db.delete(TABLE_NAME,"ID = ?",new String[] {id});


        }
        public Cursor getall(){

            SQLiteDatabase db=this.getWritableDatabase();
            Cursor res = db.rawQuery("select * from sudent_table",null);
            return  res;

        }


    }

