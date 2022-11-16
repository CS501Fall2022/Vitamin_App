package com.example.vitamin_app;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.RadioButton;

public class SurveyTripleCheckboxFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View  v = inflater.inflate(R.layout.fragment_survey_triple_checkbox, container, false);

        Bundle bundle = this.getArguments();
        RadioButton problem1 = (RadioButton) v.findViewById(R.id.triple_problem1);
        RadioButton problem2 = (RadioButton) v.findViewById(R.id.triple_problem2);
        RadioButton problem3 = (RadioButton) v.findViewById(R.id.triple_problem3);

        if (bundle.getBoolean("weight")) {
            problem1.setText("weight question 1");
            problem2.setText("weight question 2");
            problem3.setText("weight question 3");
        } else if (bundle.getBoolean("sleep")) {
            problem1.setText("sleep question 1");
            problem2.setText("sleep question 2");
            problem3.setText("sleep question 3");
        } else if (bundle.getBoolean("skin")) {
            problem1.setText("skin question 1");
            problem2.setText("skin question 2");
            problem3.setText("skin question 3");
        } else if (bundle.getBoolean("detox")) {
            problem1.setText("detox question 1");
            problem2.setText("detox question 2");
            problem3.setText("detox question 3");
        }

        return v;
    }
}