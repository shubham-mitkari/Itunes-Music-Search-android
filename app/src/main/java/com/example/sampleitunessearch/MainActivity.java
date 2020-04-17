package com.example.sampleitunessearch;

import android.app.SearchManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.sampleitunessearch.Model.ResposnsePojo;
import com.example.sampleitunessearch.Model.Songs;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements SongsRecyclerAdapter.SongsAdapterListener {
    ProgressBar progressBar;
    private RetrofitClient retrofit;
    private SimpleExoPlayer player;
    private DefaultHttpDataSourceFactory dataSourceFactory;
    private long playbackPosition;
    private String currentMediaUrl;
    SongsRecyclerAdapter songsRecyclerAdapter;
    RecyclerView recyclerView;
    private List<Songs> songsList;
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = findViewById(R.id.main_progress_bar);
        initViews();
    }

    private void initViews() {

//        player = new SimpleExoPlayer.Builder(getApplicationContext()).build();

        List<Songs> songsList = new ArrayList<>();
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        songsRecyclerAdapter = new SongsRecyclerAdapter(this,songsList,this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);

        searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        SearchManager searchManager = (SearchManager) getSystemService(SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setIconifiedByDefault(true);
        searchView.setQueryHint("Search for Songs, Artists & More");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                progressBar.setVisibility(View.VISIBLE);
                searchingSong(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return true;
    }

    private void searchingSong(String query) {

        Service client = retrofit.createService(Service.class);

        Call<ResposnsePojo> call = client.getSongs(query);

        call.enqueue(new Callback<ResposnsePojo>() {
            @Override
            public void onResponse(Call<ResposnsePojo> call, Response<ResposnsePojo> response) {

                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        progressBar.setVisibility(View.GONE);
                        songsList = response.body().getSongsList();
                        recyclerView.setAdapter(songsRecyclerAdapter);
                        Toast.makeText(getApplicationContext(), "request successful", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "No Songs to show..", Toast.LENGTH_LONG).show();
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

    @Override
    public void onSongSelected(Songs songs) {
        Toast.makeText(getApplicationContext(), "Selected: " + songs.getTrackName(), Toast.LENGTH_LONG).show();

    }

//    @Override
//    public void onStart() {
//        super.onStart();
//        initExoPlayer();
//    }
//    @Override
//    protected void onStop() {
//        super.onStop();
//        releasePlayer();
//    }
//
//    private void releasePlayer() {
//        if (player != null) {
//            playbackPosition = player.getCurrentPosition(); // Storing the playback position for resume
//            player.release();
//            player = null;
//        }
//    }
//
//    private void initExoPlayer() {
//        dataSourceFactory = new DefaultHttpDataSourceFactory("exoplayer-" + BuildConfig.APPLICATION_ID);
//        player = new SimpleExoPlayer.Builder(this).build();
//        player.setPlayWhenReady(true);
//
//        if (currentMediaUrl != null) {
//            player.prepare(getMediaSource());
//            player.seekTo(playbackPosition);
//        }
//    }
//
//    private MediaSource getMediaSource() {
//        return new ExtractorMediaSource.Factory(dataSourceFactory)
//                .createMediaSource(Uri.parse(currentMediaUrl));
//    }
}
