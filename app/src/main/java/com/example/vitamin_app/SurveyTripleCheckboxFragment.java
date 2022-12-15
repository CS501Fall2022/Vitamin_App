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

        if (bundle.getBoolean("Weight")) {
            problem1.setText("Prevent water retention");
            problem2.setText("Cleanse unhealthy toxins");
            problem3.setText("Appetite suppression");
        } else if (bundle.getBoolean("Sleep")) {
            problem1.setText("Mid-sleep awakenings");
            problem2.setText("Struggle with falling asleep");
            problem3.setText("Overactive thoughts and anxiety");
        } else if (bundle.getBoolean("Skin")) {
            problem1.setText("General nourishment");
            problem2.setText("Anti-aging");
            problem3.setText("Skin breaks");
        } else if (bundle.getBoolean("Detox")) {
            problem1.setText("Intestinal detox");
            problem2.setText("Liver detox");
            problem3.setText("General digestive detox");
        }

        return v;
    }
}