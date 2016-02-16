package com.archer.retrofittest.ui.fragments;


import android.content.ContentResolver;
import android.os.Bundle;
import android.os.Parcel;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.archer.retrofittest.R;
import com.archer.retrofittest.database.FavoritesLoader;
import com.archer.retrofittest.domain.Song;
import com.archer.retrofittest.ui.adapters.SongAdapter;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class FavoritesFragment extends Fragment implements LoaderManager.LoaderCallbacks<ArrayList<Song>>{

    private static final int NUM_COLS = 4;
    private RecyclerView mRecyclerView;
    private SongAdapter  mSongAdapter;
    private ContentResolver mContentResolver;
    private static final int LOADER_ID = 1;
    private ArrayList<Song> mData;

    public FavoritesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSongAdapter = new SongAdapter(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_favorites, container, false);
        mRecyclerView = (RecyclerView) root.findViewById(R.id.fragment_favorites_main_container);
        setConfig();
        setDummieContent();
        return root;
    }

    private void setDummieContent(){
        ArrayList<Song> dummieSongs = new ArrayList<>();
        Song masayumeChasing = new Song(Parcel.obtain());
        masayumeChasing.setName("Masayume Chasing");
        masayumeChasing.setUrlSmallImage("");

        Song pray = new Song(Parcel.obtain());
        masayumeChasing.setName("Pray");
        masayumeChasing.setUrlSmallImage("");

        Song bloodPlus = new Song(Parcel.obtain());
        masayumeChasing.setName("Aosora no Namida");
        masayumeChasing.setUrlSmallImage("");
    }

    private void setConfig(){
        mContentResolver = getActivity().getContentResolver();
        setHasOptionsMenu(true);
        getLoaderManager().initLoader(LOADER_ID, null, this);

        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), NUM_COLS));
        mRecyclerView.setAdapter(mSongAdapter);
    }

    @Override
    public Loader<ArrayList<Song>> onCreateLoader(int id, Bundle args) {
        mContentResolver = getActivity().getContentResolver();
        return new FavoritesLoader(getActivity(), mContentResolver);
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<Song>> loader, final ArrayList<Song> data) {
//        Ease implementation first

//        this.mData = data;
//        Thread thread = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                //This is not helpfull now but when we do searchs operations or long tasks
//                //help us to dont block the ui thread
//
//                mSongAdapter.addAll(data);
//            }
//        });

        mSongAdapter.setData(data);
        mData = data;
    }

    @Override
    public void onLoaderReset(Loader<ArrayList<Song>> loader) {
        mSongAdapter.setData(null);
    }
}

