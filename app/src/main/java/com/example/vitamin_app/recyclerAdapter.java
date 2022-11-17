package com.example.vitamin_app;
import static android.app.PendingIntent.getActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
public class recyclerAdapter extends RecyclerView.Adapter<recyclerAdapter.ViewHolder>{

    private String vitamin_names[];
    String vitamin_descriptions[];
    String episodelinks[];
    ArrayList<Integer> vitamin_img;
    Context context;
    public recyclerAdapter(Context aContext) {
        context = aContext;
        vitamin_names = aContext.getResources().getStringArray(R.array.vitamin_names);
        vitamin_descriptions = aContext.getResources().getStringArray(R.array.vitamin_descriptions);
//        episodelinks =  aContext.getResources().getStringArray(R.array.episode_links);
//        for (int i = 0; i < episodeRatings.length; i++) {
//            if (GeneralListActivity.sharedPrefs.contains(ratings + i)) {
////                Log.w("TAG", "MyCustomAdapter: " + i + " " + MainActivity.sharedPrefs.getFloat(ratings + i, 0));
//                episodeRatings[i] = MainActivity.sharedPrefs.getFloat(ratings + i, 0);
//            }
//        }
        vitamin_img = new ArrayList<Integer>();
        vitamin_img.add(R.drawable.multivit);
        vitamin_img.add(R.drawable.d3);
        vitamin_img.add(R.drawable.vitaminc);
        vitamin_img.add(R.drawable.multivit);
        vitamin_img.add(R.drawable.multivit);
        vitamin_img.add(R.drawable.multivit);

//        vitamin_img.add(R.drawable.st_arena__kirk_gorn);
//        vitamin_img.add(R.drawable.st_this_side_of_paradise__spock_in_love);
//        vitamin_img.add(R.drawable.st_mirror_mirror__evil_spock_and_good_kirk);
//        vitamin_img.add(R.drawable.st_platos_stepchildren__kirk_spock);
//        vitamin_img.add(R.drawable.st_the_naked_time__sulu_sword);
//        vitamin_img.add(R.drawable.st_the_trouble_with_tribbles__kirk_tribbles);
//        vitamin_img.add(R.drawable.st_mirror_mirror__evil_spock_and_good_kirk);
    }

    public  class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imgVitamin;
//        public TextView tvEpisodeTitle;
        public TextView vitaminDesc;
//        public RatingBar tvRatingBar;
        public Button btnRandom;

        public ViewHolder(View itemView) {
            super(itemView);
            this.imgVitamin = (ImageView) itemView.findViewById(R.id.imgVitamin);
//            this.nameVitamin = (TextView) itemView.findViewById(R.id.tvEpisodeTitle);
            this.vitaminDesc = (TextView) itemView.findViewById(R.id.vitaminDesc);
            this.btnRandom = (Button) itemView.findViewById(R.id.btnRandom);
        }
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View listItem= layoutInflater.inflate(R.layout.energy_list_items, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.vitaminDesc.setText(vitamin_descriptions[position]);
        holder.imgVitamin.setImageResource(vitamin_img.get(position).intValue());
//        holder.tvRatingBar.setRating(episodeRatings[position]);
//        holder.tvEpisodeTitle.setText(vitamin_names[position]);
        holder.btnRandom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(episodelinks[position]));
//                browserIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                context.startActivity(browserIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return vitamin_names.length;
    }
}
