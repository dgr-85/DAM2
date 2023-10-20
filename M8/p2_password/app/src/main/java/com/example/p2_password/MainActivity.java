package com.example.p2_password;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnCheck=findViewById(R.id.btnCheck);
        btnCheck.setOnClickListener(view -> on_BtnCheckClick());
    }

    public void on_BtnCheckClick(){
        String pass="abc123";

        EditText etInputPass=findViewById(R.id.etPassword);
        String strEtInputPass=etInputPass.getText().toString();
        EditText etInputUsername=findViewById(R.id.etUsername);

        if(strEtInputPass.equals(pass)){
            findViewById(R.id.tvWrongPassword).setVisibility(View.INVISIBLE);
            Intent intent=new Intent(this,CorrectPassword.class);
            intent.putExtra("username",etInputUsername.getText().toString());
            startActivity(intent);
        }else{
            findViewById(R.id.tvWrongPassword).setVisibility(View.VISIBLE);
        }
    }
}