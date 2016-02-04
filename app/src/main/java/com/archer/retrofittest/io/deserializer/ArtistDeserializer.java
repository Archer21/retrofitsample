package com.archer.retrofittest.io.deserializer;

import com.archer.retrofittest.domain.Artist;
import com.archer.retrofittest.io.model.ArtistResponse;
import com.archer.retrofittest.io.model.JsonKeys;
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

public class ArtistDeserializer implements JsonDeserializer<ArtistResponse>{
    @Override
    public ArtistResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Gson gson = new Gson();
        ArtistResponse response = gson.fromJson(json, ArtistResponse.class);
        JsonObject artistsResponseData = json.getAsJsonObject().getAsJsonObject(JsonKeys.ARTISTS_RESULTS);
        JsonArray  artistsArray = artistsResponseData.getAsJsonArray(JsonKeys.ARTISTS_ARRAY);
        response.setArtists(extractArtistsFromJsonArray(artistsArray));

        return response;
    }

    private ArrayList<Artist> extractArtistsFromJsonArray(JsonArray jsonArray){
        ArrayList<Artist> artists = new ArrayList<>();
        int artistArraySize = jsonArray.size();

        for (int i = 0; i < artistArraySize; i++) {
            Artist currentArtist = new Artist();

            JsonObject artistData = jsonArray.get(i).getAsJsonObject();
            String artistname     = artistData.get(JsonKeys.ARTIST_NAME).getAsString();
            JsonArray imagesArray = artistData.get(JsonKeys.ARTIST_IMAGES).getAsJsonArray();
            HashMap<Integer, String> artistImages = extractArtistImagesFromJsonArray(imagesArray);
            int votes = artistData.get(JsonKeys.ARTIST_VOTES).getAsInt();

            currentArtist.setName(artistname);
            currentArtist.setUrlImagePhoto(artistImages.get(0));
            currentArtist.setUrlImageCover(artistImages.get(1));
            currentArtist.setVotes(votes);

            artists.add(currentArtist);
        }
        return artists;
    }

    private HashMap<Integer, String> extractArtistImagesFromJsonArray(JsonArray imagesArray)
    {
        HashMap<Integer, String> images = new HashMap<>(2);
        int arraySize = imagesArray.size();
        for (int i = 0; i < arraySize; i++) {
            JsonObject imagesData = imagesArray.get(i).getAsJsonObject();
            String photoUrl = imagesData.get(JsonKeys.ARTIST_IMAGE_PHOTO).getAsString();
            String coverUrl = imagesData.get(JsonKeys.ARTIST_IMAGE_COVER).getAsString();

            images.put(0, photoUrl);
            images.put(1, coverUrl);
        }
        return images;
    }
}
