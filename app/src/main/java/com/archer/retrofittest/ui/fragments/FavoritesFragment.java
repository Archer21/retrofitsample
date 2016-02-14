package com.archer.retrofittest.ui.fragments;


import android.content.ContentResolver;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.archer.retrofittest.R;
import com.archer.retrofittest.ui.adapters.SongAdapter;

public class FavoritesFragment extends Fragment{

    private static final int NUM_COLS = 4;
    private RecyclerView mRecyclerView;
    private SongAdapter  mSongAdapter;

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

        return root;
    }

    private void setConfig(){
        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), NUM_COLS));
        mRecyclerView.setAdapter(mSongAdapter);
    }
}

