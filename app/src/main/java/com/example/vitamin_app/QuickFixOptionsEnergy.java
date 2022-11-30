package com.example.vitamin_app;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class QuickFixOptionsEnergy extends Fragment {

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;

    public QuickFixOptionsEnergy() {
        // Required empty public constructor
    }
    public static QuickFixOptionsEnergy newInstance(String param1, String param2) {
        QuickFixOptionsEnergy fragment = new QuickFixOptionsEnergy();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_quick_fix_energy_recycler, container, false);
        RecyclerView rv = (RecyclerView) v.findViewById(R.id.rvVitamins);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        RecyclerView.Adapter rvAdapter = new recyclerAdapter(this.getContext());
        rv.setAdapter(rvAdapter);
        return v;
    }
}