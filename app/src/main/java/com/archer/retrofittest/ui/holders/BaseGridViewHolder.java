package com.archer.retrofittest.ui.holders;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.archer.retrofittest.R;
import com.squareup.picasso.Picasso;

public class BaseGridViewHolder extends RecyclerView.ViewHolder {

    ImageView mImage;
    TextView  mTitle;

    public BaseGridViewHolder(View itemView) {
        super(itemView);

        mImage = (ImageView) itemView.findViewById(R.id.item_image);
        mTitle = (TextView)  itemView.findViewById(R.id.item_song_name);
    }

    public void setmName(String name)
    {
        mTitle.setText(name);
    }

    public void setmImage(Context context, String url)
    {
        Picasso.with(context)
                .load(url)
                .placeholder(R.drawable.artist_placeholder)
                .error(R.drawable.artist_placeholder)
                .into(mImage);
    }


}
