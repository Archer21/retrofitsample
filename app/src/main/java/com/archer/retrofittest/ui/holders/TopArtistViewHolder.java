package com.archer.retrofittest.ui.holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.archer.retrofittest.R;

/**
 * Created by Archer on 3/2/16.
 */
public class TopArtistViewHolder extends RecyclerView.ViewHolder{

    ImageView artistCoverImage;
    TextView  artistName;
    TextView  artistVotes;

    public TopArtistViewHolder(View itemView) {
        super(itemView);
        artistCoverImage = (ImageView) itemView.findViewById(R.id.artist_cover_image);
        artistName       = (TextView)  itemView.findViewById(R.id.artist_name);
        artistVotes      = (TextView)  itemView.findViewById(R.id.artist_votes);
    }
}
