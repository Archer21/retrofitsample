package com.archer.retrofittest.database;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.provider.BaseColumns;
import android.support.annotation.Nullable;

public class AppProvider extends ContentProvider{

    protected AppDatabase mOpenHelper;
    private static final UriMatcher sUriMatcher = buildUriMatcher();

    private static final int FAVORITES    = 100;
    private static final int FAVORITES_ID = 101;




    private static UriMatcher buildUriMatcher(){
        final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
        String authority = FavoritesContracts.CONTENT_AUTHORITY;
        matcher.addURI(authority, "favorites", FAVORITES);
        matcher.addURI(authority, "favorites/#", FAVORITES_ID);
        return matcher;
    }

    private void deleteDatabase() {
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
        final SQLiteDatabase database = mOpenHelper.getReadableDatabase();
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        final int match = sUriMatcher.match(uri);

        switch (match){
            case FAVORITES:
                queryBuilder.setTables(AppDatabase.Tables.FAVORITES);
                break;
            case FAVORITES_ID:
                queryBuilder.setTables(AppDatabase.Tables.FAVORITES);
                String id = FavoritesContracts.Favorites.getFavoriteId(uri);
                queryBuilder.appendWhere(BaseColumns._ID + "=" + id);
                break;
        }
        return queryBuilder.query(database, projection, selection, selectionArgs, null, null, sortOrder);
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        final int match = sUriMatcher.match(uri);
        switch (match)
        {
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
        SQLiteDatabase database = mOpenHelper.getWritableDatabase();
        final int match = sUriMatcher.match(uri);

        switch (match) {
            case FAVORITES:
                long id = database.insertOrThrow(AppDatabase.Tables.FAVORITES, null, values);
                return FavoritesContracts.Favorites.buildFavoriteUri(String.valueOf(id));
            default:
                throw new IllegalArgumentException("Unknown Uri: " + uri);
        }
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
