package com.archer.retrofittest.database;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.provider.BaseColumns;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;

public class AppProvider extends ContentProvider {
    protected AppDatabase mOpenHelper;
    public static final UriMatcher sUriMatcher = buildUriMatcher();

    public static final int FAVORITES    = 100;
    public static final int FAVORITES_ID = 101;

    private static UriMatcher buildUriMatcher() {
        final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
        String authority = FavoritesContract.CONTENT_AUTHORITY;
        matcher.addURI(authority, "favorites", FAVORITES);
        matcher.addURI(authority, "favorites/*", FAVORITES_ID);
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
    public Cursor query(@NonNull Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        final SQLiteDatabase database = mOpenHelper.getReadableDatabase();
        final int match = sUriMatcher.match(uri);
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();

        switch (match){
            case FAVORITES:
                queryBuilder.setTables(AppDatabase.Tables.FAVORITES);
                break;
            case FAVORITES_ID:
                queryBuilder.setTables(AppDatabase.Tables.FAVORITES);
                String favoriteId = FavoritesContract.Favorites.getFavoriteId(uri);
                queryBuilder.appendWhere(BaseColumns._ID + "=" + favoriteId);
                break;
            default:
                throw new IllegalArgumentException("Unknown Uri: " + uri);
        }
        return queryBuilder.query(database, projection, selection, selectionArgs, null, null, sortOrder);
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        final int match = sUriMatcher.match(uri);
        switch (match){
            case FAVORITES:
                return  FavoritesContract.Favorites.CONTENT_TYPE;
            case FAVORITES_ID:
                return FavoritesContract.Favorites.CONTENT_ITEM_TYPE;
            default:
                throw new IllegalArgumentException("Unknown Uri: " + uri);
        }
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, ContentValues values) {
        final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        final int match = sUriMatcher.match(uri);

        Log.v("AppProvider", "insert(uri=" + uri + ", values=" + values.toString());
        switch (match){
            case FAVORITES:
                long recordId = db.insertOrThrow(AppDatabase.Tables.FAVORITES, null, values);
                return FavoritesContract.Favorites.buildFavoriteUri(String.valueOf(recordId));
            default:
                throw new IllegalArgumentException("Unknown Uri: " + uri);
        }
    }


    @Override
    public int delete(@NonNull Uri uri, String selection, String[] selectionArgs) {
        if(uri.equals(FavoritesContract.BASE_CONTENT_URI)) {
            deleteDatabase();
            return 0;
        }
        final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        final int match = sUriMatcher.match(uri);

        switch (match) {

            case FAVORITES_ID:
                String favoritesId = FavoritesContract.Favorites.getFavoriteId(uri);
                String favoritesSelectionCriteria = BaseColumns._ID + "=" + favoritesId
                        + (!TextUtils.isEmpty(selection) ? " AND ( " + selection + ")" : "");
                return db.delete(AppDatabase.Tables.FAVORITES, favoritesSelectionCriteria, selectionArgs);
            default:
                throw new IllegalArgumentException("Unknown Uri: " + uri);
        }
    }


    @Override
    public int update(@NonNull Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        final SQLiteDatabase database = mOpenHelper.getWritableDatabase();
        final int match = sUriMatcher.match(uri);
        switch (match){
            case FAVORITES:
                return database.update(AppDatabase.Tables.FAVORITES, values, selection, selectionArgs);
            case FAVORITES_ID:
                String favoriteId = FavoritesContract.Favorites.getFavoriteId(uri);
                String selectionCriteria = BaseColumns._ID + "=" + favoriteId;
                if (!TextUtils.isEmpty(selection)){
                    selectionCriteria += " AND (" + selection + ")";
                }
                return database.update(AppDatabase.Tables.FAVORITES, values, selectionCriteria, selectionArgs);
            default:
                throw new IllegalArgumentException("Unknown Uri: " + uri);
        }
    }
}
