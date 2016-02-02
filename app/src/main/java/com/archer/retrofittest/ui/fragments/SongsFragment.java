package com.archer.retrofittest.ui.fragments;

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

import java.util.ArrayList;

public class SongsFragment extends Fragment {

    public static final String LOG_TAG = SongsFragment.class.getSimpleName();
    public static final int NUM_COLUMS = 2;
    private RecyclerView mHypedArtistList;
    private SongAdapter adapter;


    public SongsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new SongAdapter(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_songs, container, false);
        return root;
    }

    private void setupSongsList() {
        mHypedArtistList.setLayoutManager(new GridLayoutManager(getActivity(), NUM_COLUMS));
        mHypedArtistList.setAdapter(adapter);
        mHypedArtistList.addItemDecoration(new ItemOffsetDecoration(getActivity(), R.integer.offset));
    }

}
