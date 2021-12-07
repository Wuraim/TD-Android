package com.example.td6;

import java.util.ArrayList;
import java.util.List;

public class RepoList {

    private List<Repo> items;

    public RepoList() {
        this.items = new ArrayList<Repo>();
    }

    public List<Repo> getItems() {
        return items;
    }

    public void setItems(List<Repo> items) {
        this.items = items;
    }
}

