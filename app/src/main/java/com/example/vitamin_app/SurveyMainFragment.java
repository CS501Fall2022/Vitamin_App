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
        bundle.putBoolean("Weight", false);
        bundle.putBoolean("Sleep", false);
        bundle.putBoolean("Energy", false);
        bundle.putBoolean("Immunity", false);
        bundle.putBoolean("Skin", false);
        bundle.putBoolean("Detox", false);
        bundle.putBoolean("Exercise", false);
        bundle.putBoolean("Digestion", false);
        bundle.putBoolean("Articulation", false);

        Button toSurvey = (Button) v.findViewById(R.id.toSurveyMain);
        toSurvey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (count == 0) {
                    Toast.makeText(v.getContext(),"Please select at least 1 problem",Toast.LENGTH_SHORT).show();
                } else if (count == 1) {
                    Fragment single = new SurveySingleProblemFragment();
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    single.setArguments(bundle);
                    fragmentTransaction.replace(R.id.fragmentLayout1, single);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                } else if (count == 2) {
                    Fragment doublee = new SurveyDoubleProblemFragment();
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    doublee.setArguments(bundle);
                    fragmentTransaction.replace(R.id.fragmentLayout1, doublee);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                } else {
                    Toast.makeText(v.getContext(),"Please only select 2 problems",Toast.LENGTH_SHORT).show();
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
                    bundle.remove("Weight");
                    bundle.putBoolean("Weight", false);
                    checked = false;
                } else {
                    count++;
                    checked = true;
                    bundle.remove("Weight");
                    bundle.putBoolean("Weight", true);
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
                    bundle.remove("Sleep");
                    bundle.putBoolean("Sleep", false);
                    checked = false;
                } else {
                    count++;
                    checked = true;
                    bundle.remove("Sleep");
                    bundle.putBoolean("Sleep", true);
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
                    bundle.remove("Energy");
                    bundle.putBoolean("Energy", false);
                    checked = false;
                } else {
                    count++;
                    checked = true;
                    bundle.remove("Energy");
                    bundle.putBoolean("Energy", true);
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
                    bundle.remove("Immunity");
                    bundle.putBoolean("Immunity", false);
                    checked = false;
                } else {
                    count++;
                    checked = true;
                    bundle.remove("Immunity");
                    bundle.putBoolean("Immunity", true);
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
                    bundle.remove("Skin");
                    bundle.putBoolean("Skin", false);
                    checked = false;
                } else {
                    count++;
                    checked = true;
                    bundle.remove("Skin");
                    bundle.putBoolean("Skin", true);
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
                    bundle.remove("Detox");
                    bundle.putBoolean("Detox", false);
                    checked = false;
                } else {
                    count++;
                    checked = true;
                    bundle.remove("Detox");
                    bundle.putBoolean("Detox", true);
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
                    bundle.remove("Exercise");
                    bundle.putBoolean("Exercise", false);
                    checked = false;
                } else {
                    count++;
                    checked = true;
                    bundle.remove("Exercise");
                    bundle.putBoolean("Exercise", true);
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
                    bundle.remove("Digestion");
                    bundle.putBoolean("Digestion", false);
                    checked = false;
                } else {
                    count++;
                    checked = true;
                    bundle.remove("Digestion");
                    bundle.putBoolean("Digestion", true);
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
                    bundle.remove("Articulation");
                    bundle.putBoolean("Articulation", false);
                    checked = false;
                } else {
                    count++;
                    checked = true;
                    bundle.remove("Articulation");
                    bundle.putBoolean("Articulation", true);
                }
            }
        });

        return v;
    }
}