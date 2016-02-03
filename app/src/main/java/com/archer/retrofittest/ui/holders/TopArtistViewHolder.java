package com.archer.retrofittest.ui.holders;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.archer.retrofittest.R;
import com.squareup.picasso.Picasso;

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

    public void setArtistName(String name) {
        this.artistName.setText(name);
    }
    public void setArtistVotes(int votes){
        String totalVotes = votes + " votes";
        artistVotes.setText(totalVotes);
    }
    public void setArtistCoverImage(Context context, String url)
    {
        Picasso.with(context)
                .load(url)
                .placeholder(R.drawable.artist_placeholder)
                .into(artistCoverImage);
    }

    public void setDafaultImage(Context context){
        Picasso.with(context)
            .load(R.drawable.artist_placeholder)
            .into(artistCoverImage);
    }
}
