package com.example.vitamin_app;
import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import com.example.vitamin_app.QuickFixOptions.QuickFixOptionsArticulation;
import com.example.vitamin_app.QuickFixOptions.QuickFixOptionsDetox;
import com.example.vitamin_app.QuickFixOptions.QuickFixOptionsDigestion;
import com.example.vitamin_app.QuickFixOptions.QuickFixOptionsEnergy;
import com.example.vitamin_app.QuickFixOptions.QuickFixOptionsExercise;
import com.example.vitamin_app.QuickFixOptions.QuickFixOptionsImmunity;
import com.example.vitamin_app.QuickFixOptions.QuickFixOptionsSkin;
import com.example.vitamin_app.QuickFixOptions.QuickFixOptionsSleep;
import com.example.vitamin_app.QuickFixOptions.QuickFixOptionsWeightloss;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class QuickFixList extends Fragment {
    AutoCompleteTextView autocomplete;
    ArrayAdapter adapterItems;

    public QuickFixList() {
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v;
        v = inflater.inflate(R.layout.fragment_quick_fix_list, container, false);
        String[] items = {"Weightloss","Energy","Sleep","Articulation", "Detox", "Digestion", "Immunity", "Skin", "Exercise"};
        autocomplete = v.findViewById(R.id.auto_complete_txt);
        adapterItems = new ArrayAdapter<String>(getContext(), R.layout.dropdown_list, items);
        autocomplete.setAdapter(adapterItems);
        autocomplete.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String item = adapterView.getItemAtPosition(i).toString();
                FragmentManager fragmentManager;
                switch (item) {
                    case "Sleep": {
                        fragmentManager = getActivity().getSupportFragmentManager();
                        FragmentTransaction transaction = fragmentManager.beginTransaction();
                        transaction.replace(R.id.rvVitaminlay, new QuickFixOptionsSleep());
                        transaction.commitAllowingStateLoss();
                        break;
                    }
                    case "Weightloss": {
                        fragmentManager = getActivity().getSupportFragmentManager();
                        FragmentTransaction transaction = fragmentManager.beginTransaction();
                        transaction.replace(R.id.rvVitaminlay, new QuickFixOptionsWeightloss());
                        transaction.commitAllowingStateLoss();
                        break;
                    }
                    case "Energy": {
                        fragmentManager = getActivity().getSupportFragmentManager();
                        FragmentTransaction transaction = fragmentManager.beginTransaction();
                        transaction.replace(R.id.rvVitaminlay, new QuickFixOptionsEnergy());
                        transaction.commitAllowingStateLoss();
                        break;
                    }
                    case "Articulation": {
                        fragmentManager = getActivity().getSupportFragmentManager();
                        FragmentTransaction transaction = fragmentManager.beginTransaction();
                        transaction.replace(R.id.rvVitaminlay, new QuickFixOptionsArticulation());
                        transaction.commitAllowingStateLoss();
                        break;
                    }
                    case "Immunity": {
                        fragmentManager = getActivity().getSupportFragmentManager();
                        FragmentTransaction transaction = fragmentManager.beginTransaction();
                        transaction.replace(R.id.rvVitaminlay, new QuickFixOptionsImmunity());
                        transaction.commitAllowingStateLoss();
                        break;
                    }
                    case "Skin": {
                        fragmentManager = getActivity().getSupportFragmentManager();
                        FragmentTransaction transaction = fragmentManager.beginTransaction();
                        transaction.replace(R.id.rvVitaminlay, new QuickFixOptionsSkin());
                        transaction.commitAllowingStateLoss();
                        break;
                    }
                    case "Exercise": {
                        fragmentManager = getActivity().getSupportFragmentManager();
                        FragmentTransaction transaction = fragmentManager.beginTransaction();
                        transaction.replace(R.id.rvVitaminlay, new QuickFixOptionsExercise());
                        transaction.commitAllowingStateLoss();
                        break;
                    }
                    case "Detox": {
                        fragmentManager = getActivity().getSupportFragmentManager();
                        FragmentTransaction transaction = fragmentManager.beginTransaction();
                        transaction.replace(R.id.rvVitaminlay, new QuickFixOptionsDetox());
                        transaction.commitAllowingStateLoss();
                        break;
                    }
                    case "Digestion": {
                        fragmentManager = getActivity().getSupportFragmentManager();
                        FragmentTransaction transaction = fragmentManager.beginTransaction();
                        transaction.replace(R.id.rvVitaminlay, new QuickFixOptionsDigestion());
                        transaction.commitAllowingStateLoss();
                        break;
                    }
                }
            }
        });
        return v;
    }


    @Nullable
    @Override
    public Context getContext() {
        return super.getContext();
    }
}