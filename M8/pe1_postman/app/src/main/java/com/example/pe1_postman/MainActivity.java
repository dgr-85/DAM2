package com.example.pe1_postman;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.Toolbar;

import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Preparació de spinner de províncies
        Spinner spinnerProvince=findViewById(R.id.spProvince);
        ArrayAdapter<String> adapterProvince=new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, Collections.singletonList("Barcelona"));
        adapterProvince.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerProvince.setAdapter(adapterProvince);

        //Preparació de spinner de ciutats
        Spinner spinnerCity=findViewById(R.id.spCity);
        ArrayAdapter<String> adapterCity=new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,Data.BARCELONA_CITIES);
        adapterCity.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCity.setAdapter(adapterCity);

        //Obtenció del codi postal corresponent a la ciutat escollida
        spinnerCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                EditText etZip=findViewById(R.id.etZipCode);
                etZip.setText(Data.BARCELONA_ZIPS[i]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //Recollida de camps obligatoris
        EditText etName=findViewById(R.id.etName);
        EditText etSurname=findViewById(R.id.etSurname);
        EditText etAddress=findViewById(R.id.etAddress);

        //Preparació de botó Submit
        Button btnSubmit=findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(view -> {
            validateForm(etName,etSurname,etAddress);
        });
    }

    private void validateForm(EditText name,EditText surname,EditText address){
        boolean allIsOk=true;
        if(TextUtils.isEmpty(name.getText())){
            name.setError(getString(R.string.errorField));
            allIsOk=false;
        }
        if(TextUtils.isEmpty(surname.getText())){
            surname.setError(getString(R.string.errorField));
            allIsOk=false;
        }
        if(TextUtils.isEmpty(address.getText())){
            address.setError(getString(R.string.errorField));
            allIsOk=false;
        }
        if(!allIsOk){
            Toast.makeText(this,getString(R.string.badForm),Toast.LENGTH_LONG).show();
        }else{
            Intent intent=new Intent(this, SubmittedFormActivity.class);
            intent.putExtra("name",name.getText().toString());
            intent.putExtra("surname",surname.getText().toString());
            intent.putExtra("address",address.getText().toString());

            Spinner province=findViewById(R.id.spProvince);
            String strProvince=province.getSelectedItem().toString();
            intent.putExtra("province",strProvince);

            Spinner city=findViewById(R.id.spCity);
            String strCity=city.getSelectedItem().toString();
            intent.putExtra("city",strCity);

            EditText zip=findViewById(R.id.etZipCode);
            String strZip=zip.getText().toString();
            intent.putExtra("zipCode",strZip);

            EditText mail=findViewById(R.id.etEmail);
            String strMail=mail.getText().toString();
            intent.putExtra("email",strMail);

            EditText phone=findViewById(R.id.etEmail);
            String strPhone=phone.getText().toString();
            intent.putExtra("phone",strPhone);

            startActivity(intent);
        }
    }
}