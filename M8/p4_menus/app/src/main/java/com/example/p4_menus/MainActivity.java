package com.example.p4_menus;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

        btnHide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSupportActionBar().hide();
            }
        });

        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSupportActionBar().show();
            }
        });

    }
}