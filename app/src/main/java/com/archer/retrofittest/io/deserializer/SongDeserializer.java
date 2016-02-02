package com.archer.retrofittest.io.deserializer;

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
            Song currentSong = new Song();

            JsonObject songData = array.get(i).getAsJsonObject();
            String name = songData.get(JsonKeys.SONG_NAME).getAsString();
            currentSong.setName(name);
            songs.add(currentSong);
        }
        return songs;
    }

}
