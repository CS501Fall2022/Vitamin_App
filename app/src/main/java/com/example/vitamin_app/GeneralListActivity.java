package com.example.vitamin_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class GeneralListActivity extends AppCompatActivity {

//    RecyclerView rvVitamins;
//    rvVitamins = (RecyclerView) v.findViewById(R.id.rvVitamins);
//    RecyclerView.Adapter rvAdapter = new recyclerAdapter(this.getContext());
//        rvVitamins.setLayoutManager(new LinearLayoutManager(this.getContext()));
//        rvVitamins.setAdapter(rvAdapter);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general_list);



        ImageButton toHome = (ImageButton) findViewById(R.id.toHome);
        toHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), HomeActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                view.getContext().startActivity(intent);
            }
        });

        ;
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



//
//
//package com.example.sse.recycleview_sse;
//        import android.content.ActivityNotFoundException;
//        import android.content.Context;
//        import android.content.Intent;
//        import android.content.SharedPreferences;
//        import android.media.MediaPlayer;
//        import android.net.Uri;
//
//        import android.os.Bundle;
//        import android.util.Log;
//        import android.view.LayoutInflater;
//        import android.view.Menu;
//        import android.view.MenuItem;
//        import android.view.View;
//        import android.view.ViewGroup;
//        import android.widget.BaseAdapter;
//        import android.widget.Button;
//        import android.widget.ImageView;
//        import android.widget.ListAdapter;
//        import android.widget.ListView;
//        import android.widget.RatingBar;
//        import android.widget.TextView;
//        import android.widget.Toast;
//        import android.widget.Adapter;
//
//        import androidx.annotation.NonNull;
//        import androidx.appcompat.app.AppCompatActivity;
//        import androidx.recyclerview.widget.LinearLayoutManager;
//        import androidx.recyclerview.widget.RecyclerView;
//
//        import java.util.ArrayList;
//public class MainActivity extends AppCompatActivity {
//    public static SharedPreferences sharedPrefs;
//    private static Context context;
//    private
//    RecyclerView rvEpisodes;
//    RecyclerView.Adapter rvAdapter;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        sharedPrefs = this.getSharedPreferences("application", MODE_PRIVATE);
//        rvEpisodes = (RecyclerView) findViewById(R.id.rvEpisodes);
//        setAdapter();
//    }
//
//    private void setAdapter() {
//        MyListAdapter adapter = new MyListAdapter(this.getBaseContext());
//        rvEpisodes.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
//        rvEpisodes.setAdapter(adapter);
//    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.my_test_menu, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        int id = item.getItemId();
//        if (id == R.id.mnu_zero) {
//            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://shop.startrek.com/"));
//            startActivity(browserIntent);
//            return true;
//        }
//        if (id == R.id.mnu_one) {
//            Intent dialIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel: 1800startrk "));
//            startActivity(dialIntent);
//            return true;
//        }
//        if (id == R.id.mnu_two) {
//            Intent sendIntent = new Intent(Intent.ACTION_VIEW);
//            sendIntent.setData(Uri.parse("sms:"));
//            sendIntent.putExtra("sms_body", "Ouch!");
//            startActivity(sendIntent);
//            return true;
//        }
//        if (id == R.id.mnu_three) {
//
//            MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.livelong);
//            mediaPlayer.start();
//            return true;
//        }
//
//        if (id == R.id.mnu_four) {
//            try {
//                String newVideoPath = "android.resource://" + getPackageName() + "/" + R.raw.khan;
//                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(newVideoPath));
//                intent.setDataAndType(Uri.parse(newVideoPath), "video/mp4");
//                startActivity(intent);
//            } catch (ActivityNotFoundException e) {
//                Toast.makeText(getBaseContext(), "App to play video not found", Toast.LENGTH_LONG).show();
//            }
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
//}


