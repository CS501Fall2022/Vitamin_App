package com.example.vitamin_app;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.RadioButton;

public class SurveyDoubleCheckboxFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_survey_double_checkbox, container, false);

        Bundle bundle = this.getArguments();
        RadioButton problem1 = (RadioButton) v.findViewById(R.id.double_problem1);
        RadioButton problem2 = (RadioButton) v.findViewById(R.id.double_problem2);

        if (bundle.getBoolean("energy")) {
            problem1.setText("energy question 1");
            problem2.setText("energy question 2");
        } else if (bundle.getBoolean("immunity")) {
            problem1.setText("immunity question 1");
            problem2.setText("immunity question 2");
        }else if (bundle.getBoolean("exercise")) {
            problem1.setText("exercise question 1");
            problem2.setText("exercise question 2");
        } else if (bundle.getBoolean("digestion")) {
            problem1.setText("digestion question 1");
            problem2.setText("digestion question 2");
        } else if (bundle.getBoolean("articulation")) {
            problem1.setText("articulation question 1");
            problem2.setText("articulation question 2");
        }
        return v;
    }
}