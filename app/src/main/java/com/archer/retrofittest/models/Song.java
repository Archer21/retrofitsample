package com.archer.retrofittest.models;

/**
 * Created by Archer on 1/2/16.
 */
public class Song {

    private String title;
    private String urlSmallImage;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
                "title='" + title + '\'' +
                ", urlSmallImage='" + urlSmallImage + '\'' +
                '}';
    }
}
