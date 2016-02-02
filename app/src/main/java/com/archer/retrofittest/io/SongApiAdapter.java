package com.archer.retrofittest.io;

import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;

/**
 * Created by Archer on 2/2/16.
 */
public class SongApiAdapter {

    private static SongApiService API_SERVICE;

    public static SongApiService getApiService()
    {
        if (API_SERVICE == null)
        {
            Retrofit adapter = new Retrofit.Builder()
                    .baseUrl("https://api.myjson.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            API_SERVICE = adapter.create(SongApiService.class);
        }
        return API_SERVICE;
    }

}
