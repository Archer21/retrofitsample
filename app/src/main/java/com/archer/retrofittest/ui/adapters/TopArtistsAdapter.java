package com.archer.retrofittest.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.archer.retrofittest.R;
import com.archer.retrofittest.domain.Artist;
import com.archer.retrofittest.ui.holders.TopArtistViewHolder;

import java.util.ArrayList;


public class TopArtistsAdapter extends RecyclerView.Adapter<TopArtistViewHolder> {

    protected Context context;
    private ArrayList<Artist> mArtistsList;

    public TopArtistsAdapter(Context context) {
        this.context = context;
        this.mArtistsList = new ArrayList<>();
    }

    public void addAll(ArrayList<Artist> artists) {
        mArtistsList.addAll(artists);
    }

    @Override
    public TopArtistViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_top_artist_row, parent, false);
        return new TopArtistViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TopArtistViewHolder holder, int position) {
        Artist currentArtist = mArtistsList.get(position);
        holder.setArtistName(currentArtist.getName());
        holder.setArtistVotes(currentArtist.getVotes());
        if (currentArtist.getUrlImageCover() != null) {
            holder.setArtistCoverImage(context, currentArtist.getUrlImageCover());
        } else {
            holder.setDafaultImage(context);
        }
    }

    @Override
    public int getItemCount() {
        return mArtistsList.size();
    }
}
