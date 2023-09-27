package com.example.taskdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.taskdemo.adapter.Videoadapter;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private Videoadapter adapter;
    private Api apiService;
    ArrayList datalist = new ArrayList();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycle_video);
        adapter = new Videoadapter(datalist);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        fetchDataFromApi();
    }
    private void fetchDataFromApi() {
        Call<JsonObject> objectCall = RetrofitClient.getInstance().getMyApi().getvideolist();
        objectCall.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                Log.e("datavalue",response.body().toString());
                for (JsonElement object : response.body().getAsJsonArray("results")){
                    datalist.add(object.getAsJsonObject());
                }
                adapter.notifyDataSetChanged();
                Log.d("size", datalist.size() + "");
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Log.e("erro", t.toString());
            }
        });
    }
}
