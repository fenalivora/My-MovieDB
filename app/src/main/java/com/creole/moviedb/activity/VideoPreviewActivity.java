package com.creole.moviedb.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.creole.moviedb.R;
import com.creole.moviedb.adapter.SectionPagerAdapter;
import com.creole.moviedb.model.Movie;
import com.creole.moviedb.model.Video;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class VideoPreviewActivity extends YouTubeFailureRecoveryActivity {

    private Video mVideo;
    public static final String DEVELOPER_KEY = "523487989136-vu6of0o9mrqof3u4mge1jp8u8nnn68bs.apps.googleusercontent.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_preview);
        extractIntentData();
        bindVideoDetails();
    }

    private void bindVideoDetails() {
        YouTubePlayerView youTubeView = (YouTubePlayerView) findViewById(R.id.youtube_view);
        youTubeView.initialize(DEVELOPER_KEY, this);

    }

    private void extractIntentData() {
        Intent intent = getIntent();
        mVideo = (Video) intent.getSerializableExtra("VideoResponse");

    }

    @Override
    protected YouTubePlayer.Provider getYouTubePlayerProvider() {
        return (YouTubePlayerView) findViewById(R.id.youtube_view);
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider,
                                        YouTubePlayer youTubePlayer, boolean b) {
        if (!b) {
            youTubePlayer.cueVideo(mVideo.getKey());
        }

    }
}
