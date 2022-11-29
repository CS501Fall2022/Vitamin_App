package com.example.vitamin_app;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ResultListAdapter extends RecyclerView.Adapter<ResultListAdapter.ViewHolder> {
    // need context to pull default result string and display error toasts
    public Context context;
    // content to be displayed
    private String[] results;
    // provide access to db to get user's results
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    // append supplement name to this string for search
    private static final String GOOGLE_SEARCH_URL = "https://www.google.com/search?q=webmd ";

    public ResultListAdapter(Context inContext){
        context = inContext;

        // default display in case no results in db / error
        results = context.getResources().getStringArray(R.array.default_result_display);

        // connect to db
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Users");

        // grab username of currently signed in user
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        String username = currentUser.getDisplayName();

        // search db for data of currently signed in user
        databaseReference.child(username).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()) {
                    if(task.getResult().exists()) {
                        DataSnapshot dataSnapshot = task.getResult();
                        // we have the user's info from db. do they have stored survey results
                        String problem1 = String.valueOf(dataSnapshot.child("problem").getValue());
                        String problem2 = String.valueOf(dataSnapshot.child("problem2").getValue());
                        String s1 = String.valueOf(dataSnapshot.child("supplement1").getValue());
                        String s2 = String.valueOf(dataSnapshot.child("supplement2").getValue());
                        String s3 = String.valueOf(dataSnapshot.child("supplement3").getValue());
                        String s4 = String.valueOf(dataSnapshot.child("supplement4").getValue());

                        if(problem1.equals(null) && problem2.equals(null)){
                            // user has no survey results stored.
                            // use default display
                        }
                        else if(!problem1.equals(null) && problem2.equals(null)){
                            // user has survey results stored. (1 problem)
                            results = new String[]{
                                    problem1, s1, s2, s3, s4
                            };
                        }
                        else{
                            // user has survey results stored. (2 problems)
                            results = new String[]{
                                    problem1 + "/" + problem2, s1, s2, s3, s4
                            };
                        }

                    } else {
                        Toast.makeText(context, "User does not exist",Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(context, "Failed to read data",Toast.LENGTH_LONG).show();
                }
            }
        });
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView desc;
        public TextView vit1;
        public TextView vit2;
        public TextView vit3;
        public TextView vit4;
        public Button vit_button1;
        public Button vit_button2;
        public Button vit_button3;
        public Button vit_button4;

        public ViewHolder(View itemView){
            super(itemView);
            desc = itemView.findViewById(R.id.resultDescription);
            vit1 = itemView.findViewById(R.id.vit_1);
            vit2 = itemView.findViewById(R.id.vit_2);
            vit3 = itemView.findViewById(R.id.vit_3);
            vit4 = itemView.findViewById(R.id.vit_4);
            vit_button1 = itemView.findViewById(R.id.vit_1_button);
            vit_button2 = itemView.findViewById(R.id.vit_2_button);
            vit_button3 = itemView.findViewById(R.id.vit_3_button);
            vit_button4 = itemView.findViewById(R.id.vit_4_button);
        }

        public void setUrlTarget(Button clicked, String vit_name){
            clicked.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // prevent button from opening browser if no result available
                    if(vit_name.equals("--")){
                        Toast.makeText(clicked.getContext(), "No vitamin to search for",Toast.LENGTH_LONG).show();
                    }
                    else{
                        // actual vitamin, search for info
                        Intent intent = new Intent(view.getContext(), webView.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                        intent.putExtra("url", GOOGLE_SEARCH_URL + vit_name);
                        view.getContext().startActivity(intent);
                    }
                }
            });

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
        // since only 1 view, no need to use position index
        holder.desc.setText(results[0]);
        holder.vit1.setText(results[1]);
        holder.vit2.setText(results[2]);
        holder.vit3.setText(results[3]);
        holder.vit4.setText(results[4]);

        // if default result, onclick needs to send toast (handled in function)
        holder.setUrlTarget(holder.vit_button1, results[1]);
        holder.setUrlTarget(holder.vit_button2, results[2]);
        holder.setUrlTarget(holder.vit_button3, results[3]);
        holder.setUrlTarget(holder.vit_button4, results[4]);
    }

    @Override
    public int getItemCount() {
        return 1;
    }
}
