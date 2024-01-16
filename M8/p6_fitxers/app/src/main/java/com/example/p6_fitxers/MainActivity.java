package com.example.p6_fitxers;

import androidx.appcompat.app.AppCompatActivity;

import android.opengl.EGLExt;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

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

                while(!(s = reader.readLine()).equals("end")){
                    modelMap.get(serie).add(s);
                    s = reader.readLine();
                    priceMap.get(serie).add(Integer.parseInt(s));
                    s = reader.readLine();
                    imageMap.get(serie).add(s);
                }
            }
            streamRaw.close();

            setSpinners((ArrayList<String>) series,modelMap,priceMap,imageMap);
        } catch (Exception e) {
            Log.e(getString(R.string.tagReadFile), getString(R.string.errorReadFile));
        }
    }

    public void setSpinners(ArrayList<String> series,Map<String,List<String>> model,Map<String,List<Integer>> price,Map<String,List<String>> image){
        Spinner spSeries=findViewById(R.id.spSeries);
        Spinner spDetails=findViewById(R.id.spModel);

        ArrayAdapter<String> adapter=new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,series);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spSeries.setAdapter(adapter);

        spSeries.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ArrayAdapter seriesAdapter=new ArrayAdapter(MainActivity.this, android.R.layout.simple_spinner_item,model.get(parent.getItemAtPosition(position)));
                seriesAdapter.setDropDownViewResource((android.R.layout.simple_spinner_dropdown_item));
                spDetails.setAdapter(seriesAdapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spDetails.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                List<String> listModels=model.get(parent.getItemAtPosition(position));
                //int imageId=getResources().getIdentifier(listModels.get(indexOf(Integer.parseInt(String.valueOf(id)))),"drawable",getPackageName());
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}