package com.example.p6_fitxers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    int gearShift;
    int fuelType;
    int metallicPaint;
    int leatherSeating;
    int navigationSystem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView imageView=findViewById(R.id.images);
        imageView.setImageResource(R.drawable.logobmw);

        Context context=getApplicationContext();

        if(fileExists(context)){

        }

        RadioButton rbGsManual=findViewById(R.id.rbGsManual);
        RadioButton rbFGasoline=findViewById(R.id.rbFGasoline);
        rbGsManual.setChecked(true);
        rbFGasoline.setChecked(true);

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

            gearShift=Integer.parseInt(reader.readLine());
            fuelType=Integer.parseInt(reader.readLine());
            metallicPaint=Integer.parseInt(reader.readLine());
            leatherSeating=Integer.parseInt(reader.readLine());
            navigationSystem=Integer.parseInt(reader.readLine());

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
                String strModel=spSeries.getSelectedItem().toString();
                String strImage=image.get(strModel).get(position);
                int imageId=getResources().getIdentifier(strImage,"drawable",getPackageName());
                ImageView imageView=findViewById(R.id.images);
                imageView.setImageResource(imageId);

                int modelPrice=price.get(strModel).get(position);

                calculatePrice(modelPrice);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void calculatePrice(int basePrice){
        RadioButton rbGsAuto=findViewById(R.id.rbGsAuto);
        RadioButton rbFDiesel=findViewById(R.id.rbFDiesel);
        int totalPrice=basePrice;

        if(rbGsAuto.isChecked()){
            totalPrice+=gearShift;
        }
        if(rbFDiesel.isChecked()){
            totalPrice+=fuelType;
        }

        CheckBox cbPaint=findViewById(R.id.cbPaint);
        CheckBox cbSeats=findViewById(R.id.cbSeats);
        CheckBox cbGPS=findViewById(R.id.cbGPS);

        if(cbPaint.isChecked()){
            totalPrice+=metallicPaint;
        }
        if(cbSeats.isChecked()){
            totalPrice+=leatherSeating;
        }
        if(cbGPS.isChecked()){
            totalPrice+=navigationSystem;
        }

        TextView tvPrice=findViewById(R.id.tvPrice);
        tvPrice.setText(String.valueOf(totalPrice));
    }

    public boolean fileExists(Context context){
        try{
            FileOutputStream p5File=context.openFileOutput("p5File",Context.MODE_PRIVATE);
            p5File.close();
            return true;
        }catch(Exception e){
            return false;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}