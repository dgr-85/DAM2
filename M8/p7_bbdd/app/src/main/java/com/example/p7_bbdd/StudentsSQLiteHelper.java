package com.example.p7_bbdd;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class StudentsSQLiteHelper extends SQLiteOpenHelper{
        public static final int DATABASE_VERSION = 1;
        public static final String DATABASE_NAME = "Students.db";

        private static final String SQL_CREATE_TABLES =
                "CREATE TABLE " + StudentsDatabaseContract.StudentsTable.TABLE + " ("
                        + StudentsDatabaseContract.StudentsTable._ID + " INTEGER PRIMARY KEY, "
                        + StudentsDatabaseContract.StudentsTable.COLUMN_ID_CARD + " TEXT, "
                        + StudentsDatabaseContract.StudentsTable.COLUMN_NAME + " TEXT, "
                        + StudentsDatabaseContract.StudentsTable.COLUMN_SURNAME + " TEXT, "
                        + StudentsDatabaseContract.StudentsTable.COLUMN_CYCLE + " TEXT, "
                        + StudentsDatabaseContract.StudentsTable.COLUMN_COURSE + " TEXT)";

        private static final String SQL_DROP_TABLES =
                "DROP TABLE IF EXISTS " + StudentsDatabaseContract.StudentsTable.TABLE;

        public StudentsSQLiteHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(SQL_CREATE_TABLES);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL(SQL_DROP_TABLES);
            onCreate(db);
        }

        @Override
        public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            onUpgrade(db, oldVersion, newVersion);
        }
    }
