package com.example.vitamin_app;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
        // since only 1 view, no need to use position index
        holder.desc.setText(results[0]);
        holder.vit1.setText(results[1]);
        holder.vit2.setText(results[2]);
        holder.vit3.setText(results[3]);
        holder.vit4.setText(results[4]);
    }

    @Override
    public int getItemCount() {
        return results.length;
    }
}
