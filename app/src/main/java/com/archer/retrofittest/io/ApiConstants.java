package com.archer.retrofittest.io;

/**
 * Created by Archer on 1/2/16.
 */
public class ApiConstants {
//  Popular Songs
//  https://api.myjson.com/bins/2qtzx

    public static final String URL_BASE = "https://api.myjson.com/";
    public static final String PATH_BIN = "bins/";
    public static final String PARAM_SONGS = "2qtzx";

    public static final String URL_SONGS = PATH_BIN + PARAM_SONGS;

//
//  https://api.myjson.com/bins/tl69

    public static final String PARAM_ARTISTS = "tl69";

    public static final String URL_ARTISTS = PATH_BIN + PARAM_ARTISTS;

}
