package com.archer.retrofittest.database;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
<<<<<<< HEAD
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
=======
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.provider.BaseColumns;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringDef;
import android.text.TextUtils;

/**
 * Created by Archer on 13/2/16.
 */
public class AppProvider extends ContentProvider {
    protected AppDatabase mOpenHelper;
    public static final UriMatcher sUriMatcher = buildUriMatcher();

    public static final int FAVORITES    = 100;
    public static final int FAVORITES_ID = 101;

    private static UriMatcher buildUriMatcher() {
        final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
        String authority = FavoritesContract.CONTENT_AUTHORITY;
>>>>>>> origin/addSqliteDatabase
        matcher.addURI(authority, "notes", FAVORITES);
        matcher.addURI(authority, "notes/*", FAVORITES_ID);
        return matcher;
    }

<<<<<<< HEAD

    public void deleteDatabase()
    {
=======
    private void deleteDatabase() {
>>>>>>> origin/addSqliteDatabase
        mOpenHelper.close();
        AppDatabase.deleteDatabase(getContext());
        mOpenHelper = new AppDatabase(getContext());
    }

<<<<<<< HEAD

=======
>>>>>>> origin/addSqliteDatabase
    @Override
    public boolean onCreate() {
        mOpenHelper = new AppDatabase(getContext());
        return true;
    }

    @Nullable
    @Override
<<<<<<< HEAD
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        final SQLiteDatabase db = mOpenHelper.getReadableDatabase();
        final int match = uriMatcher.match(uri);
=======
    public Cursor query(@NonNull Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        final SQLiteDatabase database = mOpenHelper.getReadableDatabase();
        final int match = sUriMatcher.match(uri);
>>>>>>> origin/addSqliteDatabase
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();

        switch (match){
            case FAVORITES:
                queryBuilder.setTables(AppDatabase.Tables.FAVORITES);
                break;
            case FAVORITES_ID:
                queryBuilder.setTables(AppDatabase.Tables.FAVORITES);
<<<<<<< HEAD
                String itemId = FavoritesContracts.Favorites.getFavoriteId(uri);
                queryBuilder.appendWhere(itemId);
=======
                String favoriteId = FavoritesContract.Favorites.getFavoriteId(uri);
                queryBuilder.appendWhere(BaseColumns._ID + "=" + favoriteId);
>>>>>>> origin/addSqliteDatabase
                break;
            default:
                throw new IllegalArgumentException("Unknown Uri: " + uri);
        }
<<<<<<< HEAD

        return queryBuilder.query(db, projection, selection, null, null, null, null, sortOrder);
=======
        return queryBuilder.query(database, projection, selection, selectionArgs, null, null, sortOrder);
>>>>>>> origin/addSqliteDatabase
    }

    @Nullable
    @Override
<<<<<<< HEAD
    public String getType(Uri uri) {
        final int match = uriMatcher.match(uri);
        switch(match) {
            case FAVORITES:
                return FavoritesContracts.Favorites.CONTENT_TYPE;
            case FAVORITES_ID:
                return FavoritesContracts.Favorites.CONTENT_ITEM_TYPE;
=======
    public String getType(@NonNull Uri uri) {
        final int match = sUriMatcher.match(uri);
        switch (match){
            case FAVORITES:
                return  FavoritesContract.Favorites.CONTENT_TYPE;
            case FAVORITES_ID:
                return FavoritesContract.Favorites.CONTENT_ITEM_TYPE;
>>>>>>> origin/addSqliteDatabase
            default:
                throw new IllegalArgumentException("Unknown Uri: " + uri);
        }
    }

    @Nullable
    @Override
<<<<<<< HEAD
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
=======
    public Uri insert(@NonNull Uri uri, ContentValues values) {
        final SQLiteDatabase database = mOpenHelper.getWritableDatabase();
        final int match = sUriMatcher.match(uri);

        switch (match){
            case FAVORITES:
                long favoriterecodId = database.insertOrThrow(AppDatabase.Tables.FAVORITES, null, values);
                return FavoritesContract.Favorites.buildFavoriteUri(String.valueOf(favoriterecodId));
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
>>>>>>> origin/addSqliteDatabase
    }
}
