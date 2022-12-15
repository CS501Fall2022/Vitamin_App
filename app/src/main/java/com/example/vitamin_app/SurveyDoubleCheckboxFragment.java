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

        if (bundle.getBoolean("Energy")) {
            problem1.setText("Lack of physical energy");
            problem2.setText("Lack of mental energy");
        } else if (bundle.getBoolean("Immunity")) {
            problem1.setText("Strengthen immune system");
            problem2.setText("Recovery from illness");
        }else if (bundle.getBoolean("Exercise")) {
            problem1.setText("Improve physical performance");
            problem2.setText("Improve post-execrise recovery");
        } else if (bundle.getBoolean("Digestion")) {
            problem1.setText("Struggle with general digestive problems");
            problem2.setText("Acid base problems");
        } else if (bundle.getBoolean("Articulation")) {
            problem1.setText("Joint movement discomfort");
            problem2.setText("Joint pain");
        }
        return v;
    }
}