package com.archer.retrofittest.activity;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.archer.retrofittest.R;
import com.archer.retrofittest.database.FavoritesContract;
import com.archer.retrofittest.domain.Song;
import com.archer.retrofittest.utils.FavoritesPreferences;
import com.squareup.picasso.Picasso;

public class SongDetailActivity extends AppCompatActivity {

    private static final String CURRENT_SONG = "CURRENT_SONG";
    private static final String LOG_TAG = SongDetailActivity.class.getSimpleName();
    private int songId;
    private String favoriteSongId;

    private TextView    songNameDetail;
    private ImageView   photoArtist;
    private ImageView   songCover;
    private ImageButton addFavorite;

    private ContentResolver mContentResolver;
    private boolean isFavorite;
    private Song myCurrentSong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        songNameDetail  = (TextView)  findViewById(R.id.song_name);
        photoArtist     = (ImageView) findViewById(R.id.photo);
        songCover       = (ImageView) findViewById(R.id.cover);
        addFavorite     = (ImageButton)    findViewById(R.id.add_to_favorites);

        // Recibimos el objeto por medio de getParcelable
        Song detailSong   = getIntent().getParcelableExtra(CURRENT_SONG);
        myCurrentSong     = detailSong;
        songId            = detailSong.getId();
        favoriteSongId    = String.valueOf(songId);
        String artistName = detailSong.getArtistName();
        String photo      = detailSong.getUrlSmallImage();
        String cover      = detailSong.getUrlMediumImage();
        // posteriormente llenamos los datos
        // de la cancion
        songNameDetail.setText(artistName);
        setPhoto(photo);
        setCover(cover);

        // Favorites
        isFavorite = FavoritesPreferences.getFavoriteId(SongDetailActivity.this, favoriteSongId);
    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//        if (isFavorite){
//            addFavorite.setText("Delete from Favorites");
//        } else {
//            addFavorite.setText("Save on Favorites");
//        }
//    }

    // Shared Preferences

    public void addToFavorites(View view){
        if (!isFavorite){
//            FavoritesPreferences.setFavoriteId(getApplicationContext(), favoriteSongId, true);
//            isFavorite = FavoritesPreferences.getFavoriteId(getApplicationContext(), favoriteSongId);
            insertFavorite();
//            addFavorite.setText("Delete from Favorites");
            Log.e(LOG_TAG, "The id " + favoriteSongId + " is " + String.valueOf(isFavorite));

        } else {
//            FavoritesPreferences.setFavoriteId(getApplicationContext(), favoriteSongId, false);
//            isFavorite = FavoritesPreferences.getFavoriteId(getApplicationContext(), favoriteSongId);
            deleteFavorite();
//            addFavorite.setText("Save on Favorites");
            Log.e(LOG_TAG, "The id is " + favoriteSongId + " " + String.valueOf(isFavorite));
        }
    }
    private void insertFavorite(){
        if (!FavoritesPreferences.PREFS_NAME.contains(favoriteSongId)){
            ContentValues contentValues = new ContentValues();
            mContentResolver = this.getContentResolver();
            Uri uri = FavoritesContract.URI_TABLE;
            String id    = favoriteSongId;
            String title = songNameDetail.getText().toString();
            String image = myCurrentSong.getUrlSmallImage();

            contentValues.put(FavoritesContract.Favorites.FAVORITES_ID, id);
            contentValues.put(FavoritesContract.Favorites.FAVORITES_TITLE, title);
            contentValues.put(FavoritesContract.Favorites.FAVORITES_IMAGE, image);
            mContentResolver.insert(uri, contentValues);
            FavoritesPreferences.setFavoriteId(getApplicationContext(), favoriteSongId, true);
            isFavorite = FavoritesPreferences.getFavoriteId(getApplicationContext(), favoriteSongId);
            Toast.makeText(getApplicationContext(), "Added to favorites", Toast.LENGTH_SHORT).show();
        }

    }

    private void deleteFavorite(){
//        if (FavoritesPreferences.PREFS_NAME.contains(favoriteSongId)){
            ContentResolver cr = this.getContentResolver();
            String _ID = favoriteSongId;
            Uri uri = FavoritesContract.Favorites.buildFavoriteUri(_ID);
            cr.delete(uri, null, null);
            FavoritesPreferences.setFavoriteId(getApplicationContext(), favoriteSongId, false);
            isFavorite = FavoritesPreferences.getFavoriteId(getApplicationContext(), favoriteSongId);
            Toast.makeText(getApplicationContext(), "Deleted to favorites", Toast.LENGTH_SHORT).show();
//        }
    }


    // Metodos para establecer la UI
    private void setPhoto(String url) {
        Picasso.with(SongDetailActivity.this)
                .load(url)
                .placeholder(R.drawable.artist_placeholder)
                .error(android.R.drawable.ic_dialog_alert)
                .into(photoArtist);
    }

    private void setCover(String url) {
        Picasso.with(SongDetailActivity.this)
                .load(url)
                .placeholder(R.drawable.artist_placeholder)
                .error(android.R.drawable.ic_dialog_alert)
                .into(songCover);
    }

}
