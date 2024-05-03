package com.example.ninja_warrior;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScoreActivity extends AppCompatActivity {

    ListView listView;
    Map<String,Integer> playersScores=new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.title_score);
        setSupportActionBar(toolbar);

        playersScores.put("Christine",15);
        playersScores.put("Alex",12);
        playersScores.put("Andrew",10);
        playersScores.put("Tony",1);


        listView = findViewById(R.id.list);
        ScoreRowAdapter adapter = new ScoreRowAdapter(getApplicationContext(), playersScores);
        listView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId()==R.id.menu_info){
            AlertDialog.Builder builder=new AlertDialog.Builder(this);
            builder.setTitle(getString(R.string.info_title));
            builder.setMessage(getString(R.string.info_instructions)+System.lineSeparator()+getString(R.string.info_made_by));
            builder.setPositiveButton(getString(R.string.info_ok), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            builder.show();
        }
        if(item.getItemId()==R.id.menu_config){
            startActivity(new Intent(this, PreferencesActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }
}