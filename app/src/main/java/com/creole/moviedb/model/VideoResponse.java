package com.creole.moviedb.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by admin1234 on 6/12/17.
 */

public class VideoResponse implements Serializable {
    @SerializedName("results")
    private List<Video> videos;
    @SerializedName("id")
    private int id;


    public List<Video> getVideos() {
        return videos;
    }

    public void setVideos(List<Video> videos) {
        this.videos = videos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
