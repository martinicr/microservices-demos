package com.example.model;


import java.util.Date;

public class Movie {

    private String title;
    private String summary;
    private Date releaseDate;

    public Movie(String title, String summary, Date releaseDate){
        this.title = title;
        this.summary = summary;
        this.releaseDate = releaseDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }
}
