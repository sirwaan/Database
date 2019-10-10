package com.sirwansoft.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;


public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME ="Student.db";
    public static final String TABLE_NAME ="student_table";
    public static final String ID ="ID";
    public static final String COL_1 ="ID";
    public static final String COL_2 ="NAME";
    public static final String COL_3 ="SURNAME";
    public static final String COL_4 ="MARKS";




    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME+ "(ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT,SURNAME TEXT,MARKS INTEGER)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    //write data in database file
    public boolean insertData(String name,String surnname ,String mark ){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,name);
        contentValues.put(COL_3,surnname);
        contentValues.put(COL_4,mark);
        db.insert(DATABASE_NAME,null,contentValues);
        long result = db.insert(TABLE_NAME,null,contentValues);
        if (result==-1){
            return false;
        }else return true;

    }
    //view data from data base inside app
    public Cursor getAllData(){

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor res =db.rawQuery("select * from "+TABLE_NAME,null);
        return res;
    }

    public boolean updateData(String id,String name,String surname ,String marks){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,id);
        contentValues.put(COL_2,name);
        contentValues.put(COL_3,surname);
        contentValues.put(COL_4,marks);

        db.update(TABLE_NAME,contentValues,"id = ?",new String[]{id});
        return true;
    }


    public Integer deleteData (String id){

        SQLiteDatabase db = this.getWritableDatabase();

       return db.delete(TABLE_NAME,"ID = ?",new String[]{id});

    }

}
