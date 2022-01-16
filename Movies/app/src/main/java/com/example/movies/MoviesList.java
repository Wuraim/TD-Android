package com.example.movies;

import java.util.ArrayList;
import java.util.List;

public class MoviesList {

    private List<Movie> results;

    public MoviesList() {
        this.results = new ArrayList<Movie>();
    }

    public List<Movie> getItems() {
        return results;
    }

    public void setOriginal_title(List<Movie> results) {
        this.results = results;
    }
}
