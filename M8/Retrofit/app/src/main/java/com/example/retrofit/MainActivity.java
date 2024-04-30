package com.example.retrofit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private MunicipiAdapter municipiAdapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ApiService service = ApiAdapter.getApiServiceInstance();
        Call<Municipi> call = service.getMunicipi();

        call.enqueue(new Callback<Municipi>() {
            @Override
            public void onResponse(Call<Municipi> call, Response<Municipi> response) {
                loadDataList(response.body());
            }

            @Override
            public void onFailure(Call<Municipi> call, Throwable throwable) {
                Toast.makeText(MainActivity.this, "Unable to load elements", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void loadDataList(Municipi municipi) {
        recyclerView = findViewById(R.id.myRecyclerView);
        municipiAdapter = new MunicipiAdapter(municipi);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(municipiAdapter);
    }
}
