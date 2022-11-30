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
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SurveySingleProblemFragment extends Fragment {

    // creating a variable for our
    // Firebase Database.
    FirebaseDatabase firebaseDatabase;

    // creating a variable for our Database
    // Reference for Firebase.
    DatabaseReference databaseReference;

    // creating a variable for
    // our object class
    Users user;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_survey_single_problem, container, false);

        Bundle bundle = this.getArguments();
        TextView header = (TextView) v.findViewById(R.id.problem_header);

        firebaseDatabase = FirebaseDatabase.getInstance();

        // below line is used to get reference for our database.
        databaseReference = firebaseDatabase.getReference("Users");

        // Retrieve username from SQL database to use for firebase

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();

        String username = currentUser.getDisplayName();
        String email = currentUser.getEmail();

        user = new Users(email, username);
        user.setNum_problem(1);

        Fragment triple = null;
        Fragment doublle = null;

        if (bundle.getBoolean("weight")) {
            header.setText("Weight Loss");
            triple = new SurveyTripleCheckboxFragment();
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            triple.setArguments(bundle);
            fragmentTransaction.replace(R.id.checkboxLayout, triple);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        } else if (bundle.getBoolean("sleep")) {
            header.setText("Sleep");
            triple = new SurveyTripleCheckboxFragment();
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            triple.setArguments(bundle);
            fragmentTransaction.replace(R.id.checkboxLayout, triple);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        } else if (bundle.getBoolean("energy")) {
            header.setText("Energy");
            doublle = new SurveyDoubleCheckboxFragment();
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            doublle.setArguments(bundle);
            fragmentTransaction.replace(R.id.checkboxLayout, doublle);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        } else if (bundle.getBoolean("immunity")) {
            header.setText("Immunity");
            doublle = new SurveyDoubleCheckboxFragment();
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            doublle.setArguments(bundle);
            fragmentTransaction.replace(R.id.checkboxLayout, doublle);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        } else if (bundle.getBoolean("skin")) {
            header.setText("Skin");
            triple = new SurveyTripleCheckboxFragment();
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            triple.setArguments(bundle);
            fragmentTransaction.replace(R.id.checkboxLayout, triple);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        } else if (bundle.getBoolean("detox")) {
            header.setText("Detox");
            triple = new SurveyTripleCheckboxFragment();
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            triple.setArguments(bundle);
            fragmentTransaction.replace(R.id.checkboxLayout, triple);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        } else if (bundle.getBoolean("exercise")) {
            header.setText("Exercise");
            doublle = new SurveyDoubleCheckboxFragment();
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            doublle.setArguments(bundle);
            fragmentTransaction.replace(R.id.checkboxLayout, doublle);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        } else if (bundle.getBoolean("digestion")) {
            header.setText("Digestion");
            doublle = new SurveyDoubleCheckboxFragment();
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            doublle.setArguments(bundle);
            fragmentTransaction.replace(R.id.checkboxLayout, doublle);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        } else if (bundle.getBoolean("articulation")) {
            header.setText("Articulation");
            doublle = new SurveyDoubleCheckboxFragment();
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            doublle.setArguments(bundle);
            fragmentTransaction.replace(R.id.checkboxLayout, doublle);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }

        Button endSurvey = (Button) v.findViewById(R.id.endSurvey);
        Fragment finalTriple = triple;
        Fragment finalDouble = doublle;
        endSurvey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean check = true;
                if (bundle.getBoolean("weight")) {
                    user.setProblem("weight");
                    RadioButton triple_p1 = (RadioButton) finalTriple.getView().findViewById(R.id.triple_problem1);
                    RadioButton triple_p2 = (RadioButton) finalTriple.getView().findViewById(R.id.triple_problem2);
                    RadioButton triple_p3 = (RadioButton) finalTriple.getView().findViewById(R.id.triple_problem3);
                    if (triple_p1.isChecked()) {
                        user.setSupplement1("Bromelain");
                        user.setSupplement2("Garcinia");
                        user.setSupplement3("Grape Pomace");
                        user.setSupplement4("Centella Asiatica");
                    } else if (triple_p2.isChecked()) {
                        user.setSupplement1("Birch Sap");
                        user.setSupplement2("Green Tea");
                        user.setSupplement3("Artichoke");
                        user.setSupplement4("Milk Thistle");
                    } else if (triple_p3.isChecked()) {
                        user.setSupplement1("Konjac");
                        user.setSupplement2("Kudzu");
                        user.setSupplement3("Laminaria");
                        user.setSupplement4("Linseed Oil");
                    } else {
                        Toast.makeText(v.getContext(),"Must select a problem from the available options",Toast.LENGTH_SHORT).show();
                        check = false;
                    }
                } else if (bundle.getBoolean("sleep")) {
                    user.setProblem("sleep");
                    RadioButton triple_p1 = (RadioButton) finalTriple.getView().findViewById(R.id.triple_problem1);
                    RadioButton triple_p2 = (RadioButton) finalTriple.getView().findViewById(R.id.triple_problem2);
                    RadioButton triple_p3 = (RadioButton) finalTriple.getView().findViewById(R.id.triple_problem3);
                    if (triple_p1.isChecked()) {
                        user.setSupplement1("Passiflora");
                        user.setSupplement2("California Poppy");
                        user.setSupplement3("L-Theanine");
                        user.setSupplement4("Griffonia");
                    } else if (triple_p2.isChecked()) {
                        user.setSupplement1("Valerian");
                        user.setSupplement2("Melatonin");
                        user.setSupplement3("L-Tryptophan");
                        user.setSupplement4("Safran");
                    } else if (triple_p3.isChecked()) {
                        user.setSupplement1("L-Theanine");
                        user.setSupplement2("Griffonia");
                        user.setSupplement3("Ashwagandha");
                        user.setSupplement4("Hawthorn");
                    } else {
                        Toast.makeText(v.getContext(),"Must select a problem from the available options",Toast.LENGTH_SHORT).show();
                        check = false;
                    }
                } else if (bundle.getBoolean("energy")) {
                    user.setProblem("energy");
                    RadioButton double_p1 = (RadioButton) finalDouble.getView().findViewById(R.id.double_problem1);
                    RadioButton double_p2 = (RadioButton) finalDouble.getView().findViewById(R.id.double_problem2);
                    if (double_p1.isChecked()) {
                        user.setSupplement1("Magnesium");
                        user.setSupplement2("Ginseng");
                        user.setSupplement3("Guarana");
                        user.setSupplement4("Coenzyme Q10");
                    } else if (double_p2.isChecked()) {
                        user.setSupplement1("B Vitamins");
                        user.setSupplement2("L-Tryptophan");
                        user.setSupplement3("Rhodiola");
                        user.setSupplement4("Klamath");
                    } else {
                        Toast.makeText(v.getContext(),"Must select a problem from the available options",Toast.LENGTH_SHORT).show();
                        check = false;
                    }
                } else if (bundle.getBoolean("immunity")) {
                    user.setProblem("immunity");
                    RadioButton double_p1 = (RadioButton) finalDouble.getView().findViewById(R.id.double_problem1);
                    RadioButton double_p2 = (RadioButton) finalDouble.getView().findViewById(R.id.double_problem2);
                    if (double_p1.isChecked()) {
                        user.setSupplement1("D Vitamins");
                        user.setSupplement2("Zinc");
                        user.setSupplement3("Royal Jelly");
                        user.setSupplement4("Shiitake");
                    } else if (double_p2.isChecked()) {
                        user.setSupplement1("Glutathione");
                        user.setSupplement2("Nigella");
                        user.setSupplement3("D Vitamins");
                        user.setSupplement4("Propolis");
                    } else {
                        Toast.makeText(v.getContext(),"Must select a problem from the available options",Toast.LENGTH_SHORT).show();
                        check = false;
                    }
                } else if (bundle.getBoolean("skin")) {
                    user.setProblem("skin");
                    RadioButton triple_p1 = (RadioButton) finalTriple.getView().findViewById(R.id.triple_problem1);
                    RadioButton triple_p2 = (RadioButton) finalTriple.getView().findViewById(R.id.triple_problem2);
                    RadioButton triple_p3 = (RadioButton) finalTriple.getView().findViewById(R.id.triple_problem3);
                    if (triple_p1.isChecked()) {
                        user.setSupplement1("Borage");
                        user.setSupplement2("Nigella oil");
                        user.setSupplement3("Silica");
                        user.setSupplement4("Wheat Germ Oil");
                    } else if (triple_p2.isChecked()) {
                        user.setSupplement1("Vegan Collagen");
                        user.setSupplement2("Silica");
                        user.setSupplement3("Acerola");
                        user.setSupplement4("Grape Seeds");
                    } else if (triple_p3.isChecked()) {
                        user.setSupplement1("Wild Pansy");
                        user.setSupplement2("Burdock Root");
                        user.setSupplement3("Beer Yeast");
                        user.setSupplement4("Zinc");
                    } else {
                        Toast.makeText(v.getContext(),"Must select a problem from the available options",Toast.LENGTH_SHORT).show();
                        check = false;
                    }
                } else if (bundle.getBoolean("detox")) {
                    user.setProblem("detox");
                    RadioButton triple_p1 = (RadioButton) finalTriple.getView().findViewById(R.id.triple_problem1);
                    RadioButton triple_p2 = (RadioButton) finalTriple.getView().findViewById(R.id.triple_problem2);
                    RadioButton triple_p3 = (RadioButton) finalTriple.getView().findViewById(R.id.triple_problem3);
                    if (triple_p1.isChecked()) {
                        user.setSupplement1("Amalaki");
                        user.setSupplement2("Chlorella");
                        user.setSupplement3("Grapefruit Seeds");
                        user.setSupplement4("Linden Sapwood");
                    } else if (triple_p2.isChecked()) {
                        user.setSupplement1("Rosmary");
                        user.setSupplement2("Desmodium");
                        user.setSupplement3("Chrysanthellum");
                        user.setSupplement4("Milk Thistle");
                    } else if (triple_p3.isChecked()) {
                        user.setSupplement1("Rosmary");
                        user.setSupplement2("Fennel");
                        user.setSupplement3("Peppermint");
                        user.setSupplement4("Anise");
                    } else {
                        Toast.makeText(v.getContext(),"Must select a problem from the available options",Toast.LENGTH_SHORT).show();
                        check = false;
                    }
                } else if (bundle.getBoolean("exercise")) {
                    user.setProblem("exercise");
                    RadioButton double_p1 = (RadioButton) finalDouble.getView().findViewById(R.id.double_problem1);
                    RadioButton double_p2 = (RadioButton) finalDouble.getView().findViewById(R.id.double_problem2);
                    if (double_p1.isChecked()) {
                        user.setSupplement1("Spirulina");
                        user.setSupplement2("Creatine");
                        user.setSupplement3("L-Carnitine");
                        user.setSupplement4("Warana");
                    } else if (double_p2.isChecked()) {
                        user.setSupplement1("Collagen");
                        user.setSupplement2("Coenzyme Q10");
                        user.setSupplement3("BCAA");
                        user.setSupplement4("Glutamine");
                    } else {
                        Toast.makeText(v.getContext(),"Must select a problem from the available options",Toast.LENGTH_SHORT).show();
                        check = false;
                    }
                } else if (bundle.getBoolean("digestion")) {
                    user.setProblem("digestion");
                    RadioButton double_p1 = (RadioButton) finalDouble.getView().findViewById(R.id.double_problem1);
                    RadioButton double_p2 = (RadioButton) finalDouble.getView().findViewById(R.id.double_problem2);
                    if (double_p1.isChecked()) {
                        user.setSupplement1("Fennel");
                        user.setSupplement2("Curcuma");
                        user.setSupplement3("Coriandre");
                        user.setSupplement4("Propolis");
                    } else if (double_p2.isChecked()) {
                        user.setSupplement1("Bromelain");
                        user.setSupplement2("Cardamom");
                        user.setSupplement3("Lithothamne");
                        user.setSupplement4("Licorice");
                    } else {
                        Toast.makeText(v.getContext(),"Must select a problem from the available options",Toast.LENGTH_SHORT).show();
                        check = false;
                    }
                } else if (bundle.getBoolean("articulation")) {
                    user.setProblem("articulation");
                    RadioButton double_p1 = (RadioButton) finalDouble.getView().findViewById(R.id.double_problem1);
                    RadioButton double_p2 = (RadioButton) finalDouble.getView().findViewById(R.id.double_problem2);
                    if (double_p1.isChecked()) {
                        user.setSupplement1("Collagen");
                        user.setSupplement2("Boswellia");
                        user.setSupplement3("Fermented Papaya");
                        user.setSupplement4("Silica");
                    } else if (double_p2.isChecked()) {
                        user.setSupplement1("Meadowsweet");
                        user.setSupplement2("Curcumin");
                        user.setSupplement3("Palmitoylethanolamide");
                        user.setSupplement4("Black Currant");
                    } else {
                        Toast.makeText(v.getContext(),"Must select a problem from the available options",Toast.LENGTH_SHORT).show();
                        check = false;
                    }
                }

                if (check) {
                    databaseReference.child(username).setValue(user);

                    Intent intent = new Intent(view.getContext(), ResultListActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                    view.getContext().startActivity(intent);
                }
            }
        });

        return v;
    }
}