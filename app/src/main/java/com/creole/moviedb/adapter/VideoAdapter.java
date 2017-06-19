package com.creole.moviedb.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.creole.moviedb.R;
import com.creole.moviedb.model.Video;
import com.creole.moviedb.rest.CustomClickVideoListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin1234 on 6/12/17.
 */

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.ViewHolder> {

    private List<Video> videos = new ArrayList<>();
    private Context mContext;
    private CustomClickVideoListener mCustomClickVideoListener;


    public class ViewHolder extends RecyclerView.ViewHolder {

//        int mPosition;
//        View mItemView;
        TextView mTextView;

        ViewHolder(View itemView) {
            super(itemView);
//            this.mItemView = itemView;
//            this.mItemView.setOnClickListener(this);
            mTextView = (TextView) itemView.findViewById(R.id.video_name);
        }

//        void bindItemView(int position) {
//            mPosition = position;
//            mTextView.setText();
//        }
//
//        @Override
//        public void onClick(View v) {
//            mCustomClickVideoListener.onItemClick(v, mPosition);
//        }
    }

    public VideoAdapter(Context context, CustomClickVideoListener mCustomClickVideoListener) {
        this.mContext = context;
        this.mCustomClickVideoListener = mCustomClickVideoListener;
    }

    public void addAllData(List<Video> videoList) {
        videos.addAll(videoList);
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_video, parent, false);
        final ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mTextView.setText(videos.get(position).getName());
//        holder.bindItemView(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCustomClickVideoListener.onItemClick(v, position);

            }
        });
    }

    @Override
    public int getItemCount() {
        return videos.size();
    }


}
