package com.example.popularmoviespart1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.Menu;
import android.view.MenuItem;

import com.example.popularmoviespart1.ModelClass.MoviesClass;
import com.example.popularmoviespart1.UtilitiesClass.JsonUtils;
import com.example.popularmoviespart1.UtilitiesClass.NetworkUtils;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MovieAdapter.ListItemClickListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    private static final String SORT_POPULAR = "popular";
    private static final String SORT_TOP_RATED = "top_rated";
    private static String currentSort = SORT_POPULAR;

    private ArrayList<MoviesClass> movieList;

    private MovieAdapter mMovieAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //recyclerview
        RecyclerView mMovieRecyclerView = (RecyclerView) findViewById(R.id.rv_main);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        mMovieRecyclerView.setLayoutManager(layoutManager);
        mMovieRecyclerView.setHasFixedSize(true);

        mMovieAdapter = new MovieAdapter(movieList, this, this);
        mMovieRecyclerView.setAdapter(mMovieAdapter);

        LoadMovies();
    }

    private void LoadMovies() {
        if (movieList == null || movieList.isEmpty()) {
            makeMovieSearchQuery();
        } else {
            mMovieAdapter.setMovieData(movieList);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_sort_popular && !currentSort.equals(SORT_POPULAR)) {
            ClearMovieItemList();
            currentSort = SORT_POPULAR;
            LoadMovies();
            return true;
        }
        if (id == R.id.action_sort_top_rated && !currentSort.equals(SORT_TOP_RATED)) {
            ClearMovieItemList();
            currentSort = SORT_TOP_RATED;
            LoadMovies();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void ClearMovieItemList() {
        if (movieList != null) {
            movieList.clear();
        }
    }

    private void makeMovieSearchQuery() {
        String movieQuery = currentSort;
        URL movieSearchUrl = NetworkUtils.buildUrl(movieQuery, getText(R.string.api_key).toString());
        new MoviesQueryTask().execute(movieSearchUrl);
    }

    // AsyncTask to perform query
    public class MoviesQueryTask extends AsyncTask<URL, Void, String> {

        @Override
        protected String doInBackground(URL... params) {
            URL searchUrl = params[0];
            String searchResults = null;
            try {
                searchResults = NetworkUtils.getResponseFromHttpUrl(searchUrl);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return searchResults;
        }

        @Override
        protected void onPostExecute(String searchResults) {
            if (searchResults != null && !searchResults.equals("")) {
                movieList = JsonUtils.parseMoviesJson(searchResults);
//                Log.d(TAG,"Num movies = " + movieList.size());
                mMovieAdapter.setMovieData(movieList);
            }
        }
    }

    @Override
    public void OnListItemClick(MoviesClass movieItem) {
        //Toast.makeText(this, " clicked " + movieItem.getTitle(), Toast.LENGTH_SHORT).show();
        Intent myIntent = new Intent(this, MoviesDetails.class);
        myIntent.putExtra("movieItem", (Serializable) movieItem);
        startActivity(myIntent);
    }
}
