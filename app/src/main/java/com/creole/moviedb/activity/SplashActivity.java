package com.creole.moviedb.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.creole.moviedb.R;
import com.creole.moviedb.model.GenreResponse;
import com.creole.moviedb.model.Movie;
import com.creole.moviedb.model.MovieResponse;
import com.creole.moviedb.rest.ApiClient;
import com.creole.moviedb.rest.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by admin1234 on 6/1/17.
 */

public class SplashActivity extends AppCompatActivity {

    private final static String API_KEY = "f66b41ed42f7671a7c9623f312add642";
    private final static String LANGUAGE = "en-US";
    private static final String TAG = SplashActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        if (API_KEY.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please obtain your API key from themoviedb.org first", Toast.LENGTH_LONG).show();
        }

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<GenreResponse> call = apiInterface.getGenres(API_KEY, LANGUAGE);

        call.enqueue(new Callback<GenreResponse>() {
            @Override
            public void onResponse(Call<GenreResponse> call, Response<GenreResponse> response) {

                int statusCode = response.code();
                if (response.isSuccessful()) {
                    GenreResponse genreResponse = response.body();
                    Log.e(TAG, "onResponse: " + genreResponse.getResults());

                    Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                    intent.putExtra("abc", genreResponse);
                    startActivity(intent);
                    finish();
                }
            }

            @Override
            public void onFailure(Call<GenreResponse> call, Throwable throwable) {
                Log.e("Splash", toString());

            }
        });


    }

}
