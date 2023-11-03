package com.example.p3_addandsubtract;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText etFirstNumber=findViewById(R.id.etFirstNumber);
        EditText etSecondNumber=findViewById(R.id.etSecondNumber);

        CheckBox cbAdd=findViewById(R.id.cbAdd);
        CheckBox cbSubtract=findViewById(R.id.cbSubtract);

        TextView result=findViewById(R.id.tvResult);
        Button btnOperate=findViewById(R.id.btnOperate);
        Button btnClear=findViewById(R.id.btnClear);

        btnOperate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                result.setText(null);
                validateForm(cbAdd,cbSubtract,etFirstNumber,etSecondNumber);
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                etFirstNumber.getText().clear();
                etSecondNumber.getText().clear();
                cbAdd.setChecked(false);
                cbSubtract.setChecked(false);
                result.setText(null);
            }
        });
    }
    private void validateForm(CheckBox add, CheckBox subtract, EditText etNum1,EditText etNum2){

        String alertMsgEmptyCheckBoxes="You must check at least one operation option";
        String alertMsgEmptyNumberFields="You must fill both number fields";

        if(!add.isChecked() && !subtract.isChecked()){
            Toast toast= Toast.makeText(this,alertMsgEmptyCheckBoxes,Toast.LENGTH_LONG);
            toast.show();
        } else if (TextUtils.isEmpty(etNum1.getText()) || TextUtils.isEmpty(etNum2.getText())){
            Toast toast= Toast.makeText(this,alertMsgEmptyNumberFields,Toast.LENGTH_LONG);
            toast.show();
        }else{
            int num1=Integer.parseInt(etNum1.getText().toString());
            int num2=Integer.parseInt(etNum2.getText().toString());
            performOperations(add,subtract,num1,num2);
        }
    }

    private void performOperations(CheckBox add, CheckBox subtract,int num1,int num2){
        TextView result=findViewById(R.id.tvResult);

        if(add.isChecked() && subtract.isChecked()){
            result.setText("Add = "+(num1+num2)+"\nSubtract = "+(num1-num2));
        }
        else if(add.isChecked()){
            result.setText("Add = "+(num1+num2)+"\n");
        }
        else if(subtract.isChecked()){
            result.setText("Subtract = "+(num1-num2));
        }else{
            result.setText("ERROR");
        }
    }
}