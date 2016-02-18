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
    private int id;
    private boolean isFavorite;

    private TextView  songNameDetail;
    private ImageView photoArtist;
    private ImageView songCover;
    private Button    addFavorite;
    private ContentResolver mContentResolver;
    //    private Song myCurrentSong;

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

        id                = detailSong.getId();
        String artistName = detailSong.getArtistName();
        String photo      = detailSong.getUrlSmallImage();
        String cover      = detailSong.getUrlMediumImage();
        // posteriormente llenamos los datos
        // de la cancion
        songNameDetail.setText(artistName);
        setPhoto(photo);
        setCover(cover);

////         Guardamos los datos que recibimos en un objeto tipo cancion
        // Viendolo bien no necesita guardarlo en un nuevo objeto
        // puesto que los valores ya estan puestos en nuestros widgets
//        myCurrentSong = detailSong;
//        isFavorite = myCurrentSong.getIsFavorite();
    }

    public void addToFavorites(View view){
//        isFavorite = myCurrentSong.getIsFavorite();
        if ( == 0){
            addFavorite.setText("Remove to Favorites");
            Toast.makeText(getApplicationContext(), String.valueOf(isFavorite), Toast.LENGTH_SHORT).show();
        } else {
            addFavorite.setText("Add to Favorites");
            Toast.makeText(getApplicationContext(), String.valueOf(isFavorite), Toast.LENGTH_SHORT).show();
            deleteFavorite();
        }
    }
    private void insertFavorite(){
        isFavorite = 1;
//      Usando los datos de la cancion guardada ejecutamos un insert con esos datos
//        Guardaremos el id en una variable key/value sharedPreference
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
        isFavorite = 0;
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
