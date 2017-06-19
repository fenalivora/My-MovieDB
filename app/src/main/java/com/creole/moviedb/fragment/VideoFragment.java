package com.creole.moviedb.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.creole.moviedb.R;
import com.creole.moviedb.activity.VideoPreviewActivity;
import com.creole.moviedb.adapter.VideoAdapter;
import com.creole.moviedb.model.Movie;
import com.creole.moviedb.model.Video;
import com.creole.moviedb.model.VideoResponse;
import com.creole.moviedb.rest.ApiClient;
import com.creole.moviedb.rest.ApiInterface;
import com.creole.moviedb.rest.CustomClickVideoListener;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by admin1234 on 6/7/17.
 */

public class VideoFragment extends Fragment implements CustomClickVideoListener {
    private final static String API_KEY = "f66b41ed42f7671a7c9623f312add642";
    private final static String LANGUAGE = "en-US";
    private Movie mMovie;
    private VideoAdapter mVideoAdapter;
    private final static String KEY_MOVIE = "key_movie";
    private Video mVideo;
    private VideoResponse mVideoResponse;
    private CustomClickVideoListener customClickVideoListener;

    public static VideoFragment getInstance(Movie paramMovie) {
        VideoFragment videoFrg = new VideoFragment();
        Bundle mBundle = new Bundle();
        mBundle.putSerializable(KEY_MOVIE, paramMovie);
        videoFrg.setArguments(mBundle);
        return videoFrg;
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
        View view = inflater.inflate(R.layout.video_fragment, container, false);
        final RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.video_list);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);

        mVideoAdapter = new VideoAdapter(getActivity(),this);
        recyclerView.setAdapter(mVideoAdapter);

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<VideoResponse> call = apiInterface.getVideos(mMovie.getId(), API_KEY, LANGUAGE);
        call.enqueue(new Callback<VideoResponse>() {
            @Override
            public void onResponse(Call<VideoResponse> call, Response<VideoResponse> response) {
                if (response.isSuccessful()){
                    mVideoResponse = response.body();
                    List<Video> videos = response.body().getVideos();
                    mVideoAdapter.addAllData(videos);
                }
            }

            @Override
            public void onFailure(Call<VideoResponse> call, Throwable throwable) {
                Log.e("Movie videos", throwable.getMessage());

            }
        });

        return view;

    }

    @Override
    public void onItemClick(View view, int position) {
        Intent intent = new Intent(getActivity(), VideoPreviewActivity.class);
        Video selectedVideo = mVideoResponse.getVideos().get(position);
        intent.putExtra("VideoResponse", selectedVideo);
        startActivity(intent);
    }
}
