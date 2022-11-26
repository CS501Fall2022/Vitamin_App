package com.example.vitamin_app;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SurveyDoubleProblemFragment extends Fragment {

    // creating a variable for our
    // Firebase Database.
    FirebaseDatabase firebaseDatabase;

    // creating a variable for our Database
    // Reference for Firebase.
    DatabaseReference databaseReference;

    // creating a variable for
    // our object class
    Users user;

    ArrayList<Fragment> array = new ArrayList<Fragment>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_survey_double_problem, container, false);

        firebaseDatabase = FirebaseDatabase.getInstance();

        // below line is used to get reference for our database.
        databaseReference = firebaseDatabase.getReference("Users");

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();

        String username = currentUser.getDisplayName();
        String email = currentUser.getEmail();

        user = new Users(email, username);
        user.setNum_problem(2);

        Bundle bundle = this.getArguments();
        int count = 0;
        TextView problem1 = (TextView) v.findViewById(R.id.text_problem1);
        TextView problem2 = (TextView) v.findViewById(R.id.text_problem2);
        SeekBar seek1 = (SeekBar) v.findViewById(R.id.seekBar);
        SeekBar seek2 = (SeekBar) v.findViewById(R.id.seekBar2);

        Fragment triple1 = null;
        Fragment double1 = null;
        Fragment triple2 = null;
        Fragment double2 = null;

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
            triple1 = new SurveyTripleCheckboxFragment();
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            triple1.setArguments(bundle);
            fragmentTransaction.replace(R.id.checkboxLayout1, triple1);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
            problem1.setText("weightloss");
            array.add(triple1);
            count++;
        } if (bundle.getBoolean("sleep")) {
            if (count == 0) {
                triple1 = new SurveyTripleCheckboxFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                triple1.setArguments(bundle);
                fragmentTransaction.replace(R.id.checkboxLayout1, triple1);
                problem1.setText("sleep");
                array.add(triple1);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            } else {
                triple2 = new SurveyTripleCheckboxFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                Bundle frag_bundle = new Bundle();
                frag_bundle.putBoolean("sleep", true);
                triple2.setArguments(frag_bundle);
                fragmentTransaction.replace(R.id.checkboxLayout2, triple2);
                problem2.setText("sleep");
                array.add(triple2);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
            count++;
        } if (bundle.getBoolean("energy")) {
            if (count == 0) {
                double1 = new SurveyDoubleCheckboxFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                double1.setArguments(bundle);
                fragmentTransaction.replace(R.id.checkboxLayout1, double1);
                problem1.setText("energy");
                array.add(double1);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            } else {
                double2 = new SurveyDoubleCheckboxFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                Bundle frag_bundle = new Bundle();
                frag_bundle.putBoolean("energy", true);
                double2.setArguments(frag_bundle);
                fragmentTransaction.replace(R.id.checkboxLayout2, double2);
                problem2.setText("energy");
                array.add(double2);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
            count++;
        } if (bundle.getBoolean("immunity")) {
            if (count == 0) {
                double1 = new SurveyDoubleCheckboxFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                double1.setArguments(bundle);
                fragmentTransaction.replace(R.id.checkboxLayout1, double1);
                problem1.setText("immunity");
                array.add(double1);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            } else {
                double2 = new SurveyDoubleCheckboxFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                Bundle frag_bundle = new Bundle();
                frag_bundle.putBoolean("immunity", true);
                double2.setArguments(frag_bundle);
                fragmentTransaction.replace(R.id.checkboxLayout2, double2);
                problem2.setText("immunity");
                array.add(double2);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
            count++;
        } if (bundle.getBoolean("skin")) {
            if (count == 0) {
                triple1 = new SurveyTripleCheckboxFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                triple1.setArguments(bundle);
                fragmentTransaction.replace(R.id.checkboxLayout1, triple1);
                problem1.setText("skin");
                array.add(triple1);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            } else {
                triple2 = new SurveyTripleCheckboxFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                Bundle frag_bundle = new Bundle();
                frag_bundle.putBoolean("skin", true);
                triple2.setArguments(frag_bundle);
                fragmentTransaction.replace(R.id.checkboxLayout2, triple2);
                problem2.setText("skin");
                array.add(triple2);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
            count++;
        } if (bundle.getBoolean("detox")) {
            if (count == 0) {
                triple1 = new SurveyTripleCheckboxFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                triple1.setArguments(bundle);
                fragmentTransaction.replace(R.id.checkboxLayout1, triple1);
                problem1.setText("detox");
                array.add(triple1);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            } else {
                triple2 = new SurveyTripleCheckboxFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                Bundle frag_bundle = new Bundle();
                frag_bundle.putBoolean("detox", true);
                triple2.setArguments(frag_bundle);
                fragmentTransaction.replace(R.id.checkboxLayout2, triple2);
                problem2.setText("detox");
                array.add(triple2);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
            count++;
        } if (bundle.getBoolean("exercise")) {
            if (count == 0) {
                double1 = new SurveyDoubleCheckboxFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                double1.setArguments(bundle);
                fragmentTransaction.replace(R.id.checkboxLayout1, double1);
                problem1.setText("exercise");
                array.add(double1);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            } else {
                double2 = new SurveyDoubleCheckboxFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                Bundle frag_bundle = new Bundle();
                frag_bundle.putBoolean("exercise", true);
                double2.setArguments(frag_bundle);
                fragmentTransaction.replace(R.id.checkboxLayout2, double2);
                problem2.setText("exercise");
                array.add(double2);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
            count++;
        } if (bundle.getBoolean("digestion")) {
            if (count == 0) {
                double1 = new SurveyDoubleCheckboxFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                double1.setArguments(bundle);
                fragmentTransaction.replace(R.id.checkboxLayout1, double1);
                problem1.setText("digestion");
                array.add(double1);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            } else {
                double2 = new SurveyDoubleCheckboxFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                Bundle frag_bundle = new Bundle();
                frag_bundle.putBoolean("digestion", true);
                double2.setArguments(frag_bundle);
                fragmentTransaction.replace(R.id.checkboxLayout2, double2);
                problem2.setText("digestion");
                array.add(double2);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
            count++;
        } if (bundle.getBoolean("articulation")) {
            if (count == 0) {
                double1 = new SurveyDoubleCheckboxFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                double1.setArguments(bundle);
                fragmentTransaction.replace(R.id.checkboxLayout1, double1);
                problem1.setText("articulation");
                array.add(double1);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            } else {
                double2 = new SurveyDoubleCheckboxFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                Bundle frag_bundle = new Bundle();
                frag_bundle.putBoolean("articulation", true);
                double2.setArguments(frag_bundle);
                fragmentTransaction.replace(R.id.checkboxLayout2, double2);
                problem2.setText("articulation");
                array.add(double2);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
            count++;
        }

        Button endSurvey = (Button) v.findViewById(R.id.endSurvey2);
        endSurvey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean check = true;
                int count = 0;
                if (bundle.getBoolean("weight")) {
                    user.setProblem("weight");
                    RadioButton triple_p1 = (RadioButton) array.get(count).getView().findViewById(R.id.triple_problem1);
                    RadioButton triple_p2 = (RadioButton) array.get(count).getView().findViewById(R.id.triple_problem2);
                    RadioButton triple_p3 = (RadioButton) array.get(count).getView().findViewById(R.id.triple_problem3);
                    if (triple_p1.isChecked()) {
                        if (seek1.getProgress() == 1) {
                            user.setSupplement1("Grape Pomace");
                        } else if (seek1.getProgress() == 2) {
                            user.setSupplement1("Bromelain");
                            user.setSupplement2("Grape Pomace");
                        } else {
                            user.setSupplement1("Bromelain");
                            user.setSupplement2("Grape Pomace");
                            user.setSupplement3("Garcinia");
                        }
                    } else if (triple_p2.isChecked()) {
                        if (seek1.getProgress() == 1) {
                            user.setSupplement1("Green Tea");
                        } else if (seek1.getProgress() == 2) {
                            user.setSupplement1("Green Tea");
                            user.setSupplement2("Artichoke");
                        } else {
                            user.setSupplement1("Green Tea");
                            user.setSupplement2("Artichoke");
                            user.setSupplement3("Milk Thistle");
                        }
                    } else if (triple_p3.isChecked()) {
                        if (seek1.getProgress() == 1) {
                            user.setSupplement1("Konjac");
                        } else if (seek1.getProgress() == 2) {
                            user.setSupplement1("Konjac");
                            user.setSupplement2("Laminaria");
                        } else {
                            user.setSupplement1("Konjac");
                            user.setSupplement2("Laminaria");
                            user.setSupplement3("Kudzu");
                        }
                    } else {
                        Toast.makeText(getActivity(),"Must select a problem from the available options",Toast.LENGTH_SHORT).show();
                        check = false;
                    }
                    count++;
                } if (bundle.getBoolean("sleep")) {
                    RadioButton triple_p1 = (RadioButton) array.get(count).getView().findViewById(R.id.triple_problem1);
                    RadioButton triple_p2 = (RadioButton) array.get(count).getView().findViewById(R.id.triple_problem2);
                    RadioButton triple_p3 = (RadioButton) array.get(count).getView().findViewById(R.id.triple_problem3);
                    if (count == 0) {
                        user.setProblem("sleep");
                        if (triple_p1.isChecked()) {
                            if (seek1.getProgress() == 1) {
                                user.setSupplement1("California Poppy");
                            } else if (seek1.getProgress() == 2) {
                                user.setSupplement1("California Poppy");
                                user.setSupplement2("Griffonia");
                            } else {
                                user.setSupplement1("California Poppy");
                                user.setSupplement2("Griffonia");
                                user.setSupplement3("L-Theanine");
                            }
                        } else if (triple_p2.isChecked()) {
                            if (seek1.getProgress() == 1) {
                                user.setSupplement1("Valerian");
                            } else if (seek1.getProgress() == 2) {
                                user.setSupplement1("Valerian");
                                user.setSupplement2("Melatonin");
                            } else {
                                user.setSupplement1("Valerian");
                                user.setSupplement2("Melatonin");
                                user.setSupplement3("L-Tryptophan");
                            }
                        } else if (triple_p3.isChecked()) {
                            if (seek1.getProgress() == 1) {
                                user.setSupplement1("Hawthorn");
                            } else if (seek1.getProgress() == 2) {
                                user.setSupplement1("Hawthorn");
                                user.setSupplement2("Ashwagandha");
                            } else {
                                user.setSupplement1("Hawthorn");
                                user.setSupplement2("Ashwagandha");
                                user.setSupplement3("Griffonia");
                            }
                        } else {
                            Toast.makeText(getActivity(),"Must select a problem from the available options",Toast.LENGTH_SHORT).show();
                            check = false;
                        }
                    } else {
                        user.setProblem2("sleep");
                        if (triple_p1.isChecked()) {
                            if (seek1.getProgress() == 1) {
                                user.setSupplement4("California Poppy");
                            } else if (seek1.getProgress() == 2) {
                                user.setSupplement4("California Poppy");
                                user.setSupplement3("Griffonia");
                            } else {
                                user.setSupplement4("California Poppy");
                                user.setSupplement3("Griffonia");
                                user.setSupplement2("L-Theanine");
                            }
                        } else if (triple_p2.isChecked()) {
                            if (seek1.getProgress() == 1) {
                                user.setSupplement4("Valerian");
                            } else if (seek1.getProgress() == 2) {
                                user.setSupplement4("Valerian");
                                user.setSupplement3("Melatonin");
                            } else {
                                user.setSupplement4("Valerian");
                                user.setSupplement3("Melatonin");
                                user.setSupplement2("L-Tryptophan");
                            }
                        } else if (triple_p3.isChecked()) {
                            if (seek1.getProgress() == 1) {
                                user.setSupplement4("Hawthorn");
                            } else if (seek1.getProgress() == 2) {
                                user.setSupplement4("Hawthorn");
                                user.setSupplement3("Ashwagandha");
                            } else {
                                user.setSupplement4("Hawthorn");
                                user.setSupplement3("Ashwagandha");
                                user.setSupplement2("Griffonia");
                            }
                        } else {
                            Toast.makeText(getActivity(),"Must select a problem from the available options",Toast.LENGTH_SHORT).show();
                            check = false;
                        }
                    }
                    count++;
                } if (bundle.getBoolean("energy")) {
                    RadioButton double_p1 = (RadioButton) array.get(count).getView().findViewById(R.id.double_problem1);
                    RadioButton double_p2 = (RadioButton) array.get(count).getView().findViewById(R.id.double_problem2);
                    if (count == 0) {
                        user.setProblem("energy");
                        if (double_p1.isChecked()) {
                            if (seek1.getProgress() == 1) {
                                user.setSupplement1("Guarana");
                            } else if (seek1.getProgress() == 2) {
                                user.setSupplement1("Guarana");
                                user.setSupplement2("Magnesium");
                            } else {
                                user.setSupplement1("Guarana");
                                user.setSupplement2("Magnesium");
                                user.setSupplement3("Coenzyme Q10");
                            }
                        } else if (double_p2.isChecked()) {
                            if (seek1.getProgress() == 1) {
                                user.setSupplement1("Rhodiola");
                            } else if (seek1.getProgress() == 2) {
                                user.setSupplement1("Rhodiola");
                                user.setSupplement2("B Vitamins");
                            } else {
                                user.setSupplement1("Rhodiola");
                                user.setSupplement2("B Vitamins");
                                user.setSupplement3("L-Tryptophan");
                            }
                        } else {
                            Toast.makeText(getActivity(),"Must select a problem from the available options",Toast.LENGTH_SHORT).show();
                            check = false;
                        }
                    } else {
                        user.setProblem2("energy");
                        if (double_p1.isChecked()) {
                            if (seek1.getProgress() == 1) {
                                user.setSupplement4("Guarana");
                            } else if (seek1.getProgress() == 2) {
                                user.setSupplement4("Guarana");
                                user.setSupplement3("Magnesium");
                            } else {
                                user.setSupplement4("Guarana");
                                user.setSupplement3("Magnesium");
                                user.setSupplement2("Coenzyme Q10");
                            }
                        } else if (double_p2.isChecked()) {
                            if (seek1.getProgress() == 1) {
                                user.setSupplement4("Rhodiola");
                            } else if (seek1.getProgress() == 2) {
                                user.setSupplement4("Rhodiola");
                                user.setSupplement3("B Vitamins");
                            } else {
                                user.setSupplement4("Rhodiola");
                                user.setSupplement3("B Vitamins");
                                user.setSupplement2("L-Tryptophan");
                            }
                        } else {
                            Toast.makeText(getActivity(),"Must select a problem from the available options",Toast.LENGTH_SHORT).show();
                            check = false;
                        }
                    }
                    count++;
                } if (bundle.getBoolean("immunity")) {
                    RadioButton double_p1 = (RadioButton) array.get(count).getView().findViewById(R.id.double_problem1);
                    RadioButton double_p2 = (RadioButton) array.get(count).getView().findViewById(R.id.double_problem2);
                    if (count == 0) {
                        user.setProblem("immunity");
                        if (double_p1.isChecked()) {
                            if (seek1.getProgress() == 1) {
                                user.setSupplement1("Zinc");
                            } else if (seek1.getProgress() == 2) {
                                user.setSupplement1("Zinc");
                                user.setSupplement2("Shitake");
                            } else {
                                user.setSupplement1("Zinc");
                                user.setSupplement2("Shitake");
                                user.setSupplement3("D Vitamins");
                            }
                        } else if (double_p2.isChecked()) {
                            if (seek1.getProgress() == 1) {
                                user.setSupplement1("Propolis");
                            } else if (seek1.getProgress() == 2) {
                                user.setSupplement1("Propolis");
                                user.setSupplement2("Nigella");
                            } else {
                                user.setSupplement1("Propolis");
                                user.setSupplement2("Nigella");
                                user.setSupplement3("Glutathione");
                            }
                        } else {
                            Toast.makeText(getActivity(),"Must select a problem from the available options",Toast.LENGTH_SHORT).show();
                            check = false;
                        }
                    } else {
                        user.setProblem2("immunity");
                        if (double_p1.isChecked()) {
                            if (seek1.getProgress() == 1) {
                                user.setSupplement4("Zinc");
                            } else if (seek1.getProgress() == 2) {
                                user.setSupplement4("Zinc");
                                user.setSupplement3("Shitake");
                            } else {
                                user.setSupplement4("Zinc");
                                user.setSupplement3("Shitake");
                                user.setSupplement2("D Vitamins");
                            }
                        } else if (double_p2.isChecked()) {
                            if (seek1.getProgress() == 1) {
                                user.setSupplement4("Propolis");
                            } else if (seek1.getProgress() == 2) {
                                user.setSupplement4("Propolis");
                                user.setSupplement3("Nigella");
                            } else {
                                user.setSupplement4("Propolis");
                                user.setSupplement2("Nigella");
                                user.setSupplement3("Glutathione");
                            }
                        } else {
                            Toast.makeText(getActivity(),"Must select a problem from the available options",Toast.LENGTH_SHORT).show();
                            check = false;
                        }
                    }
                    count++;
                } if (bundle.getBoolean("skin")) {
                    RadioButton triple_p1 = (RadioButton) array.get(count).getView().findViewById(R.id.triple_problem1);
                    RadioButton triple_p2 = (RadioButton) array.get(count).getView().findViewById(R.id.triple_problem2);
                    RadioButton triple_p3 = (RadioButton) array.get(count).getView().findViewById(R.id.triple_problem3);
                    if (count == 0) {
                        user.setProblem("skin");
                        if (triple_p1.isChecked()) {
                            if (seek1.getProgress() == 1) {
                                user.setSupplement1("Borage");
                            } else if (seek1.getProgress() == 2) {
                                user.setSupplement1("Borage");
                                user.setSupplement2("Wheat Germ Oil");
                            } else {
                                user.setSupplement1("Borage");
                                user.setSupplement2("Wheat Germ Oil");
                                user.setSupplement3("Silica");
                            }
                        } else if (triple_p2.isChecked()) {
                            if (seek1.getProgress() == 1) {
                                user.setSupplement1("Vegan Collagen");
                            } else if (seek1.getProgress() == 2) {
                                user.setSupplement1("Vegan Collagen");
                                user.setSupplement2("Grape Seeds");
                            } else {
                                user.setSupplement1("Vegan Collagen");
                                user.setSupplement2("Grape Seeds");
                                user.setSupplement3("Acerola");
                            }
                        } else if (triple_p3.isChecked()) {
                            if (seek1.getProgress() == 1) {
                                user.setSupplement1("Beer Yeast");
                            } else if (seek1.getProgress() == 2) {
                                user.setSupplement1("Beer Yeast");
                                user.setSupplement2("Zinc");
                            } else {
                                user.setSupplement1("Beer Yeast");
                                user.setSupplement2("Zinc");
                                user.setSupplement3("Burdock Root");
                            }
                        } else {
                            Toast.makeText(getActivity(),"Must select a problem from the available options",Toast.LENGTH_SHORT).show();
                            check = false;
                        }
                    } else {
                        user.setProblem2("skin");
                        if (triple_p1.isChecked()) {
                            if (seek1.getProgress() == 1) {
                                user.setSupplement4("Borage");
                            } else if (seek1.getProgress() == 2) {
                                user.setSupplement4("Borage");
                                user.setSupplement3("Wheat Germ Oil");
                            } else {
                                user.setSupplement4("Borage");
                                user.setSupplement2("Wheat Germ Oil");
                                user.setSupplement3("Silica");
                            }
                        } else if (triple_p2.isChecked()) {
                            if (seek1.getProgress() == 1) {
                                user.setSupplement4("Vegan Collagen");
                            } else if (seek1.getProgress() == 2) {
                                user.setSupplement4("Vegan Collagen");
                                user.setSupplement3("Grape Seeds");
                            } else {
                                user.setSupplement4("Vegan Collagen");
                                user.setSupplement2("Grape Seeds");
                                user.setSupplement3("Acerola");
                            }
                        } else if (triple_p3.isChecked()) {
                            if (seek1.getProgress() == 1) {
                                user.setSupplement4("Beer Yeast");
                            } else if (seek1.getProgress() == 2) {
                                user.setSupplement4("Beer Yeast");
                                user.setSupplement3("Zinc");
                            } else {
                                user.setSupplement4("Beer Yeast");
                                user.setSupplement2("Zinc");
                                user.setSupplement3("Burdock Root");
                            }
                        } else {
                            Toast.makeText(getActivity(),"Must select a problem from the available options",Toast.LENGTH_SHORT).show();
                            check = false;
                        }
                    }
                    count++;
                } if (bundle.getBoolean("detox")) {
                    RadioButton triple_p1 = (RadioButton) array.get(count).getView().findViewById(R.id.triple_problem1);
                    RadioButton triple_p2 = (RadioButton) array.get(count).getView().findViewById(R.id.triple_problem2);
                    RadioButton triple_p3 = (RadioButton) array.get(count).getView().findViewById(R.id.triple_problem3);
                    if (count == 0) {
                        user.setProblem("detox");
                        if (triple_p1.isChecked()) {
                            if (seek1.getProgress() == 1) {
                                user.setSupplement1("Chlorella");
                            } else if (seek1.getProgress() == 2) {
                                user.setSupplement1("Chlorella");
                                user.setSupplement2("Amalaki");
                            } else {
                                user.setSupplement1("Chlorella");
                                user.setSupplement2("Amalaki");
                                user.setSupplement3("Grapefruit Seeds");
                            }
                        } else if (triple_p2.isChecked()) {
                            if (seek1.getProgress() == 1) {
                                user.setSupplement1("Desmodium");
                            } else if (seek1.getProgress() == 2) {
                                user.setSupplement1("Desmodium");
                                user.setSupplement2("Chrysanthellum");
                            } else {
                                user.setSupplement1("Desmodium");
                                user.setSupplement2("Chrysanthellum");
                                user.setSupplement3("Milk Thistle");
                            }
                        } else if (triple_p3.isChecked()) {
                            if (seek1.getProgress() == 1) {
                                user.setSupplement1("Fennel");
                            } else if (seek1.getProgress() == 2) {
                                user.setSupplement1("Fennel");
                                user.setSupplement2("Rosmary");
                            } else {
                                user.setSupplement1("Fennel");
                                user.setSupplement2("Rosmary");
                                user.setSupplement3("Peppermint");
                            }
                        } else {
                            Toast.makeText(getActivity(),"Must select a problem from the available options",Toast.LENGTH_SHORT).show();
                            check = false;
                        }
                    } else {
                        user.setProblem2("detox");
                        if (triple_p1.isChecked()) {
                            if (seek1.getProgress() == 1) {
                                user.setSupplement4("Chlorella");
                            } else if (seek1.getProgress() == 2) {
                                user.setSupplement4("Chlorella");
                                user.setSupplement3("Amalaki");
                            } else {
                                user.setSupplement4("Chlorella");
                                user.setSupplement2("Amalaki");
                                user.setSupplement3("Grapefruit Seeds");
                            }
                        } else if (triple_p2.isChecked()) {
                            if (seek1.getProgress() == 1) {
                                user.setSupplement4("Desmodium");
                            } else if (seek1.getProgress() == 2) {
                                user.setSupplement4("Desmodium");
                                user.setSupplement3("Chrysanthellum");
                            } else {
                                user.setSupplement4("Desmodium");
                                user.setSupplement2("Chrysanthellum");
                                user.setSupplement3("Milk Thistle");
                            }
                        } else if (triple_p3.isChecked()) {
                            if (seek1.getProgress() == 1) {
                                user.setSupplement4("Fennel");
                            } else if (seek1.getProgress() == 2) {
                                user.setSupplement4("Fennel");
                                user.setSupplement3("Rosmary");
                            } else {
                                user.setSupplement4("Fennel");
                                user.setSupplement2("Rosmary");
                                user.setSupplement3("Peppermint");
                            }
                        } else {
                            Toast.makeText(getActivity(),"Must select a problem from the available options",Toast.LENGTH_SHORT).show();
                            check = false;
                        }
                    }
                    count++;
                } if (bundle.getBoolean("exercise")) {
                    RadioButton double_p1 = (RadioButton) array.get(count).getView().findViewById(R.id.double_problem1);
                    RadioButton double_p2 = (RadioButton) array.get(count).getView().findViewById(R.id.double_problem2);
                    if (count == 0) {
                        user.setProblem("exercise");
                        if (double_p1.isChecked()) {
                            if (seek1.getProgress() == 1) {
                                user.setSupplement1("L-Carnitine");
                            } else if (seek1.getProgress() == 2) {
                                user.setSupplement1("L-Carnitine");
                                user.setSupplement2("Creatine");
                            } else {
                                user.setSupplement1("L-Carnitine");
                                user.setSupplement2("Creatine");
                                user.setSupplement3("Warana");
                            }
                        } else if (double_p2.isChecked()) {
                            if (seek1.getProgress() == 1) {
                                user.setSupplement1("BCAA");
                            } else if (seek1.getProgress() == 2) {
                                user.setSupplement1("BCAA");
                                user.setSupplement2("Coenzyme Q10");
                            } else {
                                user.setSupplement1("BCAA");
                                user.setSupplement2("Coenzyme Q10");
                                user.setSupplement3("Glutamine");
                            }
                        } else {
                            Toast.makeText(getActivity(),"Must select a problem from the available options",Toast.LENGTH_SHORT).show();
                            check = false;
                        }
                    } else {
                        user.setProblem2("exercise");
                        if (double_p1.isChecked()) {
                            if (seek1.getProgress() == 1) {
                                user.setSupplement4("L-Carnitine");
                            } else if (seek1.getProgress() == 2) {
                                user.setSupplement4("L-Carnitine");
                                user.setSupplement3("Creatine");
                            } else {
                                user.setSupplement4("L-Carnitine");
                                user.setSupplement2("Creatine");
                                user.setSupplement3("Warana");
                            }
                        } else if (double_p2.isChecked()) {
                            if (seek1.getProgress() == 1) {
                                user.setSupplement4("BCAA");
                            } else if (seek1.getProgress() == 2) {
                                user.setSupplement4("BCAA");
                                user.setSupplement3("Coenzyme Q10");
                            } else {
                                user.setSupplement4("BCAA");
                                user.setSupplement2("Coenzyme Q10");
                                user.setSupplement3("Glutamine");
                            }
                        } else {
                            Toast.makeText(getActivity(),"Must select a problem from the available options",Toast.LENGTH_SHORT).show();
                            check = false;
                        }
                    }
                    count++;
                } if (bundle.getBoolean("digestion")) {
                    RadioButton double_p1 = (RadioButton) array.get(count).getView().findViewById(R.id.double_problem1);
                    RadioButton double_p2 = (RadioButton) array.get(count).getView().findViewById(R.id.double_problem2);
                    if (count == 0) {
                        user.setProblem("digestion");
                        if (double_p1.isChecked()) {
                            if (seek1.getProgress() == 1) {
                                user.setSupplement1("Fennel");
                            } else if (seek1.getProgress() == 2) {
                                user.setSupplement1("Fennel");
                                user.setSupplement2("Coriandre");
                            } else {
                                user.setSupplement1("Fennel");
                                user.setSupplement2("Coriandre");
                                user.setSupplement3("Propolis");
                            }
                        } else if (double_p2.isChecked()) {
                            if (seek1.getProgress() == 1) {
                                user.setSupplement1("Lithothamne");
                            } else if (seek1.getProgress() == 2) {
                                user.setSupplement1("Lithothamne");
                                user.setSupplement2("Bromelain");
                            } else {
                                user.setSupplement1("Lithothamne");
                                user.setSupplement2("Bromelain");
                                user.setSupplement3("Cardamom");
                            }
                        } else {
                            Toast.makeText(getActivity(),"Must select a problem from the available options",Toast.LENGTH_SHORT).show();
                            check = false;
                        }
                    } else {
                        user.setProblem2("digestion");
                        if (double_p1.isChecked()) {
                            if (seek1.getProgress() == 1) {
                                user.setSupplement4("Fennel");
                            } else if (seek1.getProgress() == 2) {
                                user.setSupplement4("Fennel");
                                user.setSupplement3("Coriandre");
                            } else {
                                user.setSupplement4("Fennel");
                                user.setSupplement2("Coriandre");
                                user.setSupplement3("Propolis");
                            }
                        } else if (double_p2.isChecked()) {
                            if (seek1.getProgress() == 1) {
                                user.setSupplement4("Lithothamne");
                            } else if (seek1.getProgress() == 2) {
                                user.setSupplement4("Lithothamne");
                                user.setSupplement3("Bromelain");
                            } else {
                                user.setSupplement4("Lithothamne");
                                user.setSupplement2("Bromelain");
                                user.setSupplement3("Cardamom");
                            }
                        } else {
                            Toast.makeText(getActivity(),"Must select a problem from the available options",Toast.LENGTH_SHORT).show();
                            check = false;
                        }
                    }
                    count++;
                } if (bundle.getBoolean("articulation")) {
                    RadioButton double_p1 = (RadioButton) array.get(count).getView().findViewById(R.id.double_problem1);
                    RadioButton double_p2 = (RadioButton) array.get(count).getView().findViewById(R.id.double_problem2);
                    if (count == 0) {
                        user.setProblem("articulation");
                        if (double_p1.isChecked()) {
                            if (seek1.getProgress() == 1) {
                                user.setSupplement1("Boswellia");
                            } else if (seek1.getProgress() == 2) {
                                user.setSupplement1("Boswellia");
                                user.setSupplement2("Collagen");
                            } else {
                                user.setSupplement1("Boswellia");
                                user.setSupplement2("Collagen");
                                user.setSupplement3("Fermented Papaya");
                            }
                        } else if (double_p2.isChecked()) {
                            if (seek1.getProgress() == 1) {
                                user.setSupplement1("Curcumin");
                            } else if (seek1.getProgress() == 2) {
                                user.setSupplement1("Curcumin");
                                user.setSupplement2("Palmitoylethanolamide");
                            } else {
                                user.setSupplement1("Curcumin");
                                user.setSupplement2("Palmitoylethanolamide");
                                user.setSupplement3("Black Currant");
                            }
                        } else {
                            Toast.makeText(getActivity(),"Must select a problem from the available options",Toast.LENGTH_SHORT).show();
                            check = false;
                        }
                    } else {
                        user.setProblem2("articulation");
                        if (double_p1.isChecked()) {
                            if (seek1.getProgress() == 1) {
                                user.setSupplement4("Boswellia");
                            } else if (seek1.getProgress() == 2) {
                                user.setSupplement4("Boswellia");
                                user.setSupplement3("Collagen");
                            } else {
                                user.setSupplement4("Boswellia");
                                user.setSupplement2("Collagen");
                                user.setSupplement3("Fermented Papaya");
                            }
                        } else if (double_p2.isChecked()) {
                            if (seek1.getProgress() == 1) {
                                user.setSupplement4("Curcumin");
                            } else if (seek1.getProgress() == 2) {
                                user.setSupplement4("Curcumin");
                                user.setSupplement3("Palmitoylethanolamide");
                            } else {
                                user.setSupplement1("Curcumin");
                                user.setSupplement2("Palmitoylethanolamide");
                                user.setSupplement3("Black Currant");
                            }
                        } else {
                            Toast.makeText(getActivity(),"Must select a problem from the available options",Toast.LENGTH_SHORT).show();
                            check = false;
                        }
                    }
                    count++;
                }

                databaseReference.child(username).setValue(user);

                if (check) {
                    Intent intent = new Intent(view.getContext(), ResultListActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                    view.getContext().startActivity(intent);
                }
            }
        });

        return v;
    }
}