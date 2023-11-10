package com.example.p2_lordrings;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
        if(nameInput.isEmpty()){
            Toast toast=Toast.makeText(this,"You must enter your name", Toast.LENGTH_SHORT);
            toast.show();
        }
        else {
            int score = 0;

            for (int i = 0; i < users.length; i++) {
                if (nameInput.equals(users[i])) {
                    score = points[i];
                }
            }

            Intent intent = new Intent(this, DisplayNameActivity.class);
            intent.putExtra("username", nameInput);
            intent.putExtra("score", score);
            startActivity(intent);
        }
    }
}