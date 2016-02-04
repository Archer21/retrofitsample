package com.archer.retrofittest.io.model;

import com.archer.retrofittest.domain.Artist;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ArtistResponse {

    @SerializedName(JsonKeys.ARTISTS_RESULTS)
    ArtistsResult results;

    public ArrayList<Artist> getArtists(){
        return results.artists;
    }

    public void setArtists(ArrayList<Artist> artists)
    {
        this.results.artists = artists;
    }

    public class ArtistsResult{
        ArrayList<Artist> artists;
    }
}
