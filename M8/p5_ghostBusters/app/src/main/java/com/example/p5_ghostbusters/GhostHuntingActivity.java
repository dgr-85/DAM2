package com.example.p5_ghostbusters;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class GhostHuntingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ghost_hunting);

        startGame();
    }

    private void startGame(){
        Bundle bundle=getIntent().getExtras();
        SharedPreferences prefs=getPreferences(Context.MODE_PRIVATE);

        TextView tvPlayerName=findViewById(R.id.tvPlayerName);
        String username=bundle.getString("username");
        tvPlayerName.setText(username);

        TextView tvCurrentScore=findViewById(R.id.tvCurrentScore);
        int playerScore=(prefs.getInt(username,0));
        tvCurrentScore.setText(String.valueOf(playerScore));

        ConstraintLayout zone=(ConstraintLayout) findViewById(R.id.gameZone);
        createGhost(zone);
    }

    private void createGhost(ConstraintLayout zone) {
        int maxWidth = zone.getMaxWidth();
        int maxHeight = zone.getMaxHeight();

        ImageView ghost = new ImageView(GhostHuntingActivity.this);
        ghost.setImageResource(R.drawable.ghost);
        ghost.setLayoutParams(new ConstraintLayout.LayoutParams(150, 150));
        ghost.setX((float) (Math.random() * maxWidth - 150));
        ghost.setY((float) (Math.random() * maxHeight - 150));

        ghost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                zone.removeView(ghost);
                createGhost(zone);
            }
        });

        zone.addView(ghost);
    }
}