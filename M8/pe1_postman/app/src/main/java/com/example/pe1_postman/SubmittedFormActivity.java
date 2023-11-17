package com.example.pe1_postman;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class SubmittedFormActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submitted_form);

        showResults();

        Button btnBack=findViewById(R.id.btnBack);
        btnBack.setOnClickListener(view -> {
            finish();
        });
    }

    private void showResults(){
        Bundle bundle=getIntent().getExtras();
        TextView name=findViewById(R.id.tvName);
        TextView surname=findViewById(R.id.tvSurname);
        TextView address=findViewById(R.id.tvAddress);
        TextView province=findViewById(R.id.tvProvince2);
        TextView city=findViewById(R.id.tvCity2);
        TextView zipCode=findViewById(R.id.tvZipCode);
        TextView email=findViewById(R.id.tvEmail);
        TextView phone=findViewById(R.id.tvPhone2);

        name.setText(getString(R.string.etName)+": "+bundle.getString("name"));
        surname.setText(getString(R.string.etSurname)+": "+bundle.getString("surname"));
        address.setText(getString(R.string.etAddress)+": "+bundle.getString("address"));
        province.setText(getString(R.string.spProvince)+": "+bundle.getString("province"));
        city.setText(getString(R.string.spCity)+": "+bundle.getString("city"));
        zipCode.setText(getString(R.string.etZipCode)+": "+bundle.getString("zipCode"));
        email.setText(getString(R.string.etEmail)+": "+bundle.getString("email"));
        phone.setText(getString(R.string.etPhone)+": "+bundle.getString("phone"));
    }
}