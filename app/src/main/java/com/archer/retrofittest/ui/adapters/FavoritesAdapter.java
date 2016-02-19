package com.archer.retrofittest.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.archer.retrofittest.R;
import com.archer.retrofittest.domain.Song;
import com.archer.retrofittest.ui.holders.FavoritesViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Archer on 15/2/16.
 */
public class FavoritesAdapter extends RecyclerView.Adapter<FavoritesViewHolder> {

    private Context context;
    private List<Song> mFavoritesList;

    public FavoritesAdapter(Context context) {
        this.context = context;
        mFavoritesList = new ArrayList<>();
    }

    public void setData(List<Song> data){
        mFavoritesList = data;
        notifyDataSetChanged();
    }

    @Override
    public FavoritesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_favorite_row, parent, false);
        return new FavoritesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FavoritesViewHolder holder, int position) {
        Song currentSong = mFavoritesList.get(position);
        holder.setmId(currentSong.getId());
        holder.setmTitle(currentSong.getName());
        holder.setmImage(context, currentSong.getUrlSmallImage());
    }

    @Override
    public int getItemCount() {
        return mFavoritesList.size();
    }
}
