package com.archer.retrofittest.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

public class AppDatabase extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "database.db";
    private static final int DATABASE_VERSION = 1;
    public Context mContext;

    interface Tables {
        String FAVORITES = "favorites";
    }

    public AppDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + Tables.FAVORITES + "("
//                + BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + BaseColumns._ID + " INTEGER PRIMARY KEY UNIQUE,"
                + FavoritesContract.FavoritesColumns.FAVORITES_TITLE + " TEXT NOT NULL,"
                + FavoritesContract.FavoritesColumns.FAVORITES_IMAGE + " TEXT NOT NULL)");
    }
//                + FavoritesContract.FavoritesColumns.FAVORITES_DESCRIPTION + " TEXT NOT NULL,"
//                + FavoritesContract.FavoritesColumns.FAVORITES_TIME + " TEXT NOT NULL,"
//                + FavoritesContract.FavoritesColumns.FAVORITES_DATE + " TEXT NOT NULL)");}

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
