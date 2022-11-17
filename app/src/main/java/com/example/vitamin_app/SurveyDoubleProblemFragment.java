package com.example.vitamin_app;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class SurveyDoubleProblemFragment extends Fragment {

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
            Fragment triple = new SurveyTripleCheckboxFragment();
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            triple.setArguments(bundle);
            fragmentTransaction.replace(R.id.checkboxLayout1, triple);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
            problem1.setText("weightloss");
            count++;
        } if (bundle.getBoolean("sleep")) {
            Fragment triple = new SurveyTripleCheckboxFragment();
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            if (count == 0) {
                triple.setArguments(bundle);
                fragmentTransaction.replace(R.id.checkboxLayout1, triple);
                problem1.setText("sleep");
            } else {
                Bundle frag_bundle = new Bundle();
                frag_bundle.putBoolean("sleep", true);
                triple.setArguments(frag_bundle);
                fragmentTransaction.replace(R.id.checkboxLayout2, triple);
                problem2.setText("sleep");
            }
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
            count++;
        } if (bundle.getBoolean("energy")) {
            Fragment doublle = new SurveyDoubleCheckboxFragment();
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            if (count == 0) {
                fragmentTransaction.replace(R.id.checkboxLayout1, doublle);
                problem1.setText("energy");
            } else {
                Bundle frag_bundle = new Bundle();
                frag_bundle.putBoolean("energy", true);
                doublle.setArguments(frag_bundle);
                fragmentTransaction.replace(R.id.checkboxLayout2, doublle);
                problem2.setText("energy");
            }
            doublle.setArguments(bundle);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
            count++;
        } if (bundle.getBoolean("immunity")) {
            Fragment doublle = new SurveyDoubleCheckboxFragment();
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            if (count == 0) {
                doublle.setArguments(bundle);
                fragmentTransaction.replace(R.id.checkboxLayout1, doublle);
                problem1.setText("immunity");
            } else {
                Bundle frag_bundle = new Bundle();
                frag_bundle.putBoolean("immunity", true);
                doublle.setArguments(frag_bundle);
                fragmentTransaction.replace(R.id.checkboxLayout2, doublle);
                problem2.setText("immunity");
            }
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
            count++;
        } if (bundle.getBoolean("skin")) {
            Fragment triple = new SurveyTripleCheckboxFragment();
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            if (count == 0) {
                triple.setArguments(bundle);
                fragmentTransaction.replace(R.id.checkboxLayout1, triple);
                problem1.setText("skin");
            } else {
                Bundle frag_bundle = new Bundle();
                frag_bundle.putBoolean("skin", true);
                triple.setArguments(frag_bundle);
                fragmentTransaction.replace(R.id.checkboxLayout2, triple);
                problem2.setText("skin");
            }
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
            count++;
        } if (bundle.getBoolean("detox")) {
            Fragment triple = new SurveyTripleCheckboxFragment();
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            if (count == 0) {
                triple.setArguments(bundle);
                fragmentTransaction.replace(R.id.checkboxLayout1, triple);
                problem1.setText("detox");
            } else {
                Bundle frag_bundle = new Bundle();
                frag_bundle.putBoolean("detox", true);
                triple.setArguments(frag_bundle);
                fragmentTransaction.replace(R.id.checkboxLayout2, triple);
                problem2.setText("detox");
            }
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
            count++;
        } if (bundle.getBoolean("exercise")) {
            Fragment doublle = new SurveyDoubleCheckboxFragment();
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            if (count == 0) {
                doublle.setArguments(bundle);
                fragmentTransaction.replace(R.id.checkboxLayout1, doublle);
                problem1.setText("exercise");
            } else {
                Bundle frag_bundle = new Bundle();
                frag_bundle.putBoolean("exercise", true);
                doublle.setArguments(frag_bundle);
                fragmentTransaction.replace(R.id.checkboxLayout2, doublle);
                problem2.setText("exercise");
            }
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
            count++;
        } if (bundle.getBoolean("digestion")) {
            Fragment doublle = new SurveyDoubleCheckboxFragment();
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            if (count == 0) {
                doublle.setArguments(bundle);
                fragmentTransaction.replace(R.id.checkboxLayout1, doublle);
                problem1.setText("digestion");
            } else {
                Bundle frag_bundle = new Bundle();
                frag_bundle.putBoolean("digestion", true);
                doublle.setArguments(frag_bundle);
                fragmentTransaction.replace(R.id.checkboxLayout2, doublle);
                problem2.setText("digestion");
            }
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
            count++;
        } if (bundle.getBoolean("articulation")) {
            Fragment doublle = new SurveyDoubleCheckboxFragment();
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            Bundle frag_bundle = new Bundle();
            frag_bundle.putBoolean("articulation", true);
            doublle.setArguments(frag_bundle);
            fragmentTransaction.replace(R.id.checkboxLayout2, doublle);problem2.setText("articulation");
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
            count++;
        }
        return v;
    }
}