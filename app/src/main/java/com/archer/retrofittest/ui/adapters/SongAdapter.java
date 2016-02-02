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
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Archer on 1/2/16.
 */
public class SongAdapter extends RecyclerView.Adapter<SongAdapter.SongViewHolder>{

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


    @Override
    public SongViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_song_row, parent, false);
        return new SongViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SongViewHolder holder, int position) {
        Song currentSong = mSongsList.get(position);
        holder.setmName(currentSong.getName());
        if (currentSong.getUrlSmallImage() != null)
        {
            holder.setmImage(currentSong.getUrlSmallImage());
        } else {
            holder.setDafaultImage();
        }

    }

    @Override
    public int getItemCount() {
        return mSongsList.size();
    }

    public class SongViewHolder extends RecyclerView.ViewHolder {

        ImageView mImage;
        TextView  mTitle;

        public SongViewHolder(View itemView) {
            super(itemView);

            mImage = (ImageView) itemView.findViewById(R.id.item_image);
            mTitle = (TextView)  itemView.findViewById(R.id.item_title);
        }

        public void setmName(String name)
        {
            mTitle.setText(name);
        }

        public void setmImage(String url)
        {
            Picasso.with(context)
                   .load(url)
                   .placeholder(R.drawable.artist_placeholder)
                   .into(mImage);
        }

        public void setDafaultImage(){
            Picasso.with(context)
                    .load(R.drawable.artist_placeholder)
                    .into(mImage);
        }
    }
}
