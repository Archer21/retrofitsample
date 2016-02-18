package com.archer.retrofittest.io.deserializer;

import android.os.Parcel;

import com.archer.retrofittest.domain.Song;
import com.archer.retrofittest.io.model.JsonKeys;
import com.archer.retrofittest.io.model.SongResponse;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Archer on 2/2/16.
 */
public class SongDeserializer implements JsonDeserializer<SongResponse> {
    @Override
    public SongResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Gson gson = new Gson();
        SongResponse response = gson.fromJson(json, SongResponse.class);
        JsonObject songsResponseData = json.getAsJsonObject().getAsJsonObject(JsonKeys.SONGS_RESULTS);
        JsonArray  songArray = songsResponseData.getAsJsonArray(JsonKeys.SONGS_ARRAY);
        response.setSongs(extractSongsFromJsonArray(songArray));
        return response;
    }
    private ArrayList<Song> extractSongsFromJsonArray(JsonArray array)
    {
        ArrayList<Song> songs = new ArrayList<>();
        int size = array.size();
        for (int i = 0; i < size; i++) {
            Song currentSong = new Song(Parcel.obtain());

            JsonObject songData = array.get(i).getAsJsonObject();

            int    id   = songData.get(JsonKeys.ID).getAsInt();
            String name = songData.get(JsonKeys.SONG_TITLE).getAsString();
            String artistName = songData.get(JsonKeys.SONGS_ARTIST_NAME).getAsString();

            JsonArray songImages = songData.getAsJsonArray(JsonKeys.SONG_IMAGES);
            HashMap<Integer, String>  images = extractSongsImageFromJsonArray(songImages);

            currentSong.setId(id);
            currentSong.setName(name);
            currentSong.setArtistName(artistName);
            currentSong.setUrlSmallImage(images.get(0));
            currentSong.setUrlMediumImage(images.get(1));

            songs.add(currentSong);
        }
        return songs;
    }

    private HashMap<Integer, String> extractSongsImageFromJsonArray(JsonArray imagesArray)
    {
        HashMap<Integer, String> images = new HashMap<>();

        int arraySize = imagesArray.size();
        for (int i = 0; i < arraySize; i++) {
            JsonObject imagesData = imagesArray.get(i).getAsJsonObject();

            String small  = imagesData.get(JsonKeys.SONG_IMAGE_SMALL).getAsString();
            String medium = imagesData.get(JsonKeys.SONG_IMAGE_MEDIUM).getAsString();

                images.put(0, small);
                images.put(1, medium);
        }
        return images;
    }

}
