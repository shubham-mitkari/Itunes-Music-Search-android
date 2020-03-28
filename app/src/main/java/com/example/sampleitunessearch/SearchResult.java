package com.example.sampleitunessearch;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchResult extends AppCompatActivity {
    ProgressBar progressBar;
    private RetrofitClient retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
        progressBar=findViewById(R.id.rounded_bar);
        progressBar.setVisibility(View.VISIBLE);
        searchingSong();
    }

    private void searchingSong() {

        Service client = retrofit.createService(Service.class);

        Call<ResposnsePojo> call=client.getSongs("coldplay");

        call.enqueue(new Callback<ResposnsePojo>() {
            @Override
            public void onResponse(Call<ResposnsePojo> call, Response<ResposnsePojo> response) {

                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        progressBar.setVisibility(View.GONE);
                        List<Songs>songsList=response.body().getSongsList();

                        Toast.makeText(getApplicationContext(),songsList.toString(),Toast.LENGTH_LONG).show();

                    } else {

                        Toast.makeText(getApplicationContext(), "No jobs to show..", Toast.LENGTH_LONG).show();
                    }
                }

            }

            @Override
            public void onFailure(Call<ResposnsePojo> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(getApplicationContext(),"request failed",Toast.LENGTH_LONG).show();
            }
        });

    }
}
