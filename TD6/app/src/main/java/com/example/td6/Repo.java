package com.example.td6;

public class Repo {
    private int id;
    private String name;
    private String full_name;
    private String html_url;
    //getters & setters à générer avec android studio


    public Repo(int id, String name, String full_name, String html_url) {
        this.id = id;
        this.name = name;
        this.full_name = full_name;
        this.html_url = html_url;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public void setHtml_url(String html_url) {
        this.html_url = html_url;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getFull_name() {
        return full_name;
    }

    public String getHtml_url() {
        return html_url;
    }
}
