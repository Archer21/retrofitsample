package com.archer.retrofittest.io.model;

import com.archer.retrofittest.domain.Song;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Archer on 2/2/16.
 */
public class SongResponse {
    @SerializedName(JsonKeys.SONGS_RESULTS)
    SongsResult result;

    public void setSongs(ArrayList<Song> songs){
        this.result.songs = songs;
    }


    public ArrayList<Song> getArtists(){
        return result.songs;
    }

    private class SongsResult{

        ArrayList<Song> songs;
    }
}
