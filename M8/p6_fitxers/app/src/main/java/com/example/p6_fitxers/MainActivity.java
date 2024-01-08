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
            String s;
            List<String> series=new ArrayList<>();
            while(!((s = reader.readLine()).equals("end"))){
                series.add(s);
            }
            Map<String,List<String>> modelMap=new HashMap<>();
            Map<String,List<Integer>> priceMap=new HashMap<>();
            Map<String,List<String>> imageMap=new HashMap<>();

            for(String serie:series){

                if(!modelMap.containsKey(serie)){
                    modelMap.put(serie,new ArrayList<>());
                }

                if(!priceMap.containsKey(serie)){
                    priceMap.put(serie,new ArrayList<>());
                }

                if(!imageMap.containsKey(serie)){
                    imageMap.put(serie,new ArrayList<>());
                }

                while(!((s = reader.readLine()).equals("end"))){
                    modelMap.get(serie).add(s);
                    priceMap.get(serie).add(Integer.parseInt(s));
                    imageMap.get(serie).add(s);
                }
            }
            streamRaw.close();
        } catch (Exception e) {
            Log.e(getString(R.string.tagReadFile), getString(R.string.errorReadFile));
        }
    }
}