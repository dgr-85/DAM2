package com.example.p4_menus;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar=(Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Button btnHide=findViewById(R.id.btnHideToolbar);
        Button btnShow=findViewById(R.id.btnShowToolbar);
        btnShow.setEnabled(false);

        btnHide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSupportActionBar().hide();
                btnHide.setEnabled(false);
                btnShow.setEnabled(true);
            }
        });

        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSupportActionBar().show();
                btnHide.setEnabled(true);
                btnShow.setEnabled(false);
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==R.id.action_favorite) {
            String option=getString(R.string.option_selected)+" "+getString(R.string.action_favorite);
            Toast.makeText(this, option, Toast.LENGTH_SHORT).show();
            return true;
        } else if (item.getItemId()==R.id.action_settings) {
            String option=getString(R.string.option_selected)+" "+getString(R.string.action_settings);
            Toast.makeText(this, option, Toast.LENGTH_SHORT).show();
            return true;
        }else{
            return super.onOptionsItemSelected(item);
        }
    }
}