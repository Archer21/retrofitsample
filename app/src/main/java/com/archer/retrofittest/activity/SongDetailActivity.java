package com.archer.retrofittest.activity;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.archer.retrofittest.R;
import com.archer.retrofittest.database.FavoritesContract;
import com.archer.retrofittest.domain.Song;
import com.squareup.picasso.Picasso;

public class SongDetailActivity extends AppCompatActivity {

    private static final String CURRENT_SONG = "CURRENT_SONG";
    private static final String LOG_TAG = SongDetailActivity.class.getSimpleName();

    private TextView  songNameDetail;
    private ImageView photoArtist;
    private ImageView songCover;
    private Button    addFavorite;
    private Song myCurrentSong;
    private ContentResolver mContentResolver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        Song detailSong = getIntent().getParcelableExtra(CURRENT_SONG);
        songNameDetail  = (TextView)  findViewById(R.id.songNameDetail);
        photoArtist     = (ImageView) findViewById(R.id.photo);
        songCover       = (ImageView) findViewById(R.id.cover);
        addFavorite  = (Button)    findViewById(R.id.add_to_favorites);


        String artistName = detailSong.getArtistName();
        String photo  = detailSong.getUrlSmallImage();
        String cover = detailSong.getUrlMediumImage();

        setPhoto(photo);
        setCover(cover);

        songNameDetail.setText(artistName);

        myCurrentSong = detailSong;
    }

    public void addToFavorites(View view){
        ContentValues contentValues = new ContentValues();
        mContentResolver = this.getContentResolver();
        Uri uri = FavoritesContract.URI_TABLE;
        String title = myCurrentSong.getName();
        String image = myCurrentSong.getUrlSmallImage();
        contentValues.put(FavoritesContract.Favorites.FAVORITES_TITLE, title);
        contentValues.put(FavoritesContract.Favorites.FAVORITES_IMAGE, image);
        mContentResolver.insert(uri, contentValues);
        Log.e(LOG_TAG, title);
        Log.e(LOG_TAG, image);
        Log.e(LOG_TAG, mContentResolver.toString());
        Log.e(LOG_TAG, uri.toString());
        Toast.makeText(getApplicationContext(), "Add to favorites", Toast.LENGTH_SHORT).show();
    }

    public void setPhoto(String url) {
        Picasso.with(SongDetailActivity.this)
                .load(url)
                .placeholder(R.drawable.artist_placeholder)
                .error(android.R.drawable.ic_dialog_alert)
                .into(photoArtist);
    }

    public void setCover(String url) {
        Picasso.with(SongDetailActivity.this)
                .load(url)
                .placeholder(R.drawable.artist_placeholder)
                .error(android.R.drawable.ic_dialog_alert)
                .into(songCover);
    }

}
