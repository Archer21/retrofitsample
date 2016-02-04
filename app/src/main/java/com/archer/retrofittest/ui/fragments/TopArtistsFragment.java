package com.archer.retrofittest.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.archer.retrofittest.R;
import com.archer.retrofittest.io.apiadapters.SongApiAdapter;
import com.archer.retrofittest.io.model.ArtistResponse;
import com.archer.retrofittest.ui.adapters.TopArtistsAdapter;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TopArtistsFragment extends Fragment {

    RecyclerView mRecycleArtistList;
    TopArtistsAdapter adapter;

    public TopArtistsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onResume() {
        super.onResume();
        Call<ArtistResponse> call = SongApiAdapter.getApiService().getArtistsResponse();
        call.enqueue(new Callback<ArtistResponse>() {
            @Override
            public void onResponse(Response<ArtistResponse> response) {
                adapter.addAll(response.body().getArtists());
            }

            @Override
            public void onFailure(Throwable t) {
                t.printStackTrace();
            }
        });
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new TopArtistsAdapter(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_top_artist_fragments, container, false);
        mRecycleArtistList = (RecyclerView) root.findViewById(R.id.top_artist_recycler_list);

        setListConfiguration();
        return root;
    }

    private void setListConfiguration() {
        mRecycleArtistList.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecycleArtistList.setAdapter(adapter);
    }
}
