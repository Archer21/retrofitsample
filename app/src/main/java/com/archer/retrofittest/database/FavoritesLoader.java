package com.archer.retrofittest.database;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Parcel;
import android.provider.BaseColumns;
import android.provider.SyncStateContract;
import android.support.v4.content.AsyncTaskLoader;

import com.archer.retrofittest.database.FavoritesContract;
import com.archer.retrofittest.domain.Song;

import java.util.ArrayList;

public class FavoritesLoader extends AsyncTaskLoader<ArrayList<Song>> {
    private ArrayList<Song> mSongs;
    private ContentResolver mContentresolver;
    private Cursor mCursor;

    public FavoritesLoader(Context context, ContentResolver contentResolver){
        super(context);
        mContentresolver = contentResolver;
    }

    @Override
    public ArrayList<Song> loadInBackground() {
        ArrayList<Song> entries = new ArrayList<>();
        String[] projection = {
                BaseColumns._ID,
                FavoritesContract.FavoritesColumns.FAVORITES_IMAGE,
                FavoritesContract.FavoritesColumns.FAVORITES_TITLE
        };
        Uri uri = FavoritesContract.URI_TABLE;
        String sortById = BaseColumns._ID + "DESC";
        mCursor = mContentresolver.query(uri, projection, null, null, sortById);
        if (mCursor != null){
            if (mCursor.moveToFirst()){
                do{
                    Song currentSong = new Song(Parcel.obtain());
                    int      _id = mCursor.getInt(mCursor.getColumnIndex(BaseColumns._ID));
                    String title = mCursor.getString(mCursor.getColumnIndex(FavoritesContract.FavoritesColumns.FAVORITES_TITLE));
                    String image = mCursor.getString(mCursor.getColumnIndex(FavoritesContract.FavoritesColumns.FAVORITES_IMAGE));
                    currentSong.setName(title);
                    currentSong.setUrlSmallImage(image);
                    entries.add(currentSong);
                } while (mCursor.moveToNext());
            }
        }
        return entries;
    }
}
