package com.example.popularmoviespart1.ModelClass;

public class MoviesClass {

    private String title;
    private String releaseDate;
    private String vote; //rating
    private String popularity;
    private String synopsis;  //overview in api
    private String image;
    private String backdrop;  //Relative path for image in api

    //Default Constructor
    public MoviesClass() {
    }

    //Parameterised Constructor
    public MoviesClass(String title , String releaseDate , String vote , String popularity , String synopsis , String image , String backdrop) {
        this.title = title;
        this.releaseDate = releaseDate;
        this.vote = vote;
        this.popularity = popularity;
        this.synopsis = synopsis;
        this.image = image;
        this.backdrop = backdrop;
    }

    //All getter and setter
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getVote() {
        return vote;
    }

    public void setVote(String vote) {
        this.vote = vote;
    }

    public String getPopularity() {
        return popularity;
    }

    public void setPopularity(String popularity) {
        this.popularity = popularity;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getBackdrop() {
        return backdrop;
    }

    public void setBackdrop(String backdrop) {
        this.backdrop = backdrop;
    }
}
