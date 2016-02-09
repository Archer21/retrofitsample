package com.archer.retrofittest.database;

import android.net.Uri;
import android.provider.BaseColumns;

public class FavoritesContracts {

    interface FavoritesColumns {
        String SONG_ID    = "_id";
        String SONG_TITLE = "title";
        String SONG_IMAGE_URL   = "url";
    }

    public  static final String CONTENT_AUTHORITY = "com.archer.retrofittest.database.contracts";
    public  static final Uri    BASE_URI_CONTENT  = Uri.parse("content://" + CONTENT_AUTHORITY);
    private static final String PATH_FAVORITES    = "favorites";
    public  static final Uri    URI_TABLE         = BASE_URI_CONTENT.buildUpon().appendEncodedPath(PATH_FAVORITES).build();

    public static class Favorites implements BaseColumns, FavoritesColumns {
        public static final String CONTENT_TYPE      = "vnd.android.cursor.dir/vnd." + CONTENT_AUTHORITY + ".favorites";
        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd." + CONTENT_AUTHORITY + ".favorites";

        public static Uri buildFavoriteUri(String id)
        {
            return URI_TABLE.buildUpon().appendEncodedPath(id).build();
        }
        public static final String getFavoriteId(Uri uri)
        {
            return uri.getPathSegments().get(1);
        }
    }

}
