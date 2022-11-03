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

public class SurveyMainFragment extends Fragment {

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
        bundle.putBoolean("hair", false);
        bundle.putBoolean("exercise", false);
        bundle.putBoolean("tanning", false);
        bundle.putBoolean("aging", false);

        Button toSurvey = (Button) v.findViewById(R.id.nextPage);
        toSurvey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (bundle.getBoolean("weight")) {
                    Fragment weight = new SurveyWeightFragment();
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    weight.setArguments(bundle);
                    fragmentTransaction.replace(R.id.fragmentLayout, weight);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                } else if (bundle.getBoolean("sleep")) {
                    Fragment sleep = new SurveySleepFragment();
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    sleep.setArguments(bundle);
                    fragmentTransaction.replace(R.id.fragmentLayout, sleep);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                } else if (bundle.getBoolean("energy")) {
                    Fragment energy = new SurveyEnergyFragment();
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    energy.setArguments(bundle);
                    fragmentTransaction.replace(R.id.fragmentLayout, energy);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                } else if (bundle.getBoolean("immunity")) {
                    Fragment immunity = new SurveyImmunityFragment();
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    immunity.setArguments(bundle);
                    fragmentTransaction.replace(R.id.fragmentLayout, immunity);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                } else if (bundle.getBoolean("skin")) {
                    Fragment skin = new SurveySkinFragment();
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    skin.setArguments(bundle);
                    fragmentTransaction.replace(R.id.fragmentLayout, skin);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                } else if (bundle.getBoolean("hair")) {
                    Fragment hair = new SurveyHairFragment();
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    hair.setArguments(bundle);
                    fragmentTransaction.replace(R.id.fragmentLayout, hair);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                } else if (bundle.getBoolean("exercise")) {
                    Fragment exercise = new SurveyExerciseFragment();
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    exercise.setArguments(bundle);
                    fragmentTransaction.replace(R.id.fragmentLayout, exercise);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                } else if (bundle.getBoolean("tanning")) {
                    Fragment tanning = new SurveyTanningFragment();
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    tanning.setArguments(bundle);
                    fragmentTransaction.replace(R.id.fragmentLayout, tanning);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                } else if (bundle.getBoolean("aging")) {
                    Fragment aging = new SurveyAgingFragment();
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    aging.setArguments(bundle);
                    fragmentTransaction.replace(R.id.fragmentLayout, aging);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                } else {
                    //make toast
                }
            }
        });

        RadioButton weight = (RadioButton) v.findViewById(R.id.weightButton);
        weight.setOnClickListener(new View.OnClickListener() {
            Boolean checked = weight.isChecked();
            @Override
            public void onClick(View view) {
                if (checked) {
                    weight.setChecked(false);
                    bundle.remove("weight");
                    bundle.putBoolean("weight", false);
                    checked = false;
                } else {
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
                    sleep.setChecked(false);
                    bundle.remove("sleep");
                    bundle.putBoolean("sleep", false);
                    checked = false;
                } else {
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
                    energy.setChecked(false);
                    bundle.remove("energy");
                    bundle.putBoolean("energy", false);
                    checked = false;
                } else {
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
                    immunity.setChecked(false);
                    bundle.remove("immunity");
                    bundle.putBoolean("immunity", false);
                    checked = false;
                } else {
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
                    skin.setChecked(false);
                    bundle.remove("skin");
                    bundle.putBoolean("skin", false);
                    checked = false;
                } else {
                    checked = true;
                    bundle.remove("skin");
                    bundle.putBoolean("skin", true);
                }
            }
        });

        RadioButton hair = (RadioButton) v.findViewById(R.id.hairButton);
        hair.setOnClickListener(new View.OnClickListener() {
            Boolean checked = hair.isChecked();
            @Override
            public void onClick(View view) {
                if (checked) {
                    hair.setChecked(false);
                    bundle.remove("hair");
                    bundle.putBoolean("hair", false);
                    checked = false;
                } else {
                    checked = true;
                    bundle.remove("hair");
                    bundle.putBoolean("hair", true);
                }
            }
        });

        RadioButton exercise = (RadioButton) v.findViewById(R.id.exerciseButton);
        exercise.setOnClickListener(new View.OnClickListener() {
            Boolean checked = exercise.isChecked();
            @Override
            public void onClick(View view) {
                if (checked) {
                    exercise.setChecked(false);
                    bundle.remove("exercise");
                    bundle.putBoolean("exercise", false);
                    checked = false;
                } else {
                    checked = true;
                    bundle.remove("exercise");
                    bundle.putBoolean("exercise", true);
                }
            }
        });

        RadioButton tanning = (RadioButton) v.findViewById(R.id.tanningButton);
        tanning.setOnClickListener(new View.OnClickListener() {
            Boolean checked = tanning.isChecked();
            @Override
            public void onClick(View view) {
                if (checked) {
                    tanning.setChecked(false);
                    bundle.remove("tanning");
                    bundle.putBoolean("tanning", false);
                    checked = false;
                } else {
                    checked = true;
                    bundle.remove("tanning");
                    bundle.putBoolean("tanning", true);
                }
            }
        });

        RadioButton aging = (RadioButton) v.findViewById(R.id.agingButton);
        aging.setOnClickListener(new View.OnClickListener() {
            Boolean checked = aging.isChecked();
            @Override
            public void onClick(View view) {
                if (checked) {
                    aging.setChecked(false);
                    bundle.remove("aging");
                    bundle.putBoolean("aging", false);
                    checked = false;
                } else {
                    checked = true;
                    bundle.remove("aging");
                    bundle.putBoolean("aging", true);
                }
            }
        });

        return v;
    }
}