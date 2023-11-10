package com.example.p2_lordrings;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DisplayNameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_name);

        getResults();

        Button btnClose=findViewById(R.id.btnClose);
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    
    public void getResults(){
        Bundle bundle=getIntent().getExtras();
        String username=capitalizeName(bundle.getString("username"));
        String score=String.valueOf(bundle.getInt("score"));

        TextView greetPlayer=findViewById(R.id.tvLore1);
        greetPlayer.append(username+"!");
        
        TextView showPlayerScore=findViewById(R.id.tvLore2);
        showPlayerScore.append(score);
    }

    public String capitalizeName(String improperName){
        String[] separateNames=improperName.split(" ");
        for(int i=0;i<separateNames.length;i++){
            separateNames[i]=separateNames[i].substring(0,1).toUpperCase()+separateNames[i].substring(1).toLowerCase();
        }
        return String.join(" ",separateNames);
    }
}