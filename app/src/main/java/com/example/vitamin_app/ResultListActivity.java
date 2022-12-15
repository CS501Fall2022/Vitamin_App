package com.example.vitamin_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import android.widget.ImageButton;


public class ResultListActivity extends AppCompatActivity {
    RecyclerView resultDisplay;

    @Override
    protected void onResume(){
        super.onResume();
        // reset recyclerview, this allows for smooth updates to result display
        resultDisplay.setLayoutManager(new LinearLayoutManager(this));
        resultDisplay.swapAdapter(new ResultListAdapter(this), true);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_list);

        // set custom toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        // setup recycle view
        resultDisplay = findViewById(R.id.resultDisplay);
        resultDisplay.setLayoutManager(new LinearLayoutManager(this));
        resultDisplay.setAdapter(new ResultListAdapter(this));

        // setup navbar
        ImageButton toHome = (ImageButton) findViewById(R.id.toHome);
        toHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), HomeActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                view.getContext().startActivity(intent);
            }

        });

        ImageButton toList = (ImageButton) findViewById(R.id.toList);
        toList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), ResultListActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                view.getContext().startActivity(intent);
            }
        });

        ImageButton toSearch = (ImageButton) findViewById(R.id.toSearch);
        toSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), GeneralListActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                view.getContext().startActivity(intent);
            }
        });
    }

}