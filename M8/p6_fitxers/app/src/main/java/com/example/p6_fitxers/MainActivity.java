package com.example.p6_fitxers;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        readFile();
    }

    public void readFile(){
        try {
            InputStream streamRaw = getResources().openRawResource(R.raw.datos_coches);
            BufferedReader reader = new BufferedReader(new InputStreamReader(streamRaw));
            String str;
            List<String> series=new ArrayList<>();
            Map<String,List<String>> cars = new HashMap<>();
            while(!((str = reader.readLine()).equals("end"))){
                series.add(str);
            }
            while((str=reader.readLine())!=null){

            }
            streamRaw.close();
        } catch (Exception ex) {
            Log.e("Files", " Error reading file from raw resource.");
        }
    }
}