package com.example.sampleitunessearch;

import com.example.sampleitunessearch.Model.ResposnsePojo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Service {

    @GET("search")
    Call<ResposnsePojo> getSongs(
            @Query("term") CharSequence searchTerm);
}
