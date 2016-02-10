package com.archer.retrofittest.database;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Parcel;
import android.provider.BaseColumns;
import android.support.v4.content.AsyncTaskLoader;

import com.archer.retrofittest.domain.Song;

import java.util.ArrayList;
import java.util.List;

public class FavoritesSongsLoader extends AsyncTaskLoader<List<Song>> {
    private List<Song> mSongs;
    private ContentResolver mContentResolver;
    private Cursor mCursor;

    public FavoritesSongsLoader(Context context, ContentResolver mContentResolver) {
        super(context);
        this.mContentResolver = mContentResolver;
    }


    @Override
    public List<Song> loadInBackground() {
        List<Song> entries = new ArrayList<>();
        String[] projection = {
                BaseColumns._ID,
                FavoritesContracts.FavoritesColumns.SONG_TITLE,
                FavoritesContracts.FavoritesColumns.SONG_IMAGE_URL
        };
        Uri uri = FavoritesContracts.URI_TABLE;
        String desc = BaseColumns._ID + "DESC";
        mCursor = mContentResolver.query(uri, projection, null, null, desc);
        if (mCursor != null) {
            if (mCursor.moveToFirst()) {
                do {
                    String title = mCursor.getString(mCursor.getColumnIndex(FavoritesContracts.FavoritesColumns.SONG_TITLE));
                    String image = mCursor.getString(mCursor.getColumnIndex(FavoritesContracts.FavoritesColumns.SONG_IMAGE_URL));
                    Song currentSong = new Song(Parcel.obtain());
                    currentSong.setName(title);
                    entries.add(currentSong);
                } while (mCursor.moveToNext());
            }
        }
        return entries;
    }

    @Override
    public void deliverResult(List<Song> data) {
        if (isReset()){
            if (data != null){
                releaseResources();
                return;
            }
        }

        List<Song> oldData = mSongs;
        mSongs = data;
        if (isStarted()){
            super.deliverResult(data);
        }
        if (oldData != null && oldData != data){
            releaseResources();
        }
    }

    @Override
    protected void onStartLoading() {
        if(mSongs != null){
            deliverResult(mSongs);
        }
        if (takeContentChanged()){
            forceLoad();
        } else if(mSongs == null) {
            forceLoad();
        }
    }

    @Override
    protected void onStopLoading() {
        cancelLoad();
    }

    @Override
    protected void onReset() {
        onStopLoading();
        if (mSongs != null){
            releaseResources();
            mSongs = null;
        }
    }

    @Override
    public void onCanceled(List<Song> data) {
        super.onCanceled(data);
        releaseResources();
    }

    @Override
    protected void onForceLoad() {
        super.onForceLoad();
    }

    protected void releaseResources(){
        mCursor.close();
    }
}
