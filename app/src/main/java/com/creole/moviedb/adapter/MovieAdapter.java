package com.creole.moviedb.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.creole.moviedb.R;
import com.creole.moviedb.model.Genre;
import com.creole.moviedb.model.GenreResponse;
import com.creole.moviedb.model.Movie;
import com.creole.moviedb.rest.CustomClickItemListener;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static android.content.ContentValues.TAG;
import static android.nfc.tech.MifareUltralight.PAGE_SIZE;

/**
 * Created by admin1234 on 5/30/17.
 */

public class MovieAdapter extends RecyclerView.Adapter {
    private List<Movie> movie = new ArrayList<>();
    private int rowLayout;
    private Context context;
    private int geneId;
    CustomClickItemListener customClickItemListener;
    RecyclerView mRecyclerView;
    private boolean isLoad;
    LoadMoreItems mLoadMoreItems;
    private final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_LOADING = 1;
    private int visibleThreshold = 1;
    private int lastVisibleItem, totalItemCount;

    public interface LoadMoreItems {
        void loadItems();
    }
    public MovieAdapter(RecyclerView recyclerView, Context context, CustomClickItemListener customClickItemListener) {
        this.context = context;
        this.customClickItemListener = customClickItemListener;
        this.mRecyclerView = recyclerView;

        final LinearLayoutManager linearLayoutManager =
                (LinearLayoutManager) mRecyclerView.getLayoutManager();
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                totalItemCount = linearLayoutManager.getItemCount();
                lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
                if (!isLoad
                        && totalItemCount <= (lastVisibleItem + visibleThreshold)) {
                    if (mLoadMoreItems != null) {
                        Log.d(TAG, "onScrolled: "+"loadItem");
                        mLoadMoreItems.loadItems();
                    }
                    isLoad = true;
                }
            }

        });
    }


    class LoadingViewHolder extends RecyclerView.ViewHolder {
        public ProgressBar itemProgress;

        public LoadingViewHolder(View itemView) {
            super(itemView);
            itemProgress = (ProgressBar) itemView.findViewById(R.id.progressBar1);

        }
    }
    class ViewHolder extends RecyclerView.ViewHolder {

        public int currentItem;
        public ImageView itemImage;
        public TextView itemTitle;
        public TextView itemReleaseDate;
        public TextView itemRating;
        public TextView itemGenreId;
        public TextView itemDescription;

        public ViewHolder(View view) {
            super(view);
            itemImage = (ImageView) itemView.findViewById(R.id.thumbnail);
            itemTitle = (TextView) itemView.findViewById(R.id.title);
            itemReleaseDate = (TextView) itemView.findViewById(R.id.release_date);
            itemRating = (TextView) itemView.findViewById(R.id.rating);
            itemGenreId = (TextView) itemView.findViewById(R.id.genres_id);
            itemDescription = (TextView) itemView.findViewById(R.id.description);




        }

    }

    public void setLoadMoreItems(LoadMoreItems mLoadMoreItems) {
        this.mLoadMoreItems = mLoadMoreItems;

    }

    public void addAllData(List<Movie> movieList) {
        movie.addAll(movieList);
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        return movie.get(position) == null ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_ITEM) {
            final View mView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.list_movie, parent, false);
            return new ViewHolder(mView);
        } else if (viewType == VIEW_TYPE_LOADING) {
            final View mView = LayoutInflater.from(parent.getContext()).
                    inflate(R.layout.item_loading, parent, false);
            return new LoadingViewHolder(mView);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position)  {
        if (movie.size() > 0) {
            if (holder instanceof ViewHolder) {
                ((ViewHolder) holder).itemTitle.setText(movie.get(position).getTitle());
                ((ViewHolder) holder).itemReleaseDate.setText(movie.get(position).getReleaseDate());
                ((ViewHolder) holder).itemDescription.setText(movie.get(position).getOverview());
                ((ViewHolder) holder).itemRating.setText(movie.get(position).getVoteAverage().toString());
                ((ViewHolder) holder).itemReleaseDate.setText(String.valueOf(getYearFromDate(movie.get(position).getReleaseDate())));
                ((ViewHolder) holder).itemGenreId.setText(movie.get(position).getGenresName());
                String path = "https://image.tmdb.org/t/p/w500" + movie.get(position).getPosterPath();
                Picasso.with(context).load(path).into(((ViewHolder) holder).itemImage);
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        customClickItemListener.onItemClick(movie.get(position));
                    }
                });
            } else if (holder instanceof LoadingViewHolder) {
                ((LoadingViewHolder) holder).itemProgress.setIndeterminate(true);
            }
        }
    }
    private int getYearFromDate(String releaseDate) {
        SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date yourDate = parser.parse(releaseDate);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(yourDate);
            int year = calendar.get(Calendar.YEAR);
            return year;

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return 0;
    }

    @Override
    public int getItemCount() {
        return movie.size();
    }

    public void setLoaded() {
        isLoad = false;
    }

    public void showProcessItem() {
        movie.add(null);
        notifyItemInserted(movie.size()-1);
        int size=movie.size()-1;
        Log.d(TAG, "showProcessItem: "+size);
    }

    public void removeProcessItem() {

        movie.remove(movie.size()-1);
        notifyItemRemoved(movie.size());
        int size=movie.size();
        Log.d(TAG, "removeProcessItem: "+size);
    }


}
