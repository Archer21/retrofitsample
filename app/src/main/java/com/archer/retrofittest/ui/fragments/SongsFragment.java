package com.archer.retrofittest.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.archer.retrofittest.R;
import com.archer.retrofittest.domain.Song;
import com.archer.retrofittest.io.SongApiAdapter;
import com.archer.retrofittest.io.model.SongResponse;
import com.archer.retrofittest.ui.ItemOffsetDecoration;
import com.archer.retrofittest.ui.adapters.SongAdapter;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SongsFragment extends Fragment {

    public static final String LOG_TAG = SongsFragment.class.getSimpleName();
    public static final int NUM_COLUMS = 2;
    private RecyclerView mSongList;
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
        mSongList = (RecyclerView) root.findViewById(R.id.mRecyclerList);

        setupGridSongsListConfiguration();

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();

        Call<SongResponse> call = SongApiAdapter.getApiService().getSongs();
        call.enqueue(new Callback<SongResponse>() {
            @Override
            public void onResponse(Response<SongResponse> response) {
                adapter.addAll(response.body().getArtists());
            }

            @Override
            public void onFailure(Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private void setupGridSongsListConfiguration() {
        mSongList.setLayoutManager(new GridLayoutManager(getActivity(), NUM_COLUMS));
        mSongList.setAdapter(adapter);
        mSongList.addItemDecoration(new ItemOffsetDecoration(getActivity(), R.integer.offset));
        mSongList.setItemAnimator(new DefaultItemAnimator());
    }

}
