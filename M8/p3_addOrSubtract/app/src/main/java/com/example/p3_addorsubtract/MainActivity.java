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
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RadioGroup radioGroup=findViewById(R.id.rgOperations);

        EditText etFirstNumber=findViewById(R.id.etFirstNumber);
        EditText etSecondNumber=findViewById(R.id.etSecondNumber);

        TextView result=findViewById(R.id.tvResult);

        Button btnAdd=findViewById(R.id.btnAdd);
        Button btnClear=findViewById(R.id.btnClear);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                result.setText(null);
                validateForm(radioGroup,etFirstNumber,etSecondNumber);
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                etFirstNumber.getText().clear();
                etSecondNumber.getText().clear();
                radioGroup.clearCheck();
                result.setText(null);
            }
        });
    }

    private void validateForm(RadioGroup rg,EditText etNum1,EditText etNum2){

        String alertTitle="Unable to perform the operation";
        String alertMsgEmptyRadioButtons="You must check a button to either add or subtract.";
        String alertMsgEmptyNumberFields="You must fill both number fields.";

        if(rg.getCheckedRadioButtonId()==-1){
            createAlertDialog(alertTitle,alertMsgEmptyRadioButtons);
        } else if (TextUtils.isEmpty(etNum1.getText()) || TextUtils.isEmpty(etNum2.getText())) {
            createAlertDialog(alertTitle,alertMsgEmptyNumberFields);
        }else{
            int num1=Integer.parseInt(etNum1.getText().toString());
            int num2=Integer.parseInt(etNum2.getText().toString());
            performOperation(rg,num1,num2);
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

    private void performOperation(RadioGroup radioGroup,int num1,int num2){
        RadioButton radioButtonChecked=findViewById(radioGroup.getCheckedRadioButtonId());
        TextView result=findViewById(R.id.tvResult);

        if(radioButtonChecked.equals(findViewById(R.id.rBtnSubtract))){
            result.setText("Subtract = "+(num1-num2));
        }else if(radioButtonChecked.equals(findViewById(R.id.rBtnAdd))){
            result.setText("Add = "+(num1+num2));
        }else{
            result.setText("ERROR");
        }



    }

}