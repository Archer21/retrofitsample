package com.archer.retrofittest.ui.fragments;


import android.content.ContentResolver;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v4.media.MediaBrowserCompat;
import android.support.v4.media.MediaBrowserCompat.ConnectionCallback;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.archer.retrofittest.R;
import com.archer.retrofittest.activity.MainActivity;
import com.archer.retrofittest.database.FavoritesSongsLoader;
import com.archer.retrofittest.domain.Song;
import com.archer.retrofittest.ui.adapters.SongAdapter;

import java.util.ArrayList;
import java.util.List;

public class FavoritesFragment extends Fragment implements LoaderManager.LoaderCallbacks<List<Song>>{

    private static final int NUM_COLS = 4;
    private List<Song> mSongList;
    private RecyclerView mRecyclerView;
    private SongAdapter  mSongAdapter;
    private ContentResolver mContentResolver;

    public FavoritesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSongList    = new ArrayList<>();
        mSongAdapter = new SongAdapter(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_favorites, container, false);
        mRecyclerView = (RecyclerView) root.findViewById(R.id.fragment_favorites_main_container);

        setConfig();

        return root;
    }

    private void setConfig(){
        int LOADER_ID = 1;
        mContentResolver = getActivity().getContentResolver();
        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), NUM_COLS));
        mRecyclerView.setAdapter(mSongAdapter);
        getActivity().getSupportLoaderManager().initLoader(LOADER_ID, null, this);
    }

    @Override
    public Loader<List<Song>> onCreateLoader(int id, Bundle args) {
        mContentResolver = getActivity().getContentResolver();
        return new FavoritesSongsLoader(getActivity(), mContentResolver);
    }

    @Override
    public void onLoadFinished(Loader<List<Song>> loader, List<Song> data) {
        mSongList = data;
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                mSongAdapter.addAll(mSongList);
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mSongAdapter.notifyDataSetChanged();
                    }
                });
            }
        });
        thread.start();
    }

    @Override
    public void onLoaderReset(Loader<List<Song>> loader) {
        mSongAdapter.addAll(null);
    }
}

