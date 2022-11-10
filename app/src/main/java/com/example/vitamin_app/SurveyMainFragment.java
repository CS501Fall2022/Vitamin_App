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
import android.widget.Toast;

public class SurveyMainFragment extends Fragment {

    int count = 0;

    public SurveyMainFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_survey_main, container, false);
        Bundle bundle = new Bundle();
        bundle.putBoolean("weight", false);
        bundle.putBoolean("sleep", false);
        bundle.putBoolean("energy", false);
        bundle.putBoolean("immunity", false);
        bundle.putBoolean("skin", false);
        bundle.putBoolean("detox", false);
        bundle.putBoolean("exercise", false);
        bundle.putBoolean("digestion", false);
        bundle.putBoolean("articulation", false);

        Button toSurvey = (Button) v.findViewById(R.id.nextPage);
        toSurvey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (count == 0) {
                    Toast.makeText(getActivity(),"Please select at least 1 problem",Toast.LENGTH_SHORT).show();
                } else if (count == 1) {
                    Fragment single = new SurveySingleProblemFragment();
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    single.setArguments(bundle);
                    fragmentTransaction.replace(R.id.fragmentLayout, single);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                } else if (count == 2) {
                    Fragment doublee = new SurveyDoubleProblemFragment();
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    doublee.setArguments(bundle);
                    fragmentTransaction.replace(R.id.fragmentLayout, doublee);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                } else {
                    Toast.makeText(getActivity(),"Please only select 2 problems",Toast.LENGTH_SHORT).show();
                }
            }
        });

        RadioButton weight = (RadioButton) v.findViewById(R.id.weightButton);
        weight.setOnClickListener(new View.OnClickListener() {
            Boolean checked = weight.isChecked();
            @Override
            public void onClick(View view) {
                if (checked) {
                    count--;
                    weight.setChecked(false);
                    bundle.remove("weight");
                    bundle.putBoolean("weight", false);
                    checked = false;
                } else {
                    count++;
                    checked = true;
                    bundle.remove("weight");
                    bundle.putBoolean("weight", true);
                }
            }
        });

        RadioButton sleep = (RadioButton) v.findViewById(R.id.sleepButton);
        sleep.setOnClickListener(new View.OnClickListener() {
            Boolean checked = sleep.isChecked();
            @Override
            public void onClick(View view) {
                if (checked) {
                    count--;
                    sleep.setChecked(false);
                    bundle.remove("sleep");
                    bundle.putBoolean("sleep", false);
                    checked = false;
                } else {
                    count++;
                    checked = true;
                    bundle.remove("sleep");
                    bundle.putBoolean("sleep", true);
                }
            }
        });

        RadioButton energy = (RadioButton) v.findViewById(R.id.energyButton);
        energy.setOnClickListener(new View.OnClickListener() {
            Boolean checked = energy.isChecked();
            @Override
            public void onClick(View view) {
                if (checked) {
                    count--;
                    energy.setChecked(false);
                    bundle.remove("energy");
                    bundle.putBoolean("energy", false);
                    checked = false;
                } else {
                    count++;
                    checked = true;
                    bundle.remove("energy");
                    bundle.putBoolean("energy", true);
                }
            }
        });

        RadioButton immunity = (RadioButton) v.findViewById(R.id.immunityButton);
        immunity.setOnClickListener(new View.OnClickListener() {
            Boolean checked = immunity.isChecked();
            @Override
            public void onClick(View view) {
                if (checked) {
                    count--;
                    immunity.setChecked(false);
                    bundle.remove("immunity");
                    bundle.putBoolean("immunity", false);
                    checked = false;
                } else {
                    count++;
                    checked = true;
                    bundle.remove("immunity");
                    bundle.putBoolean("immunity", true);
                }
            }
        });

        RadioButton skin = (RadioButton) v.findViewById(R.id.skinButton);
        skin.setOnClickListener(new View.OnClickListener() {
            Boolean checked = skin.isChecked();
            @Override
            public void onClick(View view) {
                if (checked) {
                    count--;
                    skin.setChecked(false);
                    bundle.remove("skin");
                    bundle.putBoolean("skin", false);
                    checked = false;
                } else {
                    count++;
                    checked = true;
                    bundle.remove("skin");
                    bundle.putBoolean("skin", true);
                }
            }
        });

        RadioButton detox = (RadioButton) v.findViewById(R.id.detoxButton);
        detox.setOnClickListener(new View.OnClickListener() {
            Boolean checked = detox.isChecked();
            @Override
            public void onClick(View view) {
                if (checked) {
                    count--;
                    detox.setChecked(false);
                    bundle.remove("detox");
                    bundle.putBoolean("detox", false);
                    checked = false;
                } else {
                    count++;
                    checked = true;
                    bundle.remove("detox");
                    bundle.putBoolean("detox", true);
                }
            }
        });

        RadioButton exercise = (RadioButton) v.findViewById(R.id.exerciseButton);
        exercise.setOnClickListener(new View.OnClickListener() {
            Boolean checked = exercise.isChecked();
            @Override
            public void onClick(View view) {
                if (checked) {
                    count--;
                    exercise.setChecked(false);
                    bundle.remove("exercise");
                    bundle.putBoolean("exercise", false);
                    checked = false;
                } else {
                    count++;
                    checked = true;
                    bundle.remove("exercise");
                    bundle.putBoolean("exercise", true);
                }
            }
        });

        RadioButton digestion = (RadioButton) v.findViewById(R.id.digestionButton);
        digestion.setOnClickListener(new View.OnClickListener() {
            Boolean checked = digestion.isChecked();
            @Override
            public void onClick(View view) {
                if (checked) {
                    count--;
                    digestion.setChecked(false);
                    bundle.remove("digestion");
                    bundle.putBoolean("digestion", false);
                    checked = false;
                } else {
                    count++;
                    checked = true;
                    bundle.remove("digestion");
                    bundle.putBoolean("digestion", true);
                }
            }
        });

        RadioButton articulation = (RadioButton) v.findViewById(R.id.articulationButton);
        articulation.setOnClickListener(new View.OnClickListener() {
            Boolean checked = articulation.isChecked();
            @Override
            public void onClick(View view) {
                if (checked) {
                    count--;
                    articulation.setChecked(false);
                    bundle.remove("articulation");
                    bundle.putBoolean("articulation", false);
                    checked = false;
                } else {
                    count++;
                    checked = true;
                    bundle.remove("articulation");
                    bundle.putBoolean("articulation", true);
                }
            }
        });

        return v;
    }
}