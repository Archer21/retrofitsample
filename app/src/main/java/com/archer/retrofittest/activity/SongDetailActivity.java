package com.archer.retrofittest.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.archer.retrofittest.R;
import com.archer.retrofittest.domain.Song;
import com.squareup.picasso.Picasso;

public class SongDetailActivity extends AppCompatActivity {

    private static final String CURRENT_SONG = "CURRENT_SONG";

    private TextView songNameDetail;
    private ImageView photoArtist;
    private ImageView songCover;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Song detailSong = getIntent().getParcelableExtra(CURRENT_SONG);
        songNameDetail  = (TextView) findViewById(R.id.songNameDetail);
        photoArtist     = (ImageView) findViewById(R.id.photo);
        songCover     = (ImageView) findViewById(R.id.cover);


        String artistName = detailSong.getArtistName();
        String photo  = detailSong.getUrlSmallImage();
        String cover = detailSong.getUrlMediumImage();

        setPhoto(photo);
        setCover(cover);

        songNameDetail.setText(artistName);

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
