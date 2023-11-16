package com.example.p4_menus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Button btnHide=findViewById(R.id.btnHideToolbar);
        Button btnShow=findViewById(R.id.btnShowToolbar);
        btnShow.setEnabled(false);

        btnHide.setOnClickListener((view) -> {
                Objects.requireNonNull(getSupportActionBar()).hide();
                btnHide.setEnabled(false);
                btnShow.setEnabled(true);
        });

        btnShow.setOnClickListener((view)-> {
                Objects.requireNonNull(getSupportActionBar()).show();
                btnHide.setEnabled(true);
                btnShow.setEnabled(false);
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
        }else if (item.getItemId()==R.id.action_home){
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.institutmarianao.cat/ca/")));
            return true;
        }else if(item.getItemId()==R.id.action_code){
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.codewars.com/")));
            return true;
        }else{
            return super.onOptionsItemSelected(item);
        }
    }
}