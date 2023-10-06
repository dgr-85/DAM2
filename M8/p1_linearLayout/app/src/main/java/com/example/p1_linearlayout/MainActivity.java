package com.example.p1_linearlayout;

import static android.widget.LinearLayout.*;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {

    LinearLayout linearLayout;
    int buttonNumber=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        linearLayout=findViewById(R.id.linearLayout);

        addButton(linearLayout);
    }

    public void addButton(LinearLayout linearLayout){
        MaterialButton button = new MaterialButton(this);
        button.setText(Integer.toString(buttonNumber++));
        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                addButton(linearLayout);
            }
        });
        linearLayout.addView(button);
    }
}