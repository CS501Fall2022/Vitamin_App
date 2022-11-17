package com.example.vitamin_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        DatabaseHelper databaseHelper = new DatabaseHelper(HomeActivity.this);
        boolean success = false;
        for(int i = 0; i < 7; i++){
            success = databaseHelper.addOne();
        }
        if(success == true){
            Toast.makeText(HomeActivity.this, "Success!",Toast.LENGTH_LONG).show();
        }
        ImageButton toHome = (ImageButton) findViewById(R.id.toHome);
        toHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
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

        Button toSurvey = (Button) findViewById(R.id.toSurvey);
        toSurvey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), SurveyActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                view.getContext().startActivity(intent);
            }
        });
    }
}