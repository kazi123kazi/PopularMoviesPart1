package com.example.popularmoviespart1.UtilitiesClass;

import android.net.Uri;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class NetworkUtils {

    private static final String TAG = NetworkUtils.class.getSimpleName();
    //To get image
    private final static String BASE_URL = "http://image.tmdb.org/t/p/";

    private final static String WIDTH = "w185";
    //Api endpoint-The MoviewDB
    private final static String API_ENDPOINT= "http://api.themoviedb.org/3/movie";
    private final static String PARAM_API_KEY = "api_key";

    public static URL buildUrl(String moviewSearchQuery,String apiKey){

        Uri builtUri= Uri.parse(API_ENDPOINT).buildUpon()
                .appendPath(moviewSearchQuery)
                .appendQueryParameter(PARAM_API_KEY,apiKey)
                .build();

        URL url =null;
           try {
               url=new URL(builtUri.toString());
           }
           catch (MalformedURLException e){
               e.printStackTrace();
           }
           return url;
    }

    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection=(HttpURLConnection)url.openConnection();
        try{
            InputStream in=urlConnection.getInputStream();

            Scanner scanner = new Scanner(System.in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }


    }

    public static String buildPosterUrl(String poster) {

        return BASE_URL + WIDTH + "/" + poster;

    }




}
