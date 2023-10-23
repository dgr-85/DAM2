package com.example.p2_lordrings;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private String users[] = {"gandalf", "frodo", "saruman"};
    private int points[] = {315, 222, 489};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnEnterName=findViewById(R.id.btnCheckName);
        btnEnterName.setOnClickListener(view -> onBtnCheckClick());
    }

    public void onBtnCheckClick(){
        EditText etNameInput=findViewById(R.id.etName);
        String nameInput=etNameInput.getText().toString().toLowerCase().trim();
        int score=0;

        for(int i=0;i<users.length;i++){
            if(nameInput.equals(users[i])){
                score=points[i];
            }
        }

        Intent intent=new Intent(this, DisplayName.class);
        intent.putExtra("username",nameInput);
        intent.putExtra("score",score);
        startActivity(intent);
    }
}