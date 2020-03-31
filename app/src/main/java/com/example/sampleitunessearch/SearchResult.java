package com.example.sampleitunessearch;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.exoplayer2.SimpleExoPlayer;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchResult extends AppCompatActivity {
    ProgressBar progressBar;
    private RetrofitClient retrofit;
    SimpleExoPlayer player;
    SongsRecyclerAdapter songsRecyclerAdapter;
    RecyclerView recyclerView;
    private List<Songs> songsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
        progressBar = findViewById(R.id.rounded_bar);
        recyclerView = findViewById(R.id.recyclerView);
        progressBar.setVisibility(View.VISIBLE);
        searchingSong();
        songsList=new ArrayList<>();
        songsRecyclerAdapter = new SongsRecyclerAdapter(songsList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(songsRecyclerAdapter);


        player = new SimpleExoPlayer.Builder(getApplicationContext()).build();
    }

    private void searchingSong() {

        Service client = retrofit.createService(Service.class);

        Call<ResposnsePojo> call = client.getSongs("coldplay");

        call.enqueue(new Callback<ResposnsePojo>() {
            @Override
            public void onResponse(Call<ResposnsePojo> call, Response<ResposnsePojo> response) {

                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        progressBar.setVisibility(View.GONE);
                        songsList = response.body().getSongsList();
                        Toast.makeText(getApplicationContext(),"request successful", Toast.LENGTH_LONG).show();

                    } else {

                        Toast.makeText(getApplicationContext(), "No jobs to show..", Toast.LENGTH_LONG).show();
                    }
                }

            }

            @Override
            public void onFailure(Call<ResposnsePojo> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(getApplicationContext(), "request failed", Toast.LENGTH_LONG).show();
            }
        });

    }
}
