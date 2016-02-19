package com.archer.retrofittest.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.archer.retrofittest.R;
import com.archer.retrofittest.domain.Song;
import com.archer.retrofittest.ui.holders.BaseGridViewHolder;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Archer on 1/2/16.
 */
public class SongAdapter extends RecyclerView.Adapter<BaseGridViewHolder>{

    Context context;
    ArrayList<Song> mSongsList;

    public SongAdapter(Context context) {
        this.context = context;
        mSongsList = new ArrayList<>();
    }


    public void addAll(ArrayList<Song> songs){
        if (songs==null)
            throw new NullPointerException("The items cannot by null");

        this.mSongsList.addAll(songs);
        notifyDataSetChanged();
    }

    public Song getItemPosition(int position)
    {
        return mSongsList.get(position);
    }

    @Override
    public BaseGridViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_song_row, parent, false);
        return new BaseGridViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BaseGridViewHolder holder, int position) {
//        Me parece que aca podemos modificar cada item
//        if (position == 0){
//            int layoutWidth = RecyclerView.LayoutParams.MATCH_PARENT;
//            int layoutHeight = 200;
//        }
        Song currentSong = mSongsList.get(position);
//        holder.setSongId(currentSong.getId());
        holder.setmName(currentSong.getName());
        holder.setmImage(context, currentSong.getUrlSmallImage());
    }

    @Override
    public int getItemCount() {
        return mSongsList.size();
    }

}
