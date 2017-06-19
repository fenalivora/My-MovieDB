package com.creole.moviedb.model;

import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin1234 on 6/2/17.
 */

public class GenreResponse implements Serializable{

    @SerializedName("genres")
    private List<Genre> results;

    public List<Genre> getResults() {
        return results;
    }

    public void setResults(List<Genre> results) {
        this.results = results;
    }
}
