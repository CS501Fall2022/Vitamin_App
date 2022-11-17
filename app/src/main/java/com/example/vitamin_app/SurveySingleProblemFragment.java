package com.example.vitamin_app;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class SurveySingleProblemFragment extends Fragment {

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

        return v;
    }
}