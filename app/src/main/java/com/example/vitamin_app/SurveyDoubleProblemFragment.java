package com.example.vitamin_app;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class SurveyDoubleProblemFragment extends Fragment {

    ArrayList<String> array = new ArrayList<String>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_survey_double_problem, container, false);

        Bundle bundle = this.getArguments();
        int count = 0;
        TextView problem1 = (TextView) v.findViewById(R.id.text_problem1);
        TextView problem2 = (TextView) v.findViewById(R.id.text_problem2);
        SeekBar seek1 = (SeekBar) v.findViewById(R.id.seekBar);
        SeekBar seek2 = (SeekBar) v.findViewById(R.id.seekBar2);

        Fragment triple1 = null;
        Fragment double1;
        Fragment triple2;
        Fragment double2;

        seek1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if (i == 0) {
                    seek1.setProgress(1);
                } else if (i == 1) {
                    seek2.setProgress(3);
                } else if (i == 2) {
                    seek2.setProgress(2);
                } else if (i == 3) {
                    seek2.setProgress(1);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        seek2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if (i == 0) {
                    seek2.setProgress(1);
                } else if (i == 1) {
                    seek1.setProgress(3);
                } else if (i == 2) {
                    seek1.setProgress(2);
                } else if (i == 3) {
                    seek1.setProgress(1);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        if (bundle.getBoolean("weight")) {
            // Create new bundles for passing info
            array.add("triple");
            triple1 = new SurveyTripleCheckboxFragment();
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            triple1.setArguments(bundle);
            fragmentTransaction.replace(R.id.checkboxLayout1, triple1);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
            problem1.setText("weightloss");
            count++;
        } if (bundle.getBoolean("sleep")) {
            if (count == 0) {
                array.add("triple");
                triple1 = new SurveyTripleCheckboxFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                triple1.setArguments(bundle);
                fragmentTransaction.replace(R.id.checkboxLayout1, triple1);
                problem1.setText("sleep");
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            } else {
                array.add("triple2");
                triple2 = new SurveyTripleCheckboxFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                Bundle frag_bundle = new Bundle();
                frag_bundle.putBoolean("sleep", true);
                triple2.setArguments(frag_bundle);
                fragmentTransaction.replace(R.id.checkboxLayout2, triple2);
                problem2.setText("sleep");
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
            count++;
        } if (bundle.getBoolean("energy")) {
            if (count == 0) {
                array.add("double");
                double1 = new SurveyDoubleCheckboxFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                double1.setArguments(bundle);
                fragmentTransaction.replace(R.id.checkboxLayout1, double1);
                problem1.setText("energy");
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            } else {
                array.add("double2");
                double2 = new SurveyDoubleCheckboxFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                Bundle frag_bundle = new Bundle();
                frag_bundle.putBoolean("energy", true);
                double2.setArguments(frag_bundle);
                fragmentTransaction.replace(R.id.checkboxLayout2, double2);
                problem2.setText("energy");
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
            count++;
        } if (bundle.getBoolean("immunity")) {
            if (count == 0) {
                array.add("double");
                double1 = new SurveyDoubleCheckboxFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                double1.setArguments(bundle);
                fragmentTransaction.replace(R.id.checkboxLayout1, double1);
                problem1.setText("immunity");
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            } else {
                array.add("double2");
                double2 = new SurveyDoubleCheckboxFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                Bundle frag_bundle = new Bundle();
                frag_bundle.putBoolean("immunity", true);
                double2.setArguments(frag_bundle);
                fragmentTransaction.replace(R.id.checkboxLayout2, double2);
                problem2.setText("immunity");
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
            count++;
        } if (bundle.getBoolean("skin")) {
            if (count == 0) {
                array.add("triple");
                triple1 = new SurveyTripleCheckboxFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                triple1.setArguments(bundle);
                fragmentTransaction.replace(R.id.checkboxLayout1, triple1);
                problem1.setText("skin");
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            } else {
                array.add("triple2");
                triple2 = new SurveyTripleCheckboxFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                Bundle frag_bundle = new Bundle();
                frag_bundle.putBoolean("skin", true);
                triple2.setArguments(frag_bundle);
                fragmentTransaction.replace(R.id.checkboxLayout2, triple2);
                problem2.setText("skin");
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
            count++;
        } if (bundle.getBoolean("detox")) {
            if (count == 0) {
                array.add("triple");
                triple1 = new SurveyTripleCheckboxFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                triple1.setArguments(bundle);
                fragmentTransaction.replace(R.id.checkboxLayout1, triple1);
                problem1.setText("detox");
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            } else {
                array.add("triple2");
                triple2 = new SurveyTripleCheckboxFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                Bundle frag_bundle = new Bundle();
                frag_bundle.putBoolean("detox", true);
                triple2.setArguments(frag_bundle);
                fragmentTransaction.replace(R.id.checkboxLayout2, triple2);
                problem2.setText("detox");
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
            count++;
        } if (bundle.getBoolean("exercise")) {
            if (count == 0) {
                array.add("double");
                double1 = new SurveyDoubleCheckboxFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                double1.setArguments(bundle);
                fragmentTransaction.replace(R.id.checkboxLayout1, double1);
                problem1.setText("exercise");
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            } else {
                array.add("double2");
                double2 = new SurveyDoubleCheckboxFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                Bundle frag_bundle = new Bundle();
                frag_bundle.putBoolean("exercise", true);
                double2.setArguments(frag_bundle);
                fragmentTransaction.replace(R.id.checkboxLayout2, double2);
                problem2.setText("exercise");
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
            count++;
        } if (bundle.getBoolean("digestion")) {
            if (count == 0) {
                array.add("double");
                double1 = new SurveyDoubleCheckboxFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                double1.setArguments(bundle);
                fragmentTransaction.replace(R.id.checkboxLayout1, double1);
                problem1.setText("digestion");
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            } else {
                array.add("double2");
                double2 = new SurveyDoubleCheckboxFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                Bundle frag_bundle = new Bundle();
                frag_bundle.putBoolean("digestion", true);
                double2.setArguments(frag_bundle);
                fragmentTransaction.replace(R.id.checkboxLayout2, double2);
                problem2.setText("digestion");
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
            count++;
        } if (bundle.getBoolean("articulation")) {
            if (count == 0) {
                array.add("double");
                double1 = new SurveyDoubleCheckboxFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                double1.setArguments(bundle);
                fragmentTransaction.replace(R.id.checkboxLayout1, double1);
                problem1.setText("articulation");
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            } else {
                array.add("double2");
                double2 = new SurveyDoubleCheckboxFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                Bundle frag_bundle = new Bundle();
                frag_bundle.putBoolean("articulation", true);
                double2.setArguments(frag_bundle);
                fragmentTransaction.replace(R.id.checkboxLayout2, double2);
                problem2.setText("articulation");
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
            count++;
        }

        Button endSurvey = (Button) v.findViewById(R.id.endSurvey2);
        Fragment finalTriple = triple1;
        endSurvey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (array.get(0).equals("triple")) {
                    RadioButton triple_p1 = (RadioButton) finalTriple.getView().findViewById(R.id.triple_problem1);

                    /*
                    if (triple_p1.isChecked()) {
                        problem1.setText("yes");
                    } */

                } else if (array.get(0).equals("double")) {

                }
                if (array.get(1).equals("triple2")) {

                } else if (array.get(1).equals("double2")) {

                }
                problem2.setText(array.get(1));
            }
        });

        return v;
    }
}