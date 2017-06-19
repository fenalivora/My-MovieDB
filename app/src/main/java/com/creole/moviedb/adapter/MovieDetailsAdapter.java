package com.creole.moviedb.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.creole.moviedb.R;
import com.creole.moviedb.fragment.EditFragment;
import com.creole.moviedb.fragment.ImageFragment;
import com.creole.moviedb.fragment.ShareFragment;
import com.creole.moviedb.fragment.VideoFragment;
import com.creole.moviedb.model.Image;
import com.creole.moviedb.model.ImageResponse;
import com.creole.moviedb.model.Movie;
import com.creole.moviedb.model.Video;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin1234 on 6/5/17.
 */

public class MovieDetailsAdapter extends RecyclerView.Adapter<MovieDetailsAdapter.ViewHolder> {

    private List<Image> images = new ArrayList<>();
    private Context context;


    class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);

            imageView = (ImageView) itemView.findViewById(R.id.list_image);
        }
    }

    public MovieDetailsAdapter(Context context) {
        this.context = context;
    }

    public void addAllData(List<Image> imageList) {
        images.addAll(imageList);
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_image, parent, false);
        final ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        String path = "https://image.tmdb.org/t/p/w500" + images.get(position).getFilePath();
        Picasso.with(context).load(path).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return images.size();
    }
}
