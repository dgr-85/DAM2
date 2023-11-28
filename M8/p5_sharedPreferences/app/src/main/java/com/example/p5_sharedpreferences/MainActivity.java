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

        btnClear.setOnClickListener(view -> {
            etName.setText(null);
        });

        btnSave.setOnClickListener(view -> {
            validateForm(etName.getText().toString(), etPhone.getText().toString());
        });

        btnPhone.setOnClickListener(view -> {
            getPhoneNumber();
        });
    }

    private void validateForm(String name, String phone) {

        if (name.equals(null) || name.length() == 0) {
            createAlertDialog(getString(R.string.alertTitleCantSavePhone), getString(R.string.alertTextEmptyName, getString(R.string.etName)), false);
        } else if (phone.equals(null) || phone.length() == 0) {
            createAlertDialog(getString(R.string.alertTitleWarning), getString(R.string.alertTextDeleteEntry, name), true);
        } else {
            saveNewEntry(name,phone);
        }
    }

    private void createAlertDialog(String title, String message, Boolean asksForConfirmation) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setCancelable(false);
        if (!asksForConfirmation) {
            builder.setPositiveButton(getString(R.string.alertButtonOK), (DialogInterface.OnClickListener) (dialog, which) -> {
                dialog.cancel();
            });
        } else {
            builder.setPositiveButton(getString(R.string.alertButtonOK), (DialogInterface.OnClickListener) (dialog, which) -> {
                dialog.dismiss();
            });
            builder.setNegativeButton(getString(R.string.alertButtonCancel), (DialogInterface.OnClickListener) (dialog, which) -> {
                dialog.cancel();
            });
        }

        builder.create().show();
    }

    private void saveNewEntry(String name, String phone) {

        if(!prefs.getString(name,"none").equals("none")){
            createAlertDialog(getString(R.string.alertTitleWarning),getString(R.string.alertTextOverwriteUser),true);
        }
        editor.putString(name,phone);
        editor.commit();

        createAlertDialog(getString(R.string.alertTitleEntrySaved),getString(R.string.alertTextNewEntrySaved),false);

    }

    private void getPhoneNumber(){

    }
}