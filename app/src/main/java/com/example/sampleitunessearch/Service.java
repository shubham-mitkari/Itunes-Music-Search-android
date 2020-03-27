package com.example.sampleitunessearch;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Service {

    @GET("search")
    Call<List<Songs>> getSongs(
            @Query("term") CharSequence searchTerm);
}
