package com.creole.moviedb.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.creole.moviedb.R;
import com.creole.moviedb.adapter.MovieAdapter;
import com.creole.moviedb.model.GenreResponse;
import com.creole.moviedb.model.Movie;
import com.creole.moviedb.model.MovieResponse;
import com.creole.moviedb.rest.ApiClient;
import com.creole.moviedb.rest.ApiInterface;
import com.creole.moviedb.rest.CustomClickItemListener;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements CustomClickItemListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    private final static String API_KEY = "f66b41ed42f7671a7c9623f312add642";
    private final static String LANGUAGE = "en-US";
    private int PAGE = 1;
    private GenreResponse genreResponse;
    MovieAdapter mAdapter;
    private MovieResponse movieResponse;
    private List<Movie> movie = new ArrayList<>();
    private RecyclerView recyclerView;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = (ProgressBar) findViewById(R.id.progress_main);
        final Intent intent = getIntent();
        genreResponse = (GenreResponse) intent.getSerializableExtra("abc");

        recyclerView = (RecyclerView) findViewById(R.id.movie_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mAdapter = new MovieAdapter(recyclerView, this, this);
        recyclerView.setAdapter(mAdapter);

        getMovieList();
        mAdapter.setLoadMoreItems(loadMoreItems);
    }

    private void getMovieList() {
        showProgressBar();
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

        //call request
        Call<MovieResponse> call = apiInterface.getTopRatedMovies(API_KEY, LANGUAGE, PAGE);
        call.enqueue(new Callback<MovieResponse>() {

            //call response
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {

                hideProgressBar();
                mAdapter.setLoaded();
                int statusCode = response.code();
                if (response.isSuccessful()) {
                    movieResponse = response.body();
                    List<Movie> movie = response.body().getResults();
                    for (Movie movie1 : movie) {
                        movie1.setGenresName(genreResponse.getResults());
                    }

                    mAdapter.addAllData(movie);
                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable throwable) {
                Log.e(TAG, toString());
                mAdapter.setLoaded();
                hideProgressBar();
            }
        });

    }

    public void hideProgressBar() {
        if (PAGE == 1) {
            progressBar.setVisibility(View.GONE);
        } else {
            mAdapter.removeProcessItem();
        }
    }

    public void showProgressBar() {
        if (PAGE == 1) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            mAdapter.showProcessItem();
        }

    }


    @Override
    public void onItemClick(Movie movie) {
        Intent intent = new Intent(MainActivity.this, MovieDetailsActivity.class);
        intent.putExtra("movieresponse", movie);
        startActivity(intent);
    }

    MovieAdapter.LoadMoreItems loadMoreItems=new MovieAdapter.LoadMoreItems() {
        @Override
        public void loadItems() {
            PAGE = PAGE + 1;
            Log.d(TAG, "loadItems: "+PAGE);
            getMovieList();
        }
    };

}
