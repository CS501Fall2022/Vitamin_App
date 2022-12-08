package com.example.vitamin_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {
    TabLayout tabLayout;
    TabItem news;
    PagerAdapter pagerAdapter;
    InputStream inputStream;
    static ArrayList<String[]> databaselist;
    ViewPager viewPager;
    FrameLayout frameLayout;

    @Override
    protected void onResume() {
        // Set default tab as profile
        super.onResume();
        tabLayout.selectTab(tabLayout.getTabAt(1));
        getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.fragmentLayout1, ProfileFragment.class, null)
                .commit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // set custom toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

//        inputStream = getResources().openRawResource(R.raw.supplement_sheet_final);
//        VitaminDatabaseHandler vitaminDatabaseHandler = new VitaminDatabaseHandler(HomeActivity.this);
//        inputStream = getResources().openRawResource(R.raw.supplement_sheet_final);
//        File file = new File("/data/data/com.example.vitamin_app/databases/vitamin.db");
//        file.delete();
//        VitaminDatabaseHandler vitamingDatabaseHelper = new VitaminDatabaseHandler(HomeActivity.this);
//        BufferedInputStream bf = new BufferedInputStream(inputStream);
//        BufferedReader reader = new BufferedReader(new InputStreamReader(bf, StandardCharsets.UTF_8));
//        String line;
//        try{
//            while ((line = reader.readLine()) != null) {
//                String[] str = line.split(",");
//                vitaminDatabaseHandler.addCSV(str[1], str[2], str[3], str[6]);
//            }
//        }
//        catch (IOException ex) {
//            ex.printStackTrace();
//        }
//        ArrayList<String[]> list = vitamingDatabaseHelper.getData();
//        databaselist = list;

        news = findViewById(R.id.news);
        viewPager = findViewById(R.id.fragmentcontainer);
        tabLayout = findViewById(R.id.include);
        pagerAdapter = new PagerAdapter(getSupportFragmentManager(),2);
        frameLayout = findViewById(R.id.fragmentLayout1);
        frameLayout.bringToFront();

        // Set default tab as profile
        tabLayout.selectTab(tabLayout.getTabAt(1));
        getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.fragmentLayout1, ProfileFragment.class, null)
                .commit();

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if(tab.getPosition()==2) {
                    // Survey Tab
                    viewPager.removeAllViews();
                    getSupportFragmentManager().beginTransaction()
                            .setReorderingAllowed(true)
                            .replace(R.id.fragmentLayout1, SurveyMainFragment.class, null)
                            .commit();
                }
                if (tab.getPosition()==1) {
                    // Profile Tab
                    viewPager.removeAllViews();
                    getSupportFragmentManager().beginTransaction()
                            .setReorderingAllowed(true)
                            .replace(R.id.fragmentLayout1, ProfileFragment.class, null)
                            .commit();
                }
                if(tab.getPosition()==0) {
                    // Health News Tab
                    viewPager.setAdapter(pagerAdapter);
                    Fragment oldFrag = getSupportFragmentManager().findFragmentById(R.id.fragmentLayout1);
                    if (oldFrag != null) { // Remove survey or profile fragment if currently displaying
                        getSupportFragmentManager().beginTransaction().
                                remove(oldFrag).commit();
                    }
                    pagerAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

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
//    public static ArrayList<String []> getDatabaselist(){
//        return databaselist;
//    }

}