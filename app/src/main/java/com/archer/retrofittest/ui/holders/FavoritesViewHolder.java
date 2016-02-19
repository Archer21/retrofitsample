package com.archer.retrofittest.ui.holders;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.archer.retrofittest.R;
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
    public void setmImage(Context context, String image){
        Picasso.with(context)
                .load(image)
                .placeholder(R.drawable.artist_placeholder)
                .error(android.R.drawable.ic_dialog_alert);
    }
}
