package com.archer.retrofittest.ui.holders;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.archer.retrofittest.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

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

    public void setmImage(final Context context, final String url)
    {
        Picasso.with(context)
                .load(url)
                .placeholder(R.drawable.artist_placeholder)
                .networkPolicy(NetworkPolicy.OFFLINE)
                .into(mImage, new Callback() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError() {
                        //Try again online if cache failed
                        Picasso.with(context)
                                .load(url)
                                .error(R.drawable.artist_placeholder)
                                .into(mImage, new Callback() {
                                    @Override
                                    public void onSuccess() {

                                    }

                                    @Override
                                    public void onError() {
                                        Log.v("Picasso","Could not fetch image");
                                    }
                                });
                    }
                });
    }


}
