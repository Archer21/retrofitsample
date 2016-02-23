package com.archer.retrofittest.ui.fragments;


import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.app.ShareCompat;
import android.support.v4.content.Loader;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.archer.retrofittest.R;
import com.archer.retrofittest.activity.SongDetailActivity;
import com.archer.retrofittest.database.FavoritesContract;
import com.archer.retrofittest.database.FavoritesLoader;
import com.archer.retrofittest.domain.Song;
import com.archer.retrofittest.ui.adapters.FavoritesAdapter;
import com.archer.retrofittest.ui.uiutils.RecyclerItemClickListener;
import com.archer.retrofittest.utils.FavoritesPreferences;

import java.util.ArrayList;
import java.util.List;

public class FavoritesFragment extends Fragment implements LoaderManager.LoaderCallbacks<List<Song>>{

    private static final int NUM_COLS = 4;
    private static final String SIMPLE_ROW_CURSOR = "SIMPLE_ROW_CURSOR";
    private RecyclerView mRecyclerView;
    private FavoritesAdapter mFavoritesAdapter;
    private ContentResolver mContentResolver;
    private static final int LOADER_ID = 1;
    private List<Song> mData;
    private Song favoriteData;

    public FavoritesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mData = new ArrayList<>();
        mFavoritesAdapter = new FavoritesAdapter(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_favorites, container, false);
        mRecyclerView = (RecyclerView) root.findViewById(R.id.fragment_favorites_main_container);
        setConfig();
        setHasOptionsMenu(true);
        return root;
    }

//    private void setDummieContent(){
//        List<Song> dummieSongs = new ArrayList<>();
//        Song masayumeChasing = new Song(Parcel.obtain());
//        masayumeChasing.setName("Masayume Chasing");
//        masayumeChasing.setUrlSmallImage("http://40.media.tumblr.com/d588a3da1d3a96dedae4cefb42337a23/tumblr_n9auqslLue1tvlvpyo7_500.jpg");
//
//        Song pray = new Song(Parcel.obtain());
//        pray.setName("Pray");
//        pray.setUrlSmallImage("http://vignette3.wikia.nocookie.net/lyricwiki/images/5/5b/Tommy_Heavenly6_-_Hey_My_Friend.jpg/revision/latest?cb=20090616001310");
//
//        Song kizuna = new Song(Parcel.obtain());
//        kizuna.setName("Kizuna ni nosete");
//        kizuna.setUrlSmallImage("https://kuromisub.files.wordpress.com/2015/07/6278e-hayamiwhv-1000572325.jpg");
//
//        dummieSongs.add(masayumeChasing);
//        dummieSongs.add(pray);
//        dummieSongs.add(kizuna);
//
//        mFavoritesAdapter.setData(dummieSongs);
//        mFavoritesAdapter.notifyDataSetChanged();
//    }

    private void setConfig(){
        mContentResolver = getActivity().getContentResolver();
        setHasOptionsMenu(true);
        getLoaderManager().initLoader(LOADER_ID, null, this);

        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), NUM_COLS));
        mRecyclerView.setAdapter(mFavoritesAdapter);
        mRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), mRecyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                goToSimpleRow(view, position);
            }

            @Override
            public void onItemLongClick(final View view, int position) {
//                Toast.makeText(getActivity(), "Log click" , Toast.LENGTH_SHORT).show();
//                PopupMenu popupMenu = new PopupMenu(getActivity(), view);
//                popupMenu.getMenuInflater().inflate(R.menu.popup_menu, popupMenu.getMenu());
//
//                final View v = view;
//                final int pos = position;
//
//                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
//                    @Override
//                    public boolean onMenuItemClick(MenuItem item) {
////                        Toast.makeText(getActivity(),"You Clicked : " + item.getTitle(),Toast.LENGTH_SHORT).show();
//                        delete(v, pos);
//                        return false;
//                    }
//                });
//                popupMenu.show();
            }
        }));
    }

    public void goToSimpleRow(View view, int position){
        String _ID = ((TextView) view.findViewById(R.id.item_id)).getText().toString();
        ContentResolver cr = getActivity().getContentResolver();
        Uri uri = FavoritesContract.Favorites.buildFavoriteUri(_ID);
        Cursor cursor = cr.query(uri, null, null, null, _ID);
        String name = cursor.getString(cursor.getColumnIndex(FavoritesContract.Favorites.FAVORITES_TITLE))
        favoriteData.setName();
        Intent intent = new Intent(getActivity(), SongDetailActivity.class);
        intent.putExtra(SIMPLE_ROW_CURSOR, song);
        cursor.close();
    }

//    public void delete(View view, int position){
////        String _ID = ((TextView) view.findViewById(R.id.item_id)).getText().toString();
////        String title = ((TextView) view.findViewById(R.id.favorite_title)).getText().toString();
////        Toast.makeText(getActivity(), "id: " + _ID + ", title: " + title, Toast.LENGTH_SHORT).show();
//        ContentResolver cr = getActivity().getContentResolver();
////        String _ID = FavoritesPreferences.getFavoriteId();
//        Uri uri = FavoritesContract.URI_TABLE;
//        cr.delete(uri, null, null);
//        mData.clear();
//
////        FavoritesPreferences.setFavoriteId(getApplicationContext(), favoriteSongId, false);
////        isFavorite = FavoritesPreferences.getFavoriteId(getApplicationContext(), favoriteSongId);
//////        Toast.makeText(getApplicationContext(), "Deleted to favorites", Toast.LENGTH_SHORT).show();
//    }

    @Override
    public Loader<List<Song>> onCreateLoader(int id, Bundle args) {
        mContentResolver = getActivity().getContentResolver();
        return new FavoritesLoader(getActivity(), mContentResolver);
    }

    @Override
    public void onLoadFinished(Loader<List<Song>> loader, final List<Song> data) {
//        Ease implementation first

//        this.mData = data;
//        Thread thread = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                //This is not helpfull now but when we do searchs operations or long tasks
//                //help us to dont block the ui thread
//
//                mSongAdapter.addAll(data);
//            }
//        });

        mFavoritesAdapter.setData(data);
        mData = data;
    }

    @Override
    public void onLoaderReset(Loader<List<Song>> loader) {
        List<Song> placeholderArray = new ArrayList<>();
        mFavoritesAdapter.setData(placeholderArray);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_favorite, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.delete_favorites) {
            deleteAllFavorites();
        }

        return super.onOptionsItemSelected(item);
    }
    public void deleteAllFavorites(){
        ContentResolver cr = getActivity().getContentResolver();
        Uri uri = FavoritesContract.URI_TABLE;
        cr.delete(uri, null, null);
        FavoritesPreferences.clearPreferences(getActivity());
        mData.clear();
        mFavoritesAdapter.notifyDataSetChanged();

    }
}

