package com.archer.retrofittest.database;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.annotation.Nullable;

public class AppProvider extends ContentProvider {

    protected AppDatabase mOpenHelper;
    private UriMatcher uriMatcher = buildUriMatcher();

    private static final int FAVORITES = 100;
    private static final int FAVORITES_ID = 101;

    public static UriMatcher buildUriMatcher() {
        final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
        String authority = FavoritesContracts.CONTENT_AUTHORITY;
        matcher.addURI(authority, "notes", FAVORITES);
        matcher.addURI(authority, "notes/*", FAVORITES_ID);
        return matcher;
    }


    public void deleteDatabase()
    {
        mOpenHelper.close();
        AppDatabase.deleteDatabase(getContext());
        mOpenHelper = new AppDatabase(getContext());
    }


    @Override
    public boolean onCreate() {
        mOpenHelper = new AppDatabase(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        final SQLiteDatabase db = mOpenHelper.getReadableDatabase();
        final int match = uriMatcher.match(uri);
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();

        switch (match){
            case FAVORITES:
                queryBuilder.setTables(AppDatabase.Tables.FAVORITES);
                break;
            case FAVORITES_ID:
                queryBuilder.setTables(AppDatabase.Tables.FAVORITES);
                String itemId = FavoritesContracts.Favorites.getFavoriteId(uri);
                queryBuilder.appendWhere(itemId);
                break;
            default:
                throw new IllegalArgumentException("Unknown Uri: " + uri);
        }

        return queryBuilder.query(db, projection, selection, null, null, null, null, sortOrder);
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        final int match = uriMatcher.match(uri);
        switch(match) {
            case FAVORITES:
                return FavoritesContracts.Favorites.CONTENT_TYPE;
            case FAVORITES_ID:
                return FavoritesContracts.Favorites.CONTENT_ITEM_TYPE;
            default:
                throw new IllegalArgumentException("Unknown Uri: " + uri);
        }
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}
