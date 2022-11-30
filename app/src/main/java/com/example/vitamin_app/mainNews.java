package com.example.vitamin_app;

import java.util.ArrayList;

public class mainNews {

    private String status;
    private  String totalResult;
    private ArrayList<ModelClass> articles;

    public mainNews(String status, String totalResult, ArrayList<ModelClass> articles) {
        this.status = status;
        this.totalResult = totalResult;
        this.articles = articles;
    }

    public ArrayList<ModelClass> getArticles() {
        return articles;
    }
}
