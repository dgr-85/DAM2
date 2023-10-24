package com.example.p3_addorsubtract;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RadioGroup radioGroup=findViewById(R.id.rgOperations);
        EditText etFirstNumber=(EditText) findViewById(R.id.etFirstNumber);
        EditText etSecondNumber=(EditText) findViewById(R.id.etSecondNumber);
        Button btnAdd=findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validateForm(radioGroup,etFirstNumber,etSecondNumber);
            }
        });
    }

    private void validateForm(RadioGroup rg,EditText num1,EditText num2){

        String alertTitle="Unable to perform the operation";
        String alertMsgEmptyRadioButtons="You must check a button to either add or subtract.";
        String alertMsgEmptyNumberFields="You must fill both number fields.";

        if(rg.getCheckedRadioButtonId()==-1){
            createAlertDialog(alertTitle,alertMsgEmptyRadioButtons);
        } else if (TextUtils.isEmpty(num1.getText().toString()) || TextUtils.isEmpty(num2.getText().toString())) {
            createAlertDialog(alertTitle,alertMsgEmptyNumberFields);
        }else{
            performOperation(rg.getCheckedRadioButtonId(),num1,num2);
        }
    }

    private void createAlertDialog(String title,String message){
        AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton("OK", (DialogInterface.OnClickListener)(dialog,which)->{
            dialog.cancel();
        });
        builder.create().show();
    }

    private void performOperation(int radioButtonId,EditText num1,EditText num2){
        createAlertDialog("CheckedButtonId",String.valueOf(radioButtonId));
    }

}