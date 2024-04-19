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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnClear = findViewById(R.id.btnClear);

        Button btnSave = findViewById(R.id.btnSave);
        Button btnPhone = findViewById(R.id.btnPhone);
        EditText etName = findViewById(R.id.etName);
        EditText etPhone = findViewById(R.id.etPhone);

        btnClear.setOnClickListener(view -> {
            etName.setText(null);
        });

        btnSave.setOnClickListener(view -> {
            validateForm(etName.getText().toString(), etPhone.getText().toString());
        });

        btnPhone.setOnClickListener(view -> {
            getPhoneNumber(etName.getText().toString(), etPhone);
        });
    }

    private void validateForm(String name, String phone) {

        //El camp per al nom és buit
        if (name == null || name.length() == 0) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
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
        } else if (phone == null || phone.length() == 0) {

            SharedPreferences prefs = getPreferences(Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();

            //El nom introduït no existeix a l'agenda -> no hi ha res a guardar
            if (prefs.getString(name, "none").equals("none")) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle(getString(R.string.alertTitleCantDeletePhone));
                builder.setMessage(getString(R.string.alertTextNonExistingEntry, name));
                builder.setPositiveButton(getString(R.string.alertButtonOK), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.show();

                //El nom existeix -> esborrar entrada?
            } else {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle(getString(R.string.alertTitleWarning));
                builder.setMessage(getString(R.string.alertTextDeleteEntry, name));
                builder.setPositiveButton(getString(R.string.alertButtonOK), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        deleteEntry(name);
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

            SharedPreferences prefs = getPreferences(Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();

            //El nom introduït ja existeix -> sobrescriure telèfon?
            if (!prefs.getString(name, "none").equals("none")) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle(getString(R.string.alertTitleWarning));
                builder.setMessage(getString(R.string.alertTextOverwriteUser, name));
                builder.setPositiveButton(getString(R.string.alertButtonOK), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        updateEntry(name, phone);
                    }
                });
                builder.setNegativeButton(getString(R.string.alertButtonCancel), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.show();

                //El nom no existeix a l'agenda -> gravar entrada nova
            } else {
                saveNewEntry(name, phone);
            }
        }
    }

    private void saveNewEntry(String name, String phone) {

        SharedPreferences prefs = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        editor.putString(name, phone);
        editor.apply();

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getString(R.string.alertTitleSuccess));
        builder.setMessage(getString(R.string.alertTextNewEntrySaved));
        builder.setPositiveButton(getString(R.string.alertButtonOK), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }

    private void updateEntry(String name, String phone) {

        SharedPreferences prefs = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        editor.putString(name, phone);
        editor.apply();

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getString(R.string.alertTitleSuccess));
        builder.setMessage(getString(R.string.alertTextEntryUpdated, name));
        builder.setPositiveButton(getString(R.string.alertButtonOK), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }

    private void deleteEntry(String name) {

        SharedPreferences prefs = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        editor.remove(name);
        editor.apply();

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getString(R.string.alertTitleSuccess));
        builder.setMessage(getString(R.string.alertTextEntryDeleted, name));
        builder.setPositiveButton(getString(R.string.alertButtonOK), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
    }

    private void getPhoneNumber(String name, EditText etPhone) {

        //El camp per al nom és buit -> no es pot recuperar res
        if (name == null || name.length() == 0) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(getString(R.string.alertTitleCantGetPhone));
            builder.setMessage(getString(R.string.alertTextEmptyField, getString(R.string.etName)));
            builder.setPositiveButton(getString(R.string.alertButtonOK), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            builder.show();
        } else {

            SharedPreferences prefs = getPreferences(Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();

            //El nom introduït no existeix a l'agenda -> no hi ha res a recuperar
            if (prefs.getString(name, "none").equals("none")) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle(getString(R.string.alertTitleCantGetPhone));
                builder.setMessage(getString(R.string.alertTextNonExistingEntry, name));
                builder.setPositiveButton(getString(R.string.alertButtonOK), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.show();

                //El nom existeix -> mostrar telèfon en alert i en camp per al telèfon
            } else {

                String phone = prefs.getString(name, "none");

                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle(getString(R.string.alertTitleSuccess));
                builder.setMessage(getString(R.string.alertTextEntryRetrieved, name, phone));
                builder.setPositiveButton(getString(R.string.alertButtonOK), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        etPhone.setText(phone);
                        dialog.cancel();
                    }
                });

                builder.show();
            }
        }
    }
}