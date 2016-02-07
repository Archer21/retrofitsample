package com.archer.retrofittest.ui.fragments;


import android.os.Bundle;
import android.os.Parcel;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.archer.retrofittest.R;
import com.archer.retrofittest.domain.Song;
import com.archer.retrofittest.ui.adapters.SongAdapter;

import java.util.ArrayList;


public class FavoritesFragment extends Fragment {

    private static final int NUM_COLS = 4;
    private RecyclerView mRecyclerList;
    private SongAdapter adapter;

    public FavoritesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new SongAdapter(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_favorites, container, false);
        mRecyclerList = (RecyclerView) root.findViewById(R.id.favorites_main_container);

        setRecyclerListConfiguration();
        setDummieSongs();

        return root;
    }
    
    private void setRecyclerListConfiguration() {
        mRecyclerList.setLayoutManager(new GridLayoutManager(getActivity(), NUM_COLS));
        mRecyclerList.setAdapter(adapter);
    }

    public void setDummieSongs()
    {
        ArrayList<Song> dummieList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            Song song = new Song(Parcel.obtain());
            song.setName("Artist " + i);
            dummieList.add(song);
        }
        adapter.addAll(dummieList);
    }
    

}
