package com.example.vitamin_app;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SurveySingleProblemFragment extends Fragment {

    // creating a variable for our
    // Firebase Database.
    FirebaseDatabase firebaseDatabase;

    // creating a variable for our Database
    // Reference for Firebase.
    DatabaseReference databaseReference;

    // creating a variable for
    // our object class
    Users user;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_survey_single_problem, container, false);

        Bundle bundle = this.getArguments();
        TextView header = (TextView) v.findViewById(R.id.problem_header);

        firebaseDatabase = FirebaseDatabase.getInstance();

        // below line is used to get reference for our database.
        databaseReference = firebaseDatabase.getReference("Users");

        // Retrieve username from SQL database to use for firebase
        user = new Users();

        if (bundle.getBoolean("weight")) {
            header.setText("Weight Loss");
            Fragment triple = new SurveyTripleCheckboxFragment();
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            triple.setArguments(bundle);
            fragmentTransaction.replace(R.id.checkboxLayout, triple);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        } else if (bundle.getBoolean("sleep")) {
            header.setText("Sleep");
            Fragment triple = new SurveyTripleCheckboxFragment();
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            triple.setArguments(bundle);
            fragmentTransaction.replace(R.id.checkboxLayout, triple);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        } else if (bundle.getBoolean("energy")) {
            header.setText("Energy");
            Fragment doublle = new SurveyDoubleCheckboxFragment();
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            doublle.setArguments(bundle);
            fragmentTransaction.replace(R.id.checkboxLayout, doublle);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        } else if (bundle.getBoolean("immunity")) {
            header.setText("Immunity");
            Fragment doublle = new SurveyDoubleCheckboxFragment();
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            doublle.setArguments(bundle);
            fragmentTransaction.replace(R.id.checkboxLayout, doublle);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        } else if (bundle.getBoolean("skin")) {
            header.setText("Skin");
            Fragment triple = new SurveyTripleCheckboxFragment();
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            triple.setArguments(bundle);
            fragmentTransaction.replace(R.id.checkboxLayout, triple);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        } else if (bundle.getBoolean("detox")) {
            header.setText("Detox");
            Fragment triple = new SurveyTripleCheckboxFragment();
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            triple.setArguments(bundle);
            fragmentTransaction.replace(R.id.checkboxLayout, triple);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        } else if (bundle.getBoolean("exercise")) {
            header.setText("Exercise");
            Fragment doublle = new SurveyDoubleCheckboxFragment();
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            doublle.setArguments(bundle);
            fragmentTransaction.replace(R.id.checkboxLayout, doublle);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        } else if (bundle.getBoolean("digestion")) {
            header.setText("Digestion");
            Fragment doublle = new SurveyDoubleCheckboxFragment();
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            doublle.setArguments(bundle);
            fragmentTransaction.replace(R.id.checkboxLayout, doublle);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        } else if (bundle.getBoolean("articulation")) {
            header.setText("Articulation");
            Fragment doublle = new SurveyDoubleCheckboxFragment();
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            doublle.setArguments(bundle);
            fragmentTransaction.replace(R.id.checkboxLayout, doublle);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }

        Button endSurvey = (Button) v.findViewById(R.id.endSurvey);
        endSurvey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (bundle.getBoolean("weight")) {
                    user.setProblem("weight");
                } else if (bundle.getBoolean("sleep")) {
                    user.setProblem("sleep");
                } else if (bundle.getBoolean("energy")) {
                    user.setProblem("energy");
                } else if (bundle.getBoolean("immunity")) {
                    user.setProblem("immunity");
                } else if (bundle.getBoolean("skin")) {
                    user.setProblem("skin");
                } else if (bundle.getBoolean("detox")) {
                    user.setProblem("detox");
                } else if (bundle.getBoolean("exercise")) {
                    user.setProblem("exercise");
                } else if (bundle.getBoolean("digestion")) {
                    user.setProblem("digestion");
                } else if (bundle.getBoolean("articulation")) {
                    user.setProblem("articulation");
                }


                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        databaseReference.child(user.getUsername()).setValue(user);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

        return v;
    }
}