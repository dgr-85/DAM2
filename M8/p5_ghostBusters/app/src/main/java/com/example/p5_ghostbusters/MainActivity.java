package com.example.p5_ghostbusters;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnStart=findViewById(R.id.btnStart);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startGame();
            }
        });
    }

    private void startGame(){
        Intent intent=new Intent(this, GhostHuntingActivity.class);
        EditText etUsername=findViewById(R.id.etUsername);
        String strUsername=etUsername.getText().toString();

        SharedPreferences prefs=getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor= prefs.edit();

        if(prefs.getInt(strUsername,-1)==-1){
            editor.putInt(strUsername,0);
            editor.apply();
        }
        intent.putExtra("username",strUsername);

        startActivity(intent);
    }
}