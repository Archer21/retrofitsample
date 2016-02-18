package com.archer.retrofittest.database;

import android.widget.Toast;

/**
 * Created by Archer on 17/2/16.
 */
public class DatabaseConstants {
    private void insertFavorite(){
        isFavorite = 1;
        // Usando los datos de la cancion guardada ejecutamos un insert con esos datos
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
//        String _ID = ;
//        Uri uri = FavoritesContract.Favorites.buildFavoriteUri(_ID);
//        cr.delete(uri, null, null);
    }
}
