package com.creole.moviedb.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.creole.moviedb.R;
import com.creole.moviedb.adapter.SectionPagerAdapter;
import com.creole.moviedb.model.Movie;
import com.squareup.picasso.Picasso;

/**
 * Created by admin1234 on 6/5/17.
 */

public class MovieDetailsActivity extends AppCompatActivity {

    private final static String API_KEY = "f66b41ed42f7671a7c9623f312add642";

    private static final String TAG = MovieDetailsActivity.class.getSimpleName();
    private CollapsingToolbarLayout mCollapsingToolbarLayout = null;

    private SectionPagerAdapter mPagerAdapter;
    private ViewPager mViewPager;
    ImageView image;
    TextView title;
    TextView overview;
    private Movie mMovie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        extractIntentData();
        bindMovieDetails();
        bindPagerAdapter();
        initCollapsingToolbar();
        bindTabLayout();
    }

    private void initCollapsingToolbar(){
        mCollapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapse_toolbar);
        mCollapsingToolbarLayout.setTitle(" ");
    }

    private void bindMovieDetails() {
        image = (ImageView) findViewById(R.id.movie_image);
        title = (TextView) findViewById(R.id.movie_title);
        overview = (TextView) findViewById(R.id.overview);

        String imagePath = "https://image.tmdb.org/t/p/w500" + mMovie.getBackdropPath();
        Picasso picasso = Picasso.with(this);
        picasso.setLoggingEnabled(true);
        picasso.load(imagePath).into(image);


        String textTitle = mMovie.getTitle();
        String textOverview = mMovie.getOverview();
        title.setText(textTitle);
        overview.setText(textOverview);
    }

    private void bindPagerAdapter() {
        mPagerAdapter = new SectionPagerAdapter(getSupportFragmentManager());
        mPagerAdapter.setMovieInstance(mMovie);
        mViewPager = (ViewPager) findViewById(R.id.view_pager);
        mViewPager.setAdapter(mPagerAdapter);
    }

    private void bindTabLayout(){
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));
    }

    private void extractIntentData(){
        Intent intent = getIntent();
        mMovie = (Movie) intent.getSerializableExtra("movieresponse");
    }
}
