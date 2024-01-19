package com.example.books;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] isbns={"9788499301518","9788467009477","9788425343537","9788423342518"};
        String[] titles={"Harry Potter y la piedra filosofal","La luz y el misterio de las catedrales","La catedral del mar","Lo que esconde tu nombre"};
        String[] authors={"J.K. Rowling","Jose Maria Peridis Perez","Ildefonso Falcones","Clara Sanchez"};

        BooksSQLiteHelper helper=new BooksSQLiteHelper(this);
        SQLiteDatabase dbInsert=helper.getWritableDatabase();

        for(int i=0;i<isbns.length;i++){
            ContentValues cv=new ContentValues();
            cv.put(BooksDatabaseContract.BooksTable.COLUMN_ISBN,isbns[i]);
            cv.put(BooksDatabaseContract.BooksTable.COLUMN_TITLE,titles[i]);
            cv.put(BooksDatabaseContract.BooksTable.COLUMN_AUTHOR,authors[i]);
            dbInsert.insert(BooksDatabaseContract.BooksTable.TABLE,null,cv);
        }

        dbInsert.close();

        String[] projection={
                BooksDatabaseContract.BooksTable.COLUMN_ISBN,
                BooksDatabaseContract.BooksTable.COLUMN_TITLE
        };

        SQLiteDatabase dbFindAll=helper.getReadableDatabase();

        Cursor cursor=dbFindAll.query(BooksDatabaseContract.BooksTable.TABLE,projection,null,null,null,null,null);

        ArrayAdapter<String> adapter=new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        List<String[]> result=new ArrayList<>();
        cursor.moveToFirst();
        result.add(new String[]{cursor.getString(cursor.getColumnIndexOrThrow(BooksDatabaseContract.BooksTable.COLUMN_TITLE)),
                cursor.getString((cursor.getColumnIndexOrThrow(BooksDatabaseContract.BooksTable.COLUMN_ISBN)))});

        while(cursor.moveToNext()){
            result.add(new String[]{cursor.getString(cursor.getColumnIndexOrThrow(BooksDatabaseContract.BooksTable.COLUMN_TITLE)),
                    cursor.getString((cursor.getColumnIndexOrThrow(BooksDatabaseContract.BooksTable.COLUMN_ISBN)))});
        }

        for(String[] str:result){
            for(String s:str){
                adapter.addAll(s);
            }
        }
        ListView listView=findViewById(R.id.listBooks);
        listView.setAdapter(adapter);
    }
}