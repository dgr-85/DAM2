package com.example.books;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class BooksSQLiteHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Books.db";

    public BooksSQLiteHelper(Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    private static final String SQL_CREATE_TABLE="CREATE TABLE "+BooksDatabaseContract.BooksTable.TABLE+"("
            +BooksDatabaseContract.BooksTable._ID+" INTEGER PRIMARY KEY,"
            +BooksDatabaseContract.BooksTable.COLUMN_ISBN+" TEXT, "
            +BooksDatabaseContract.BooksTable.COLUMN_TITLE+" TEXT, "
            +BooksDatabaseContract.BooksTable.COLUMN_AUTHOR+" TEXT)";

    private static final String SQL_DROP_TABLE="DROP TABLE IF EXISTS "+BooksDatabaseContract.BooksTable.TABLE;


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DROP_TABLE);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion){
        onUpgrade(db,oldVersion,newVersion);
    }
}
