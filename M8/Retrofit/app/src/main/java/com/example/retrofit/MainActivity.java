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

    private MyAdapter myAdapter;
    private RecyclerView myRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ApiService service = ApiAdapter.getApiServiceInstance().create(ApiService.class);

        Call<Municipi> call = service.getAllMunicipis();

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
        myRecyclerView = findViewById(R.id.myRecyclerView);
        myAdapter = new MyAdapter(municipi);



        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        myRecyclerView.setLayoutManager(layoutManager);

        myRecyclerView.setAdapter(myAdapter);
    }
}
