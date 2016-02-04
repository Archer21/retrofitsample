package com.archer.retrofittest.io.apiservices;

import com.archer.retrofittest.io.ApiConstants;
import com.archer.retrofittest.io.model.ArtistResponse;
import com.archer.retrofittest.io.model.SongResponse;

import retrofit2.Call;
import retrofit2.http.GET;


public interface SongApiService {
    @GET(ApiConstants.URL_SONGS)
    Call<SongResponse> getSongs();

    @GET(ApiConstants.URL_ARTISTS)
    Call<ArtistResponse> getArtistsResponse();
}
