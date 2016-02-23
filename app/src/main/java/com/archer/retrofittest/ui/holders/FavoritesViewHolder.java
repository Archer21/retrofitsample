package com.archer.retrofittest.ui.holders;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.archer.retrofittest.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

public class FavoritesViewHolder extends RecyclerView.ViewHolder {

    private TextView  mId;
    private TextView  mTitle;
    private ImageView mImage;

    public FavoritesViewHolder(View itemView) {
        super(itemView);
        mId    = (TextView)  itemView.findViewById(R.id.item_id);
        mTitle = (TextView)  itemView.findViewById(R.id.favorite_title);
        mImage = (ImageView) itemView.findViewById(R.id.favorite_image);
    }

    public void setmId(int id){
        mId.setText(String.valueOf(id));
    }
    public void setmTitle(String title){
        mTitle.setText(title);
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
                                .placeholder(R.drawable.artist_placeholder)
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
