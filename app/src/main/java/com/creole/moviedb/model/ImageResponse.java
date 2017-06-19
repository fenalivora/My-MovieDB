package com.creole.moviedb.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by admin1234 on 6/7/17.
 */

public class ImageResponse {
    @SerializedName("posters")
    private List<Image> images;
    @SerializedName("id")
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }






}
