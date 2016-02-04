package com.archer.retrofittest.domain;

import com.archer.retrofittest.io.model.JsonKeys;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Archer on 3/2/16.
 */
public class Artist {

    @SerializedName(JsonKeys.ARTIST_NAME)
    private String name;
    private String urlImageCover;
    private String urlImagePhoto;

    private int artistId;
    private int votes;

    public int getArtistId() {
        return artistId;
    }

    public void setArtistId(int artistId) {
        this.artistId = artistId;
    }

    public int getVotes() {
        return votes;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrlImageCover() {
        return urlImageCover;
    }

    public void setUrlImageCover(String urlImageCover) {
        this.urlImageCover = urlImageCover;
    }

    public String getUrlImagePhoto() {
        return urlImagePhoto;
    }

    public void setUrlImagePhoto(String urlImagePhoto) {
        this.urlImagePhoto = urlImagePhoto;
    }
}
