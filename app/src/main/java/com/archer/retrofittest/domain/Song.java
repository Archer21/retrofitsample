package com.archer.retrofittest.domain;

import com.archer.retrofittest.io.model.JsonKeys;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Archer on 1/2/16.
 */
public class Song {

    @SerializedName(JsonKeys.SONG_NAME)
    private String name;
    private String urlSmallImage;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrlSmallImage() {
        return urlSmallImage;
    }

    public void setUrlSmallImage(String urlSmallImage) {
        this.urlSmallImage = urlSmallImage;
    }

    @Override
    public String toString() {
        return "Song{" +
                "name='" + name + '\'' +
                ", urlSmallImage='" + urlSmallImage + '\'' +
                '}';
    }
}
