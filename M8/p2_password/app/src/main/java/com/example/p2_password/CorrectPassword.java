package com.example.p2_password;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CorrectPassword extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_correct_password);

        makeWelcome();

        Button btnClose=findViewById(R.id.btnClose);
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void makeWelcome(){
        Bundle bundle=getIntent().getExtras();
        String username=bundle.getString("username");

        TextView greet=findViewById(R.id.tvWelcome);
        greet.append(" "+username+"!");
    }
}