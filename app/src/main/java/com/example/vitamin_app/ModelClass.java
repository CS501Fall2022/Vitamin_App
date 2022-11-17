package com.example.vitamin_app;

public class ModelClass {

    private String title;
    private String url;
    private String urlToImage;

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
