package com.archer.retrofittest.domain;

import android.os.Parcel;
import android.os.Parcelable;

public class Song implements Parcelable{

    private String name;
    private String artistName;
    private String urlSmallImage;
    private String urlMediumImage;
    private int isFavorite;

    public Song(Parcel in) {
        name = in.readString();
        artistName = in.readString();
        urlSmallImage = in.readString();
        urlMediumImage = in.readString();
        isFavorite = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(artistName);
        dest.writeString(urlSmallImage);
        dest.writeString(urlMediumImage);
        dest.writeInt(isFavorite);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Song> CREATOR = new Creator<Song>() {
        @Override
        public Song createFromParcel(Parcel in) {
            return new Song(in);
        }

        @Override
        public Song[] newArray(int size) {
            return new Song[size];
        }
    };

    // Getters y Setters

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

    public String getUrlMediumImage() {
        return urlMediumImage;
    }

    public void setUrlMediumImage(String urlMediumImage) {
        this.urlMediumImage = urlMediumImage;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public int getIsFavorite() {
        return isFavorite;
    }

    public void setIsFavorite(int isFavorite) {
        this.isFavorite = isFavorite;
    }
}
