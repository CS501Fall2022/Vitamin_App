package com.example.vitamin_app;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HealthFragment extends Fragment {

    // Set the filters for the kind of news that you would like to retrieve. You can filter based
    // on the country and category of  the news.
    String api="60d7ed3a1b204cc7aabdd73fa2dc124f";
    ArrayList<ModelClass> modelClassArrayList;
    Adapter adapter;
    String country="us";
    private RecyclerView health;
    private String category="health";

    // Create an adapter that will be used to dynamically create a list of news articles.
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.healthfragment,null);


        health = view.findViewById(R.id.recyclerviewofhealth);
        modelClassArrayList = new ArrayList<>();
        health.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new Adapter(getContext(),modelClassArrayList);
        health.setAdapter(adapter);

        update();
        return view ;
    }

    // Makes an Api call to newsapi.com with the specified filter
    // and the provided Api key
    private void update() {
        // Api call uses a predefined ‘Get’ statement in the ApiInterface class.
        ApiUtilites.getApiInterface().getCategoryNews(country,category,100,api).enqueue(new Callback<mainNews>() {
            @Override
            public void onResponse(Call<mainNews> call, Response<mainNews> response) {
                if(response.isSuccessful())
                {
                    modelClassArrayList.addAll(response.body().getArticles());
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<mainNews> call, Throwable t) {}
        });

    }
}
