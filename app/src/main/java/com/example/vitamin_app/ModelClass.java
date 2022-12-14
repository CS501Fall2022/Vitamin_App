package com.example.vitamin_app;

//Object to hold article information from News-API
public class ModelClass {

    //Store article title, url, and url to article image.
    private String title;
    private String url;
    private String urlToImage;

    // Create a simple Model Class for the Articles.
    public ModelClass(String author, String title, String description, String url, String urlToImage, String publishedAt) {
        this.title = title;
        this.url = url;
        this.urlToImage = urlToImage;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public String getUrlToImage() {
        return urlToImage;
    }
}
