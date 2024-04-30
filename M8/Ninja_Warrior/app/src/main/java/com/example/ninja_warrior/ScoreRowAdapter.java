package com.example.ninja_warrior;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CursorAdapter;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;
import java.util.zip.Inflater;

public class ScoreRowAdapter extends BaseAdapter {

    LayoutInflater inflater;
    private Context applicationContext;
    private Map<String, Integer> playersScores;
    private String[] names;

    public ScoreRowAdapter(Context applicationContext, Map<String, Integer> playersScores) {
        this.applicationContext = applicationContext;
        this.playersScores = playersScores;
        this.names = playersScores.keySet().toArray(new String[playersScores.size()]);
        inflater = LayoutInflater.from(applicationContext);
    }

    @Override
    public int getCount() {
        return playersScores.size();
    }

    @Override
    public Object getItem(int position) {
        return playersScores.get(names[position]);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.score_list, null);
        }
        TextView tvName = convertView.findViewById(R.id.tvName);
        TextView tvScore = convertView.findViewById(R.id.tvScore);

        tvName.setText(names[position]);
        tvScore.setText(getItem(position).toString());
        return convertView;
    }
}