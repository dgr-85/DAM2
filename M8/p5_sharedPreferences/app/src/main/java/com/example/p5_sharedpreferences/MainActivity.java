package com.example.p5_sharedpreferences;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    SharedPreferences prefs=getPreferences(Context.MODE_PRIVATE);
    SharedPreferences.Editor editor=prefs.edit();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnClear = findViewById(R.id.btnClear);

        Button btnSave = findViewById(R.id.btnSave);
        Button btnPhone=findViewById(R.id.btnPhone);
        EditText etName = findViewById(R.id.etName);
        EditText etPhone = findViewById(R.id.etPhone);

        AlertDialog.Builder builder=new AlertDialog.Builder(this);

        btnClear.setOnClickListener(view -> {
            etName.setText(null);
        });

        btnSave.setOnClickListener(view -> {
            validateForm(etName.getText().toString(), etPhone.getText().toString(),builder);
        });

        btnPhone.setOnClickListener(view -> {
            getPhoneNumber();
        });
    }

    private void validateForm(String name, String phone,AlertDialog.Builder builder) {

        //El camp per al nom és buit
        if (name.equals(null) || name.length() == 0) {
            builder.setTitle(getString(R.string.alertTitleCantSavePhone));
            builder.setMessage(getString(R.string.alertTextEmptyField, getString(R.string.etName)));
            builder.setPositiveButton(getString(R.string.alertButtonOK), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            builder.show();

            //El camp per al telèfon és buit
        } else if (phone.equals(null) || phone.length() == 0) {

            //El nom introduït no existeix a l'agenda -> no hi ha res a guardar
            if(prefs.getString(name,"none").equals("none")){
                builder.setTitle(getString(R.string.alertTitleCantDeletePhone));
                builder.setMessage(getString(R.string.alertTextNonExistingEntry, name));
                builder.setPositiveButton(getString(R.string.alertButtonOK), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.show();

            //El nom existeix -> esborrar entrada
            }else {
                builder.setTitle(getString(R.string.alertTitleWarning));
                builder.setMessage(getString(R.string.alertTextDeleteEntry, name));
                builder.setPositiveButton(getString(R.string.alertButtonOK), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        deleteEntry();
                    }
                });
                builder.setNegativeButton(getString(R.string.alertButtonCancel), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.show();
            }

            //Nom i telèfon estan emplenats
        } else {
            //El nom introduït ja existeix -> sobrescriure telèfon?
            if(!prefs.getString(name,"none").equals("none")){
                builder.setTitle(getString(R.string.alertTitleWarning));
                builder.setMessage(getString(R.string.alertTextOverwriteUser));
                builder.setPositiveButton(getString(R.string.alertTitleWarning), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        saveNewEntry(name,phone,builder);
                    }
                });

            //El nom no existeix a l'agenda -> gravar entrada nova
            }else {
                saveNewEntry(name, phone, builder);
            }
        }
    }
    private void saveNewEntry(String name, String phone,AlertDialog.Builder builder) {


        editor.putString(name,phone);
        editor.apply();

        builder.setTitle(getString(R.string.alertTitleEntrySaved));
        builder.setMessage(getString(R.string.alertTextNewEntrySaved));

    }

    private void deleteEntry(){

    }

    private void getPhoneNumber(){

    }
}