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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class SurveyDoubleProblemFragment extends Fragment {

    // creating a variable for our
    // Firebase Database.
    FirebaseDatabase firebaseDatabase;

    // creating a variable for our Database
    // Reference for Firebase.
    DatabaseReference databaseReference;

    // Database for tasks on profile page
    ToDoDatabaseHandler db;

    // creating a variable for
    // our object class
    Users user;

    // to store data from user's database
    String age;
    String gender;

    TextView problem1;
    TextView problem2;

    int count = 0;

    Fragment triple1 = null;
    Fragment double1 = null;
    Fragment triple2 = null;
    Fragment double2 = null;
    Fragment single1 = null;
    Fragment single2 = null;

    String vit1;
    String vit2;
    String vit3;

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

        // Opening database for tasks
        db = new ToDoDatabaseHandler(getContext());
        db.openDatabase();

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();

        String username = currentUser.getDisplayName();
        String email = currentUser.getEmail();

        Bundle bundle = this.getArguments();
        problem1 = (TextView) v.findViewById(R.id.text_problem1);
        problem2 = (TextView) v.findViewById(R.id.text_problem2);
        SeekBar seek1 = (SeekBar) v.findViewById(R.id.seekBar);
        SeekBar seek2 = (SeekBar) v.findViewById(R.id.seekBar2);


        // Retrieving user data from firebase
        databaseReference.child(username).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()) {
                    if(task.getResult().exists()) {
                        DataSnapshot dataSnapshot = task.getResult();
                        age = String.valueOf(dataSnapshot.child("age").getValue());
                        gender = String.valueOf(dataSnapshot.child("gender").getValue());
                        user = new Users(email, username, gender, age);
                        user.setNum_problem(2);
                        checkboxFragment(bundle);
                    } else {
                        Toast.makeText(v.getContext(), "User does not exist",Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(v.getContext(), "Failed to read data",Toast.LENGTH_LONG).show();
                }
            }
        });

        //Toast.makeText(v.getContext(), " "+ age,Toast.LENGTH_LONG).show();

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


        Button endSurvey = (Button) v.findViewById(R.id.endSurvey2);
        endSurvey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean check = true;
                db.deleteProblemTasks();
                int count = 0;
                if (bundle.getBoolean(Problem.WEIGHT)) {
                    user.setProblem(Problem.WEIGHT);
                    db.insertProblemTask(Problem.WEIGHT);
                    RadioButton triple_p1 = (RadioButton) array.get(count).getView().findViewById(R.id.triple_problem1);
                    RadioButton triple_p2 = (RadioButton) array.get(count).getView().findViewById(R.id.triple_problem2);
                    RadioButton triple_p3 = (RadioButton) array.get(count).getView().findViewById(R.id.triple_problem3);
                    if (triple_p1.isChecked()) {
                        vit3 = "Garcinia";
                        if (gender.equals("male")) {
                            vit1 = "Bromelain";
                            vit2 = "Grape Pomace";
                        } else {
                            vit2 = "Bromelain";
                            vit1 = "Grape Pomace";
                        }

                        if (seek1.getProgress() == 1) {
                            user.setSupplement1(vit1);
                        } else if (seek1.getProgress() == 2) {
                            user.setSupplement1(vit1);
                            user.setSupplement2(vit2);
                        } else {
                            user.setSupplement1(vit1);
                            user.setSupplement2(vit2);
                            user.setSupplement3(vit3);
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
                        if (gender.equals("male")) {
                            vit1 = "Kudzu";
                            if (age.equals("12-20")) {
                                vit2 = "Nopal";
                            } else {
                                vit2 = "Konjac";
                            }
                            vit3 = "Laminaria";
                        } else {
                            if (age.equals("12-20")) {
                                vit1 = "Nopal";
                            } else {
                                vit1 = "Konjac";
                            }
                            vit2 = "Laminaria";
                            vit3 = "Kudzu";
                        }

                        if (seek1.getProgress() == 1) {
                            user.setSupplement1(vit1);
                        } else if (seek1.getProgress() == 2) {
                            user.setSupplement1(vit1);
                            user.setSupplement2(vit2);
                        } else {
                            user.setSupplement1(vit1);
                            user.setSupplement2(vit2);
                            user.setSupplement3(vit3);
                        }
                    } else {
                        Toast.makeText(v.getContext(),"Must select a problem from the available options",Toast.LENGTH_SHORT).show();
                        check = false;
                    }
                    count++;
                } if (bundle.getBoolean(Problem.SLEEP)) {
                    RadioButton triple_p1 = (RadioButton) array.get(count).getView().findViewById(R.id.triple_problem1);
                    RadioButton triple_p2 = (RadioButton) array.get(count).getView().findViewById(R.id.triple_problem2);
                    RadioButton triple_p3 = (RadioButton) array.get(count).getView().findViewById(R.id.triple_problem3);
                    if (triple_p1.isChecked()) {
                        vit3 = "L-Theanine";
                        if (age.equals("20-60")) {
                            vit1 = "California Poppy";
                            vit2 = "Griffonia";
                        } else if (age.equals("12-20")) {
                            vit1 = "California Poppy";
                            vit2 = "Melissa";
                        } else {
                            vit2 = "California Poppy";
                            vit1 = "Griffonia";
                        }
                    }
                    if (count == 0) {
                        user.setProblem(Problem.SLEEP);
                        db.insertProblemTask(Problem.SLEEP);
                        if (triple_p1.isChecked()) {
                            if (seek1.getProgress() == 1) {
                                user.setSupplement1(vit1);
                            } else if (seek1.getProgress() == 2) {
                                user.setSupplement1(vit1);
                                user.setSupplement2(vit2);
                            } else {
                                user.setSupplement1(vit1);
                                user.setSupplement2(vit2);
                                user.setSupplement3(vit3);
                            }
                        } else if (triple_p2.isChecked()) {
                            ///////////////////////////////////////////////////////////////////////////
                            // Need to edit this
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
                            Toast.makeText(v.getContext(),"Must select a problem from the available options",Toast.LENGTH_SHORT).show();
                            check = false;
                        }
                    } else {
                        user.setProblem2(Problem.SLEEP);
                        db.insertProblemTask(Problem.SLEEP);
                        if (triple_p1.isChecked()) {
                            if (seek2.getProgress() == 1) {
                                user.setSupplement4(vit1);
                            } else if (seek2.getProgress() == 2) {
                                user.setSupplement4(vit1);
                                user.setSupplement3(vit2);
                            } else {
                                user.setSupplement4(vit1);
                                user.setSupplement3(vit2);
                                user.setSupplement2(vit3);
                            }
                        } else if (triple_p2.isChecked()) {
                            ///////////////////////////////////////////////////////////////////////////
                            // Need to edit this
                            if (seek2.getProgress() == 1) {
                                user.setSupplement4("Valerian");
                            } else if (seek2.getProgress() == 2) {
                                user.setSupplement4("Valerian");
                                user.setSupplement3("Melatonin");
                            } else {
                                user.setSupplement4("Valerian");
                                user.setSupplement3("Melatonin");
                                user.setSupplement2("L-Tryptophan");
                            }
                        } else if (triple_p3.isChecked()) {
                            if (seek2.getProgress() == 1) {
                                user.setSupplement4("Hawthorn");
                            } else if (seek2.getProgress() == 2) {
                                user.setSupplement4("Hawthorn");
                                user.setSupplement3("Ashwagandha");
                            } else {
                                user.setSupplement4("Hawthorn");
                                user.setSupplement3("Ashwagandha");
                                user.setSupplement2("Griffonia");
                            }
                        } else {
                            Toast.makeText(v.getContext(),"Must select a problem from the available options",Toast.LENGTH_SHORT).show();
                            check = false;
                        }
                    }
                    count++;
                } if (bundle.getBoolean(Problem.ENERGY)) {
                    RadioButton double_p1 = (RadioButton) array.get(count).getView().findViewById(R.id.double_problem1);
                    if (double_p1.isChecked()) {
                        if (age.equals("60+")) {
                            vit1 = "Klamath";
                            vit2 = "Rhodiola";
                            vit3 = "B Vitamins";
                        } else if (age.equals("12-20")) {
                            ///////////////////////////////////////////////////////////////////
                            // Need to double check this
                            vit1 = "Royal Jelly";
                            vit2 = "B Vitamins";
                            vit3 = "L-Tryptophan";
                        } else {
                            vit1 = "Rhodiola";
                            vit2 = "B Vitamins";
                            vit3 = "L-Tryptophan";
                        }
                    }
                    if (!age.equals("20-60")) {
                        if (count == 0) {
                            user.setProblem(Problem.ENERGY);
                            db.insertProblemTask(Problem.ENERGY);
                            if (double_p1.isChecked()) {
                                if (seek1.getProgress() == 1) {
                                    user.setSupplement1(vit1);
                                } else if (seek1.getProgress() == 2) {
                                    user.setSupplement1(vit1);
                                    user.setSupplement2(vit2);
                                } else {
                                    user.setSupplement1(vit1);
                                    user.setSupplement2(vit2);
                                    user.setSupplement3(vit3);
                                }
                            } else {
                                Toast.makeText(v.getContext(),"Must select a problem from the available options",Toast.LENGTH_SHORT).show();
                                check = false;
                            }
                        } else {
                            user.setProblem2(Problem.ENERGY);
                            db.insertProblemTask(Problem.ENERGY);
                            if (double_p1.isChecked()) {
                                if (seek2.getProgress() == 1) {
                                    user.setSupplement4(vit1);
                                } else if (seek2.getProgress() == 2) {
                                    user.setSupplement4(vit1);
                                    user.setSupplement3(vit2);
                                } else {
                                    user.setSupplement4(vit1);
                                    user.setSupplement3(vit2);
                                    user.setSupplement2(vit3);
                                }
                            } else {
                                Toast.makeText(v.getContext(),"Must select a problem from the available options",Toast.LENGTH_SHORT).show();
                                check = false;
                            }
                        }
                    } else {
                        RadioButton double_p2 = (RadioButton) array.get(count).getView().findViewById(R.id.double_problem2);
                        if (count == 0) {
                            user.setProblem(Problem.ENERGY);
                            db.insertProblemTask(Problem.ENERGY);
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
                                    user.setSupplement1(vit1);
                                } else if (seek1.getProgress() == 2) {
                                    user.setSupplement1(vit1);
                                    user.setSupplement2(vit2);
                                } else {
                                    user.setSupplement1(vit1);
                                    user.setSupplement2(vit2);
                                    user.setSupplement3(vit3);
                                }
                            } else {
                                Toast.makeText(v.getContext(),"Must select a problem from the available options",Toast.LENGTH_SHORT).show();
                                check = false;
                            }
                        } else {
                            user.setProblem2(Problem.ENERGY);
                            db.insertProblemTask(Problem.ENERGY);
                            if (double_p1.isChecked()) {
                                if (seek2.getProgress() == 1) {
                                    user.setSupplement4("Guarana");
                                } else if (seek2.getProgress() == 2) {
                                    user.setSupplement4("Guarana");
                                    user.setSupplement3("Magnesium");
                                } else {
                                    user.setSupplement4("Guarana");
                                    user.setSupplement3("Magnesium");
                                    user.setSupplement2("Coenzyme Q10");
                                }
                            } else if (double_p2.isChecked()) {
                                if (seek2.getProgress() == 1) {
                                    user.setSupplement4(vit1);
                                } else if (seek2.getProgress() == 2) {
                                    user.setSupplement4(vit1);
                                    user.setSupplement3(vit2);
                                } else {
                                    user.setSupplement4(vit1);
                                    user.setSupplement3(vit2);
                                    user.setSupplement2(vit3);
                                }
                            } else {
                                Toast.makeText(v.getContext(),"Must select a problem from the available options",Toast.LENGTH_SHORT).show();
                                check = false;
                            }
                        }
                    }
                    count++;
                } if (bundle.getBoolean(Problem.IMMUNITY)) {
                    RadioButton double_p1 = (RadioButton) array.get(count).getView().findViewById(R.id.double_problem1);
                    RadioButton double_p2 = (RadioButton) array.get(count).getView().findViewById(R.id.double_problem2);
                    if (double_p1.isChecked()) {
                        if (age.equals("60+")) {
                            vit1 = "Royal Jelly";
                            vit2 = "Zinc";
                            vit3 = "Shiitake";
                        } else {
                            vit1 = "Zinc";
                            vit2 = "Shiitake";
                            vit3 = "D Vitamins";
                        }
                    }
                    if (count == 0) {
                        user.setProblem(Problem.IMMUNITY);
                        db.insertProblemTask(Problem.IMMUNITY);
                        if (double_p1.isChecked()) {
                            if (seek1.getProgress() == 1) {
                                user.setSupplement1(vit1);
                            } else if (seek1.getProgress() == 2) {
                                user.setSupplement1(vit1);
                                user.setSupplement2(vit2);
                            } else {
                                user.setSupplement1(vit1);
                                user.setSupplement2(vit2);
                                user.setSupplement3(vit3);
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
                            Toast.makeText(v.getContext(),"Must select a problem from the available options",Toast.LENGTH_SHORT).show();
                            check = false;
                        }
                    } else {
                        user.setProblem2(Problem.IMMUNITY);
                        db.insertProblemTask(Problem.IMMUNITY);
                        if (double_p1.isChecked()) {
                            if (seek2.getProgress() == 1) {
                                user.setSupplement4(vit1);
                            } else if (seek2.getProgress() == 2) {
                                user.setSupplement4(vit1);
                                user.setSupplement3(vit2);
                            } else {
                                user.setSupplement4(vit1);
                                user.setSupplement3(vit2);
                                user.setSupplement2(vit3);
                            }
                        } else if (double_p2.isChecked()) {
                            if (seek2.getProgress() == 1) {
                                user.setSupplement4("Propolis");
                            } else if (seek2.getProgress() == 2) {
                                user.setSupplement4("Propolis");
                                user.setSupplement3("Nigella");
                            } else {
                                user.setSupplement4("Propolis");
                                user.setSupplement2("Nigella");
                                user.setSupplement3("Glutathione");
                            }
                        } else {
                            Toast.makeText(v.getContext(),"Must select a problem from the available options",Toast.LENGTH_SHORT).show();
                            check = false;
                        }
                    }
                    count++;
                } if (bundle.getBoolean(Problem.SKIN)) {
                    RadioButton triple_p1 = (RadioButton) array.get(count).getView().findViewById(R.id.triple_problem1);
                    RadioButton triple_p2 = (RadioButton) array.get(count).getView().findViewById(R.id.triple_problem2);
                    RadioButton triple_p3 = (RadioButton) array.get(count).getView().findViewById(R.id.triple_problem3);
                    if (triple_p1.isChecked()) {
                        vit3 = "Silica";
                        if (age.equals("60+")) {
                            vit1 = "Wheat Germ Oil";
                            vit2 = "Borage";
                        } else {
                            vit2 = "Wheat Germ Oil";
                            vit1 = "Borage";
                        }
                    }
                    if (count == 0) {
                        user.setProblem(Problem.SKIN);
                        db.insertProblemTask(Problem.SKIN);
                        if (triple_p1.isChecked()) {
                            if (seek1.getProgress() == 1) {
                                user.setSupplement1(vit1);
                            } else if (seek1.getProgress() == 2) {
                                user.setSupplement1(vit1);
                                user.setSupplement2(vit2);
                            } else {
                                user.setSupplement1(vit1);
                                user.setSupplement2(vit2);
                                user.setSupplement3(vit3);
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
                            Toast.makeText(v.getContext(),"Must select a problem from the available options",Toast.LENGTH_SHORT).show();
                            check = false;
                        }
                    } else {
                        user.setProblem2(Problem.SKIN);
                        db.insertProblemTask(Problem.SKIN);
                        if (triple_p1.isChecked()) {
                            if (seek2.getProgress() == 1) {
                                user.setSupplement4(vit1);
                            } else if (seek2.getProgress() == 2) {
                                user.setSupplement4(vit1);
                                user.setSupplement3(vit2);
                            } else {
                                user.setSupplement4(vit1);
                                user.setSupplement2(vit2);
                                user.setSupplement3(vit3);
                            }
                        } else if (triple_p2.isChecked()) {
                            if (seek2.getProgress() == 1) {
                                user.setSupplement4("Vegan Collagen");
                            } else if (seek2.getProgress() == 2) {
                                user.setSupplement4("Vegan Collagen");
                                user.setSupplement3("Grape Seeds");
                            } else {
                                user.setSupplement4("Vegan Collagen");
                                user.setSupplement2("Grape Seeds");
                                user.setSupplement3("Acerola");
                            }
                        } else if (triple_p3.isChecked()) {
                            if (seek2.getProgress() == 1) {
                                user.setSupplement4("Beer Yeast");
                            } else if (seek2.getProgress() == 2) {
                                user.setSupplement4("Beer Yeast");
                                user.setSupplement3("Zinc");
                            } else {
                                user.setSupplement4("Beer Yeast");
                                user.setSupplement2("Zinc");
                                user.setSupplement3("Burdock Root");
                            }
                        } else {
                            Toast.makeText(v.getContext(),"Must select a problem from the available options",Toast.LENGTH_SHORT).show();
                            check = false;
                        }
                    }
                    count++;
                } if (bundle.getBoolean(Problem.DETOX)) {
                    RadioButton triple_p1 = (RadioButton) array.get(count).getView().findViewById(R.id.triple_problem1);
                    RadioButton triple_p2 = (RadioButton) array.get(count).getView().findViewById(R.id.triple_problem2);
                    RadioButton triple_p3 = (RadioButton) array.get(count).getView().findViewById(R.id.triple_problem3);
                    if (count == 0) {
                        user.setProblem(Problem.DETOX);
                        db.insertProblemTask(Problem.DETOX);
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
                            Toast.makeText(v.getContext(),"Must select a problem from the available options",Toast.LENGTH_SHORT).show();
                            check = false;
                        }
                    } else {
                        user.setProblem2(Problem.DETOX);
                        db.insertProblemTask(Problem.DETOX);
                        if (triple_p1.isChecked()) {
                            if (seek2.getProgress() == 1) {
                                user.setSupplement4("Chlorella");
                            } else if (seek2.getProgress() == 2) {
                                user.setSupplement4("Chlorella");
                                user.setSupplement3("Amalaki");
                            } else {
                                user.setSupplement4("Chlorella");
                                user.setSupplement2("Amalaki");
                                user.setSupplement3("Grapefruit Seeds");
                            }
                        } else if (triple_p2.isChecked()) {
                            if (seek2.getProgress() == 1) {
                                user.setSupplement4("Desmodium");
                            } else if (seek2.getProgress() == 2) {
                                user.setSupplement4("Desmodium");
                                user.setSupplement3("Chrysanthellum");
                            } else {
                                user.setSupplement4("Desmodium");
                                user.setSupplement2("Chrysanthellum");
                                user.setSupplement3("Milk Thistle");
                            }
                        } else if (triple_p3.isChecked()) {
                            if (seek2.getProgress() == 1) {
                                user.setSupplement4("Fennel");
                            } else if (seek2.getProgress() == 2) {
                                user.setSupplement4("Fennel");
                                user.setSupplement3("Rosmary");
                            } else {
                                user.setSupplement4("Fennel");
                                user.setSupplement2("Rosmary");
                                user.setSupplement3("Peppermint");
                            }
                        } else {
                            Toast.makeText(v.getContext(),"Must select a problem from the available options",Toast.LENGTH_SHORT).show();
                            check = false;
                        }
                    }
                    count++;
                } if (bundle.getBoolean(Problem.EXERCISE)) {
                    RadioButton double_p1 = (RadioButton) array.get(count).getView().findViewById(R.id.double_problem1);
                    RadioButton double_p2 = (RadioButton) array.get(count).getView().findViewById(R.id.double_problem2);
                    if (count == 0) {
                        user.setProblem(Problem.EXERCISE);
                        db.insertProblemTask(Problem.EXERCISE);
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
                            Toast.makeText(v.getContext(),"Must select a problem from the available options",Toast.LENGTH_SHORT).show();
                            check = false;
                        }
                    } else {
                        user.setProblem2(Problem.EXERCISE);
                        db.insertProblemTask(Problem.EXERCISE);
                        if (double_p1.isChecked()) {
                            if (seek2.getProgress() == 1) {
                                user.setSupplement4("L-Carnitine");
                            } else if (seek2.getProgress() == 2) {
                                user.setSupplement4("L-Carnitine");
                                user.setSupplement3("Creatine");
                            } else {
                                user.setSupplement4("L-Carnitine");
                                user.setSupplement2("Creatine");
                                user.setSupplement3("Warana");
                            }
                        } else if (double_p2.isChecked()) {
                            if (seek2.getProgress() == 1) {
                                user.setSupplement4("BCAA");
                            } else if (seek2.getProgress() == 2) {
                                user.setSupplement4("BCAA");
                                user.setSupplement3("Coenzyme Q10");
                            } else {
                                user.setSupplement4("BCAA");
                                user.setSupplement2("Coenzyme Q10");
                                user.setSupplement3("Glutamine");
                            }
                        } else {
                            Toast.makeText(v.getContext(),"Must select a problem from the available options",Toast.LENGTH_SHORT).show();
                            check = false;
                        }
                    }
                    count++;
                } if (bundle.getBoolean(Problem.DIGESTION)) {
                    RadioButton double_p1 = (RadioButton) array.get(count).getView().findViewById(R.id.double_problem1);
                    RadioButton double_p2 = (RadioButton) array.get(count).getView().findViewById(R.id.double_problem2);
                    if (double_p1.isChecked()) {
                        if (!age.equals("20-60")) {
                            /////////////////////////////////////////////////////////////////////////////////////
                            // Might need to change this
                            vit1 = "Fennel";
                            vit2 = "Coriander";
                            vit3 = "Propolis";
                        } else {
                            vit1 = "Fennel";
                            vit2 = "Coriander";
                            vit3 = "Propolis";
                        }
                    }
                    if (count == 0) {
                        user.setProblem(Problem.DIGESTION);
                        db.insertProblemTask(Problem.DIGESTION);
                        if (double_p1.isChecked()) {
                            if (seek1.getProgress() == 1) {
                                user.setSupplement1(vit1);
                            } else if (seek1.getProgress() == 2) {
                                user.setSupplement1(vit1);
                                user.setSupplement2(vit2);
                            } else {
                                user.setSupplement1(vit1);
                                user.setSupplement2(vit2);
                                user.setSupplement3(vit3);
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
                            Toast.makeText(v.getContext(),"Must select a problem from the available options",Toast.LENGTH_SHORT).show();
                            check = false;
                        }
                    } else {
                        user.setProblem2(Problem.DIGESTION);
                        db.insertProblemTask(Problem.DIGESTION);
                        if (double_p1.isChecked()) {
                            if (seek2.getProgress() == 1) {
                                user.setSupplement4(vit1);
                            } else if (seek2.getProgress() == 2) {
                                user.setSupplement4(vit1);
                                user.setSupplement3(vit2);
                            } else {
                                user.setSupplement4(vit1);
                                user.setSupplement2(vit2);
                                user.setSupplement3(vit3);
                            }
                        } else if (double_p2.isChecked()) {
                            if (seek2.getProgress() == 1) {
                                user.setSupplement4("Lithothamne");
                            } else if (seek2.getProgress() == 2) {
                                user.setSupplement4("Lithothamne");
                                user.setSupplement3("Bromelain");
                            } else {
                                user.setSupplement4("Lithothamne");
                                user.setSupplement2("Bromelain");
                                user.setSupplement3("Cardamom");
                            }
                        } else {
                            Toast.makeText(v.getContext(),"Must select a problem from the available options",Toast.LENGTH_SHORT).show();
                            check = false;
                        }
                    }
                    count++;
                } if (bundle.getBoolean(Problem.ARTICULATION)) {
                    RadioButton double_p1 = (RadioButton) array.get(count).getView().findViewById(R.id.double_problem1);
                    RadioButton double_p2 = (RadioButton) array.get(count).getView().findViewById(R.id.double_problem2);
                    if (double_p1.isChecked()) {
                        //////////////////////////////////////////////////////////////////////////////////////////////////
                        // Need to edit this
                        if (age.equals("60+")) {
                            vit1 = "Collagen";
                            vit2 = "Silica";
                            vit3 = "Boswellia";
                        } else if (age.equals("12-20")) {
                            vit1 = "Borage";
                            vit2 = "Boswellia";
                            vit3 = "Collagen";
                        } else {
                            vit3 = "Fermented Papaya";
                            vit1 = "Boswellia";
                            vit2 = "Collagen";
                        }
                    } else {
                        if (age.equals("12-20")) {
                            vit1 = "Glucosamine";
                            vit2 = "Palmitoylethanolamide";
                            vit3 = "Black Currant";
                        } else {
                            vit1 = "Curcumin";
                            vit2 = "Palmitoylethanolamide";
                            vit3 = "Black Currant";
                        }
                    }
                    if (count == 0) {
                        user.setProblem(Problem.ARTICULATION);
                        db.insertProblemTask(Problem.ARTICULATION);
                        if (double_p1.isChecked()) {
                            if (seek1.getProgress() == 1) {
                                user.setSupplement1(vit1);
                            } else if (seek1.getProgress() == 2) {
                                user.setSupplement1(vit1);
                                user.setSupplement2(vit2);
                            } else {
                                user.setSupplement1(vit1);
                                user.setSupplement2(vit2);
                                user.setSupplement3(vit3);
                            }
                        } else if (double_p2.isChecked()) {
                            if (seek1.getProgress() == 1) {
                                user.setSupplement1(vit1);
                            } else if (seek1.getProgress() == 2) {
                                user.setSupplement1(vit1);
                                user.setSupplement2(vit2);
                            } else {
                                user.setSupplement1(vit1);
                                user.setSupplement2(vit2);
                                user.setSupplement3(vit3);
                            }
                        } else {
                            Toast.makeText(v.getContext(),"Must select a problem from the available options",Toast.LENGTH_SHORT).show();
                            check = false;
                        }
                    } else {
                        user.setProblem2(Problem.ARTICULATION);
                        db.insertProblemTask(Problem.ARTICULATION);
                        if (double_p1.isChecked()) {
                            if (seek2.getProgress() == 1) {
                                user.setSupplement4(vit1);
                            } else if (seek2.getProgress() == 2) {
                                user.setSupplement4(vit1);
                                user.setSupplement3(vit2);
                            } else {
                                user.setSupplement4(vit1);
                                user.setSupplement2(vit2);
                                user.setSupplement3(vit3);
                            }
                        } else if (double_p2.isChecked()) {
                            if (seek2.getProgress() == 1) {
                                user.setSupplement4(vit1);
                            } else if (seek2.getProgress() == 2) {
                                user.setSupplement4(vit1);
                                user.setSupplement3(vit2);
                            } else {
                                user.setSupplement4(vit1);
                                user.setSupplement2(vit2);
                                user.setSupplement3(vit3);
                            }
                        } else {
                            Toast.makeText(v.getContext(),"Must select a problem from the available options",Toast.LENGTH_SHORT).show();
                            check = false;
                        }
                    }
                    count++;
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

    public void checkboxFragment(Bundle bundle) {
        if (bundle.getBoolean("Weight")) {
            // Create new bundles for passing info
            triple1 = new SurveyTripleCheckboxFragment();
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            triple1.setArguments(bundle);
            fragmentTransaction.replace(R.id.checkboxLayout1, triple1);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
            problem1.setText("Weightloss");
            array.add(triple1);
            count++;
        } if (bundle.getBoolean("Sleep")) {
            if (count == 0) {
                triple1 = new SurveyTripleCheckboxFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                triple1.setArguments(bundle);
                fragmentTransaction.replace(R.id.checkboxLayout1, triple1);
                problem1.setText("Sleep");
                array.add(triple1);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            } else {
                triple2 = new SurveyTripleCheckboxFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                Bundle frag_bundle = new Bundle();
                frag_bundle.putBoolean("Sleep", true);
                triple2.setArguments(frag_bundle);
                fragmentTransaction.replace(R.id.checkboxLayout2, triple2);
                problem2.setText("Sleep");
                array.add(triple2);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
            count++;
        } if (bundle.getBoolean("Energy")) {
            if (count == 0) {
                if (!age.equals("20-60")) {
                    single1 = new SurveySingleCheckboxFragment();
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    single1.setArguments(bundle);
                    fragmentTransaction.replace(R.id.checkboxLayout1, single1);
                    problem1.setText("Energy");
                    array.add(single1);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                } else {
                    double1 = new SurveyDoubleCheckboxFragment();
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    double1.setArguments(bundle);
                    fragmentTransaction.replace(R.id.checkboxLayout1, double1);
                    problem1.setText("Energy");
                    array.add(double1);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                }
            } else {
                if (!age.equals("20-60")) {
                    single2 = new SurveySingleCheckboxFragment();
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    Bundle frag_bundle = new Bundle();
                    frag_bundle.putBoolean("Energy", true);
                    single2.setArguments(frag_bundle);
                    fragmentTransaction.replace(R.id.checkboxLayout2, single2);
                    problem2.setText("Energy");
                    array.add(single2);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                } else {
                    double2 = new SurveyDoubleCheckboxFragment();
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    Bundle frag_bundle = new Bundle();
                    frag_bundle.putBoolean("Energy", true);
                    double2.setArguments(frag_bundle);
                    fragmentTransaction.replace(R.id.checkboxLayout2, double2);
                    problem2.setText("energy");
                    array.add(double2);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                }
            }
            count++;
        } if (bundle.getBoolean("Immunity")) {
            if (count == 0) {
                double1 = new SurveyDoubleCheckboxFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                double1.setArguments(bundle);
                fragmentTransaction.replace(R.id.checkboxLayout1, double1);
                problem1.setText("Immunity");
                array.add(double1);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            } else {
                double2 = new SurveyDoubleCheckboxFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                Bundle frag_bundle = new Bundle();
                frag_bundle.putBoolean("Immunity", true);
                double2.setArguments(frag_bundle);
                fragmentTransaction.replace(R.id.checkboxLayout2, double2);
                problem2.setText("Immunity");
                array.add(double2);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
            count++;
        } if (bundle.getBoolean("Skin")) {
            if (count == 0) {
                triple1 = new SurveyTripleCheckboxFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                triple1.setArguments(bundle);
                fragmentTransaction.replace(R.id.checkboxLayout1, triple1);
                problem1.setText("Skin");
                array.add(triple1);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            } else {
                triple2 = new SurveyTripleCheckboxFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                Bundle frag_bundle = new Bundle();
                frag_bundle.putBoolean("Skin", true);
                triple2.setArguments(frag_bundle);
                fragmentTransaction.replace(R.id.checkboxLayout2, triple2);
                problem2.setText("Skin");
                array.add(triple2);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
            count++;
        } if (bundle.getBoolean("Detox")) {
            if (count == 0) {
                triple1 = new SurveyTripleCheckboxFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                triple1.setArguments(bundle);
                fragmentTransaction.replace(R.id.checkboxLayout1, triple1);
                problem1.setText("Detox");
                array.add(triple1);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            } else {
                triple2 = new SurveyTripleCheckboxFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                Bundle frag_bundle = new Bundle();
                frag_bundle.putBoolean("Detox", true);
                triple2.setArguments(frag_bundle);
                fragmentTransaction.replace(R.id.checkboxLayout2, triple2);
                problem2.setText("Detox");
                array.add(triple2);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
            count++;
        } if (bundle.getBoolean("Exercise")) {
            if (count == 0) {
                double1 = new SurveyDoubleCheckboxFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                double1.setArguments(bundle);
                fragmentTransaction.replace(R.id.checkboxLayout1, double1);
                problem1.setText("Exercise");
                array.add(double1);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            } else {
                double2 = new SurveyDoubleCheckboxFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                Bundle frag_bundle = new Bundle();
                frag_bundle.putBoolean("Exercise", true);
                double2.setArguments(frag_bundle);
                fragmentTransaction.replace(R.id.checkboxLayout2, double2);
                problem2.setText("Exercise");
                array.add(double2);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
            count++;
        } if (bundle.getBoolean("Digestion")) {
            if (count == 0) {
                double1 = new SurveyDoubleCheckboxFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                double1.setArguments(bundle);
                fragmentTransaction.replace(R.id.checkboxLayout1, double1);
                problem1.setText("Digestion");
                array.add(double1);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            } else {
                double2 = new SurveyDoubleCheckboxFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                Bundle frag_bundle = new Bundle();
                frag_bundle.putBoolean("Digestion", true);
                double2.setArguments(frag_bundle);
                fragmentTransaction.replace(R.id.checkboxLayout2, double2);
                problem2.setText("Digestion");
                array.add(double2);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
            count++;
        } if (bundle.getBoolean("Articulation")) {
            if (count == 0) {
                double1 = new SurveyDoubleCheckboxFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                double1.setArguments(bundle);
                fragmentTransaction.replace(R.id.checkboxLayout1, double1);
                problem1.setText("Articulation");
                array.add(double1);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            } else {
                double2 = new SurveyDoubleCheckboxFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                Bundle frag_bundle = new Bundle();
                frag_bundle.putBoolean("Articulation", true);
                double2.setArguments(frag_bundle);
                fragmentTransaction.replace(R.id.checkboxLayout2, double2);
                problem2.setText("Articulation");
                array.add(double2);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
            count++;
        }
    }
}