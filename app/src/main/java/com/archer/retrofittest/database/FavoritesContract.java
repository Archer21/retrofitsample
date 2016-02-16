package com.archer.retrofittest.database;

import android.net.Uri;
import android.provider.BaseColumns;

public class FavoritesContract {
    interface FavoritesColumns {
//        String FAVORITES_ID = "_ID";
        String FAVORITES_TITLE = "favorites_title";
//        String FAVORITES_DESCRIPTION = "favorites_description";
//        String FAVORITES_DATE = "favorites_date";
//        String FAVORITES_TIME = "favorites_time";
        String FAVORITES_IMAGE = "favorites_image";
    }

    public static  final String CONTENT_AUTHORITY = "com.archer.retrofittest.database.provider";
    public static  final Uri    BASE_CONTENT_URI  = Uri.parse("content://" + CONTENT_AUTHORITY);
    private static final String PATH_FAVORITES    = "favorites";
    public static  final Uri    URI_TABLE         = BASE_CONTENT_URI.buildUpon().appendEncodedPath(PATH_FAVORITES).build();

    public static class Favorites implements FavoritesColumns, BaseColumns {
        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd." + CONTENT_AUTHORITY + ".favorites";
        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd." + CONTENT_AUTHORITY + ".favorites";

        public static Uri buildFavoriteUri(String favoriteId) {
            return URI_TABLE.buildUpon().appendEncodedPath(favoriteId).build();
        }

        public static String getFavoriteId(Uri uri) {
            return uri.getLastPathSegment();
        }


    }
}
