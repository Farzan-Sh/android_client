package com.F.ac;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class DBAdapter 
{
    public static final String KEY_ROWID = "_id";
    public static final String KEY_USERPASS = "userpass";
    public static final String KEY_FRIEND = "friend";
    public static final String KEY_CHAT = "chat";    
    private static final String TAG = "DBAdapter";
    
    //private static final String DATABASE_NAME = "books";
    private static final String DATABASE_NAME = "DB";
    private static final String DATABASE_TABLE = "titles";
    private static final int DATABASE_VERSION = 3;

    private static final String DATABASE_CREATE =
        "create table titles (_id integer primary key autoincrement, "
        + "userpass text not null, friend text not null, " 
        + "chat text not null);";
        
    private final Context context; 
    
    private DatabaseHelper DBHelper;
    private SQLiteDatabase db;

    public DBAdapter(Context ctx) 
    {
        this.context = ctx;
        DBHelper = new DatabaseHelper(context);
    }
        
    private static class DatabaseHelper extends SQLiteOpenHelper 
    {
        DatabaseHelper(Context context) 
        {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) 
        {
            db.execSQL(DATABASE_CREATE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, 
        int newVersion) 
        {
            Log.w(TAG, "Upgrading database from version " + oldVersion 
                    + " to "
                    + newVersion + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS titles");
            onCreate(db);
        }
    }    
    
    //---opens the database---
    public DBAdapter open() throws SQLException 
    {
        db = DBHelper.getWritableDatabase();
        return this;
    }

    //---closes the database---    
    public void close() 
    {
        DBHelper.close();
    }
    
    //---insert a title into the database---
    public long insertTitle(String userpass, String friend, String chat) 
    {
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_USERPASS, userpass);
        initialValues.put(KEY_FRIEND, friend);
        initialValues.put(KEY_CHAT, chat);
        return db.insert(DATABASE_TABLE, null, initialValues);
    }

    //---deletes a particular title---
    public boolean deleteTitle(long rowId) 
    {
        return db.delete(DATABASE_TABLE, KEY_ROWID + 
        		"=" + rowId, null) > 0;
    }

    //---retrieves all the titles---
    public Cursor getAllTitles() 
    {
        return db.query(DATABASE_TABLE, new String[] {
        		KEY_ROWID, 
        		KEY_USERPASS,
        		KEY_FRIEND,
                KEY_CHAT}, 
                null, 
                null, 
                null, 
                null, 
                null);
    }

    //---retrieves a particular title---
    public Cursor getTitle(long rowId) throws SQLException 
    {
        Cursor mCursor =
                db.query(true, DATABASE_TABLE, new String[] {
                		KEY_ROWID,
                		KEY_USERPASS, 
                		KEY_FRIEND,
                		KEY_CHAT
                		}, 
                		KEY_ROWID + "=" + rowId, 
                		null,
                		null, 
                		null, 
                		null, 
                		null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }

    //---updates a title---
    public boolean updateTitle(long rowId, String userpass, 
    String friend, String chat) 
    {
        ContentValues args = new ContentValues();
        args.put(KEY_USERPASS, userpass);
        args.put(KEY_FRIEND, friend);
        args.put(KEY_CHAT, chat);
        return db.update(DATABASE_TABLE, args, 
                         KEY_ROWID + "=" + rowId, null) > 0;
    }
}