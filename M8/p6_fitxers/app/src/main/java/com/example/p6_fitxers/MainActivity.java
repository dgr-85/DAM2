package com.example.p6_fitxers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
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
    int modelPrice;
    Spinner spSeries;
    Spinner spModel;
    RadioButton rbGsManual;
    RadioButton rbGsAuto;
    RadioButton rbFGasoline;
    RadioButton rbFDiesel;
    CheckBox cbPaint;
    CheckBox cbSeats;
    CheckBox cbGPS;
    boolean onStartHasBeenCalled;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView imageView = findViewById(R.id.images);
        imageView.setImageResource(R.drawable.logobmw);

        cbPaint = findViewById(R.id.cbPaint);
        cbSeats = findViewById(R.id.cbSeats);
        cbGPS = findViewById(R.id.cbGPS);

        CheckBox[] cbs = {cbPaint, cbSeats, cbGPS};
        for (CheckBox cb : cbs) {
            cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    calculatePrice(modelPrice);
                }
            });
        }

        RadioGroup rgGearShift = findViewById(R.id.rgGearShift);
        RadioGroup rgFuel = findViewById(R.id.rgFuel);

        rgGearShift.setOnCheckedChangeListener((radioGroup, i) -> calculatePrice(modelPrice));

        rgFuel.setOnCheckedChangeListener((radioGroup, i) -> calculatePrice(modelPrice));

        rbGsManual = findViewById(R.id.rbGsManual);
        rbFGasoline = findViewById(R.id.rbFGasoline);

        onStartHasBeenCalled = false;

        readFile();
    }

    @Override
    protected void onStart() {
        super.onStart();
        File p5File = new File(getApplicationContext().getFilesDir(), "p5File.txt");
        if (p5File.exists()) {
            try {
                InputStream inStream = getApplicationContext().openFileInput((p5File.getName()));
                BufferedReader reader = new BufferedReader(new InputStreamReader(inStream));
                spSeries.setSelection(((ArrayAdapter) spSeries.getAdapter()).getPosition(reader.readLine()));
                spModel.setSelection(Integer.parseInt(reader.readLine()));
                rbGsManual.setChecked(Boolean.parseBoolean(reader.readLine()));
                if (!rbGsManual.isChecked()) {
                    RadioButton rbGsAuto = findViewById(R.id.rbGsAuto);
                    rbGsAuto.setChecked(true);
                }
                rbFGasoline.setChecked(Boolean.parseBoolean(reader.readLine()));
                if (!rbFGasoline.isChecked()) {
                    RadioButton rbFDiesel = findViewById(R.id.rbFDiesel);
                    rbFDiesel.setChecked(true);
                }
                cbPaint.setChecked(Boolean.parseBoolean(reader.readLine()));
                cbSeats.setChecked(Boolean.parseBoolean(reader.readLine()));
                cbGPS.setChecked(Boolean.parseBoolean(reader.readLine()));
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            rbGsManual.setChecked(true);
            rbFGasoline.setChecked(true);
        }
    }

    public void readFile() {
        try {
            InputStream streamRaw = getResources().openRawResource(R.raw.datos_coches);
            BufferedReader reader = new BufferedReader(new InputStreamReader(streamRaw));
            String s;
            List<String> series = new ArrayList<>();
            while (!((s = reader.readLine()).equals("end"))) {
                series.add(s);
            }
            Map<String, List<String>> modelMap = new HashMap<>();
            Map<String, List<Integer>> priceMap = new HashMap<>();
            Map<String, List<String>> imageMap = new HashMap<>();

            for (String serie : series) {

                if (!modelMap.containsKey(serie)) {
                    modelMap.put(serie, new ArrayList<>());
                }

                if (!priceMap.containsKey(serie)) {
                    priceMap.put(serie, new ArrayList<>());
                }

                if (!imageMap.containsKey(serie)) {
                    imageMap.put(serie, new ArrayList<>());
                }

                while (!(s = reader.readLine()).equals("end")) {
                    modelMap.get(serie).add(s);
                    s = reader.readLine();
                    priceMap.get(serie).add(Integer.parseInt(s));
                    s = reader.readLine();
                    imageMap.get(serie).add(s);
                }
            }

            gearShift = Integer.parseInt(reader.readLine());
            fuelType = Integer.parseInt(reader.readLine());
            metallicPaint = Integer.parseInt(reader.readLine());
            leatherSeating = Integer.parseInt(reader.readLine());
            navigationSystem = Integer.parseInt(reader.readLine());

            streamRaw.close();

            setSpinners((ArrayList<String>) series, modelMap, priceMap, imageMap);

        } catch (Exception e) {
            Log.e(getString(R.string.tagReadFile), getString(R.string.errorReadFile));
        }
    }

    public void setSpinners(ArrayList<String> series, Map<String, List<String>> model, Map<String, List<Integer>> price, Map<String, List<String>> image) {
        spSeries = findViewById(R.id.spSeries);
        spModel = findViewById(R.id.spModel);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, series);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spSeries.setAdapter(adapter);

        spSeries.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ArrayAdapter seriesAdapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_spinner_item, model.get(parent.getItemAtPosition(position)));
                seriesAdapter.setDropDownViewResource((android.R.layout.simple_spinner_dropdown_item));
                spModel.setAdapter(seriesAdapter);

                if (!onStartHasBeenCalled) {
                    onStart();
                    onStartHasBeenCalled = true;
                }
            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spModel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String strModel = spSeries.getSelectedItem().toString();
                String strImage = image.get(strModel).get(position);
                int imageId = getResources().getIdentifier(strImage, "drawable", getPackageName());
                ImageView imageView = findViewById(R.id.images);
                imageView.setImageResource(imageId);

                modelPrice = price.get(strModel).get(position);

                calculatePrice(modelPrice);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void calculatePrice(int basePrice) {
        rbGsAuto = findViewById(R.id.rbGsAuto);
        rbFDiesel = findViewById(R.id.rbFDiesel);
        int totalPrice = basePrice;

        if (rbGsAuto.isChecked()) {
            totalPrice += gearShift;
        }
        if (rbFDiesel.isChecked()) {
            totalPrice += fuelType;
        }

        CheckBox cbPaint = findViewById(R.id.cbPaint);
        CheckBox cbSeats = findViewById(R.id.cbSeats);
        CheckBox cbGPS = findViewById(R.id.cbGPS);

        if (cbPaint.isChecked()) {
            totalPrice += metallicPaint;
        }
        if (cbSeats.isChecked()) {
            totalPrice += leatherSeating;
        }
        if (cbGPS.isChecked()) {
            totalPrice += navigationSystem;
        }

        TextView tvPrice = findViewById(R.id.tvPrice);
        tvPrice.setText(String.valueOf(totalPrice));
    }

    @Override
    protected void onStop() {
        super.onStop();
        File p5File = new File(getApplicationContext().getFilesDir(), "p5File.txt");
        try (FileOutputStream outStream = getApplicationContext().openFileOutput(p5File.getName(), Context.MODE_PRIVATE)) {

            outStream.write((spSeries.getSelectedItem().toString() + "\n").getBytes());
            outStream.write((spModel.getSelectedItemPosition() + "\n").getBytes());

            outStream.write((rbGsManual.isChecked() + "\n").getBytes());
            outStream.write((rbFGasoline.isChecked() + "\n").getBytes());

            outStream.write(((cbPaint.isChecked()) + "\n").getBytes());
            outStream.write(((cbSeats.isChecked()) + "\n").getBytes());
            outStream.write(((cbGPS.isChecked()) + "\n").getBytes());

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}