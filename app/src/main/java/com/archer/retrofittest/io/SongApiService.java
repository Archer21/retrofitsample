package com.archer.retrofittest.io;

import com.archer.retrofittest.io.model.SongResponse;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Archer on 1/2/16.
 */
public interface SongApiService {
    @GET("bins/4dsrd")
    Call<SongResponse> getSongs();
}
