package com.archer.retrofittest.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

public class AppDatabase extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "retrofittest.db";
    private static final int DATABASE_VERSION = 1;


    public interface Tables{
        String FAVORITES = "favorites";
    }

    public AppDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
            "CREATE TABLE " + Tables.FAVORITES +
                "(" + BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    FavoritesContracts.FavoritesColumns.SONG_IMAGE_URL + " TEXT NOT NULL, " +
                    FavoritesContracts.FavoritesColumns.SONG_TITLE     + " TEXT NOT NULL" +
                ");"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        int version = oldVersion;
        if(version == 1) {
            version = 2;
        }

        if(version != DATABASE_VERSION) {
            db.execSQL("DROP TABLE IF EXISTS " + Tables.FAVORITES);
            onCreate(db);
        }
    }

    public static void deleteDatabase(Context context) {
        context.deleteDatabase(DATABASE_NAME);
    }
}
