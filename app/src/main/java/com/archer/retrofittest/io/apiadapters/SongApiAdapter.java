package com.archer.retrofittest.io.apiadapters;

import com.archer.retrofittest.io.ApiConstants;
import com.archer.retrofittest.io.apiservices.SongApiService;
import com.archer.retrofittest.io.deserializer.ArtistDeserializer;
import com.archer.retrofittest.io.deserializer.SongDeserializer;
import com.archer.retrofittest.io.model.ArtistResponse;
import com.archer.retrofittest.io.model.SongResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;

public class SongApiAdapter {

    private static SongApiService API_SERVICE;

    public static SongApiService getApiService()
    {
        if (API_SERVICE == null)
        {
            Retrofit adapter = new Retrofit.Builder()
                    .baseUrl(ApiConstants.URL_BASE)
                    .addConverterFactory(GsonConverterFactory.create(buildSongsApiGsonConverter()))
                    .build();

            API_SERVICE = adapter.create(SongApiService.class);
        }
        return API_SERVICE;
    }

    private static Gson buildSongsApiGsonConverter() {
        Gson gsonConfig = new GsonBuilder()
                .registerTypeAdapter(SongResponse.class, new SongDeserializer())
                .registerTypeAdapter(ArtistResponse.class, new ArtistDeserializer())
                .create();

        return gsonConfig;
    }

}
