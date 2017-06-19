package com.creole.moviedb.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.creole.moviedb.R;
import com.creole.moviedb.adapter.MovieDetailsAdapter;
import com.creole.moviedb.model.Image;
import com.creole.moviedb.model.ImageResponse;
import com.creole.moviedb.model.Movie;
import com.creole.moviedb.rest.ApiClient;
import com.creole.moviedb.rest.ApiInterface;

import java.util.List;

import javax.security.auth.login.LoginException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.creole.moviedb.rest.ApiClient.getClient;

/**
 * Created by admin1234 on 6/7/17.
 */

public class ImageFragment extends Fragment {

    private final static String KEY_MOVIE = "key_movie";

    public MovieDetailsAdapter movieDetailsAdapter;
    private final static String API_KEY = "f66b41ed42f7671a7c9623f312add642";
    private final static String LANGUAGE = "en-US";
    private final static String INCLUDE_IMAGE_LANGUAGE = "en,null";
    public static final String TAG = "ImageFragment";
    private Movie mMovie;

    public static ImageFragment getInstance(Movie paramMovie) {
        ImageFragment movieImageFrg = new ImageFragment();
        Bundle mBundle = new Bundle();
        mBundle.putSerializable(KEY_MOVIE, paramMovie);
        movieImageFrg.setArguments(mBundle);
        return movieImageFrg;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments()!= null){
            mMovie = (Movie) getArguments().getSerializable(KEY_MOVIE);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.image_fragment, container, false);
        final RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.image_list);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(mLayoutManager);
        movieDetailsAdapter = new MovieDetailsAdapter(getContext());
        recyclerView.setAdapter(movieDetailsAdapter);


        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<ImageResponse> call = apiInterface.getImages(mMovie.getId(), API_KEY, LANGUAGE, INCLUDE_IMAGE_LANGUAGE);
        call.enqueue(new Callback<ImageResponse>() {
            @Override
            public void onResponse(Call<ImageResponse> call, Response<ImageResponse> response) {

                if (response.isSuccessful()) {
                    List<Image> imageList = response.body().getImages();
//                    ImageResponse imageResponse = response.body();
//                    Log.e(TAG, "onResponse: " + imageResponse.getImages());
                    movieDetailsAdapter.addAllData(imageList);
                }

            }

            @Override
            public void onFailure(Call<ImageResponse> call, Throwable throwable) {
                Log.e("Movie Details", throwable.getMessage());
            }
        });

        return view;

    }
}
