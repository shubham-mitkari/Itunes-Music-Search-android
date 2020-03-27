package com.example.sampleitunessearch;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

class RetrofitClient {
    static String BASE_URL = "https://itune.apple.com/";

    static Retrofit.Builder builder = new retrofit2.Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create());
    static Retrofit retrofit = builder.build();

    static <S> S createService(Class<S> serviceClass) {
        return retrofit.create(serviceClass);
    }
}
