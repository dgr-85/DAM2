package com.example.p2_author;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnAuthor=findViewById(R.id.btnAuthor);
        btnAuthor.setOnClickListener(view -> on_BtnAuthorClick());
    }

    public void on_BtnAuthorClick(){
        startActivity(new Intent(this, AuthorInfo.class));
    }
}