package com.example.sampleitunessearch;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

public class SearchResult extends AppCompatActivity {
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
        progressBar=findViewById(R.id.rounded_bar);
        progressBar.setVisibility(View.VISIBLE);
        searchingSong();
    }

    private void searchingSong() {

        Service client = RetrofitClient.createService(Service.class);
//
//        Call<List<Songs>> call=client.getSongs(searchTerm);
//        call.enqueue(new Callback<List<Songs>>() {
//            @Override
//            public void onResponse(Call<List<Songs>> call, Response<List<Songs>> response) {
//                List<Songs> songs = response.body();
//                Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_LONG).show();
//
//            }
//
//            @Override
//            public void onFailure(Call<List<Songs>> call, Throwable t) {
//
//            }
//        });

    }
}
