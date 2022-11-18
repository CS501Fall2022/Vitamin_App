package com.example.vitamin_app;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class ResultListAdapter extends RecyclerView.Adapter<ResultListAdapter.ViewHolder> {
    public Context context;
    private String[][] results;

    // default constructor
    public ResultListAdapter(Context inContext){
        context = inContext;
        // get data here

        // data format
        results = new String[][]{
                {"PROBLEM NAME", "VIT1 NAME", "VIT2 NAME", "VIT3 NAME", "VIT4 NAME"},
                {context.getResources().getString(R.string.no_result_found), "", "" ,"" ,"" ,"" }
        };

        // < 2 results ? add placeholders here.

        // placeholder format
//        results = new String[][]{
//                {context.getResources().getString(R.string.no_result_found), "", "" ,"" ,"" ,"" },
//                {context.getResources().getString(R.string.no_result_found), "", "" ,"" ,"" ,"" }
//        };

    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView desc;
        public TextView vit1;
        public TextView vit2;
        public TextView vit3;
        public TextView vit4;

        public ViewHolder(View itemView){
            super(itemView);
            desc = itemView.findViewById(R.id.resultDescription);
            vit1 = itemView.findViewById(R.id.vit_1);
            vit2 = itemView.findViewById(R.id.vit_2);
            vit3 = itemView.findViewById(R.id.vit_3);
            vit4 = itemView.findViewById(R.id.vit_4);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // inflate result view
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View result = layoutInflater.inflate(R.layout.recycle_result, parent, false);
        return new ViewHolder(result);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // place data into result view
        holder.desc.setText(results[position][0]);
        holder.vit1.setText(results[position][1]);
        holder.vit2.setText(results[position][2]);
        holder.vit3.setText(results[position][3]);
        holder.vit4.setText(results[position][4]);
    }

    @Override
    public int getItemCount() {
        return results.length;
    }
}
