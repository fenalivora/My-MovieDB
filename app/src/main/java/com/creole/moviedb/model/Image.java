package com.creole.moviedb.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by admin1234 on 6/8/17.
 */

public class Image {
    @SerializedName("file_path")
    private String filePath;
    @SerializedName("vote_average")
    private float voteAverage;
    @SerializedName("vote_count")
    private float voteCount;

    public Image(String filePath, int voteAverage, int voteCount) {
        this.filePath = filePath;
        this.voteAverage = voteAverage;
        this.voteCount = voteCount;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public float getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(float voteAverage) {
        this.voteAverage = voteAverage;
    }

    public float getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(float voteCount) {
        this.voteCount = voteCount;
    }
}
