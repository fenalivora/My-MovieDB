package com.creole.moviedb.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.creole.moviedb.fragment.EditFragment;
import com.creole.moviedb.fragment.ImageFragment;
import com.creole.moviedb.fragment.ShareFragment;
import com.creole.moviedb.fragment.VideoFragment;
import com.creole.moviedb.model.Movie;
import com.creole.moviedb.model.Video;

/**
 * Created by admin1234 on 6/7/17.
 */

public class SectionPagerAdapter extends FragmentStatePagerAdapter {

    private Movie mMovie;
    private Video mVideo;

    public SectionPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public void setMovieInstance(Movie paraMovie) {
        this.mMovie = paraMovie;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return ImageFragment.getInstance(mMovie);
            case 1:
                return VideoFragment.getInstance(mMovie);
            case 2:
               return ShareFragment.getInstance(mMovie);
            case 3:
                EditFragment editFragment = new EditFragment();
                return editFragment;

        }
        return null;
    }

    @Override
    public int getCount() {
        return 4;
    }
}