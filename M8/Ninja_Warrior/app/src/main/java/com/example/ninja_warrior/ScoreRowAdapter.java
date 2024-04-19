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
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.score_list, null);
        TextView tvName = convertView.findViewById(R.id.tvName);
        TextView tvScore = convertView.findViewById(R.id.tvScore);
        tvName.setText(players[position]);
        tvScore.setText(scores[position]);
        return convertView;
    }
}