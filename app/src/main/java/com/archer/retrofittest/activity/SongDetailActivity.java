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
    public static final String PREFS_NAME = "FAVORITES";
    public  final String INSERT_FAVORITE = SongDetailActivity.this.getResources().getString(R.string.add_favorite);
    public  final String DELETE_FAVORITE = SongDetailActivity.this.getResources().getString(R.string.delete_favorite);

    private static final String LOG_TAG = SongDetailActivity.class.getSimpleName();
    private int songId;

    private String favoriteSongId;
    private TextView  songNameDetail;
    private ImageView photoArtist;
    private ImageView songCover;
    private Button    addFavorite;

    private ContentResolver mContentResolver;
    private boolean isFavorite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        songNameDetail  = (TextView)  findViewById(R.id.songNameDetail);
        photoArtist     = (ImageView) findViewById(R.id.photo);
        songCover       = (ImageView) findViewById(R.id.cover);
        addFavorite     = (Button)    findViewById(R.id.add_to_favorites);

        // Recibimos el objeto por medio de getParcelable
        Song detailSong   = getIntent().getParcelableExtra(CURRENT_SONG);

        songId            = detailSong.getId();
        favoriteSongId    = String.valueOf(songId);
        String artistName = detailSong.getArtistName();
        String photo      = detailSong.getUrlSmallImage();
        String cover      = detailSong.getUrlMediumImage();
        // posteriormente llenamos los datos
        // de la cancion
        Log.d(LOG_TAG, String.valueOf(songId));
        songNameDetail.setText(artistName);
        setPhoto(photo);
        setCover(cover);

        // Favorites
        isFavorite = FavoritesPreferences.getFavoriteId(SongDetailActivity.this, favoriteSongId);

    }

    // Shared Preferences

    public void addToFavorites(View view){
        if (isFavorite){
            addFavorite.setText(DELETE_FAVORITE);
            FavoritesPreferences.setFavoriteId(getApplicationContext(), favoriteSongId, true);
            Log.d(LOG_TAG, "The id is " + favoriteSongId + " " + String.valueOf(isFavorite));
        } else {
            addFavorite.setText(INSERT_FAVORITE);
            Log.d(LOG_TAG, "The id is " + favoriteSongId + " " + String.valueOf(isFavorite));
            deleteFavorite();
        }
    }
    private void insertFavorite(){
//        ContentValues contentValues = new ContentValues();
//        mContentResolver = this.getContentResolver();
//        Uri uri = FavoritesContract.URI_TABLE;
//        String title = myCurrentSong.getName();
//        String image = myCurrentSong.getUrlSmallImage();
//        contentValues.put(FavoritesContract.Favorites.FAVORITES_TITLE, title);
//        contentValues.put(FavoritesContract.Favorites.FAVORITES_IMAGE, image);
//        mContentResolver.insert(uri, contentValues);
        Toast.makeText(getApplicationContext(), "Add to favorites", Toast.LENGTH_SHORT).show();
    }

    private void deleteFavorite(){
//        ContentResolver cr = this.getContentResolver();
//        Al momento de borrar el favorito accedemos a la sharedPreference con la key del id del favorito
//        y procederemos a eliminar esa sharedPreference
//        String _ID = ;
//        Uri uri = FavoritesContract.Favorites.buildFavoriteUri(_ID);
//        cr.delete(uri, null, null);
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
