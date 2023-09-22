package com.example.calculadora;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private EditText etVal1;
    private EditText etVal2;
    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etVal1 = findViewById(R.id.etValue1);
        etVal2 = findViewById(R.id.etValue2);
        tvResult = findViewById(R.id.tvResult);
    }

    public void bAddOnClick(View view) {
        int val1 = Integer.parseInt(etVal1.getText().toString());
        int val2 = Integer.parseInt(etVal2.getText().toString());

        int sum = val1 + val2;
        tvResult.setText(String.format(new Locale("es", "ES"),"%,d", sum));
    }

}