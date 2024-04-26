package com.example.ninja_warrior;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CursorAdapter;
import android.widget.TextView;

import java.util.zip.Inflater;

public class ScoreRowAdapter extends BaseAdapter {

    Context context;
    String[] players;
    int[] scores;
    LayoutInflater inflater;

    public ScoreRowAdapter(Context applicationContext, String[] players, int[] scores) {
        this.context = context;
        this.players = players;
        this.scores = scores;
        inflater = LayoutInflater.from(applicationContext);
    }

    @Override
    public int getCount() {
        return players.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null) {
            convertView = inflater.inflate(R.layout.activity_score, null);
        }
        TextView tvName = convertView.findViewById(R.id.tvName);
        TextView tvScore = convertView.findViewById(R.id.tvScore);
        tvName.setText(players[position]);
        tvScore.setText(scores[position]);
        return convertView;
    }
}