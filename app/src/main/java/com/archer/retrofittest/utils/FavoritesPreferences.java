package com.archer.retrofittest.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Archer on 18/2/16.
 */
public class FavoritesPreferences {
    public static final String PREFS_NAME = "FAVORITES_PREFERENCES";

    // Set ResourceID for favorite
    public static void setFavoriteId(Context context, String id, boolean value) {
        SharedPreferences preferences = context.getSharedPreferences(
                PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(id, value);
        editor.apply();
    }

    // Return ResourceID of the favorite
    public static boolean getFavoriteId(Context context, String id) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(id, false);
    }

    public static void deleteFavoriteId(Context context, String id) {
        SharedPreferences preferences = context.getSharedPreferences(
                PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.remove(id);
        editor.apply();
    }
}
