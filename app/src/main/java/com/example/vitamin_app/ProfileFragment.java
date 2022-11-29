package com.example.vitamin_app;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.provider.CalendarContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
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

import java.util.GregorianCalendar;

public class ProfileFragment extends Fragment {

    // creating a variable for our
    // Firebase Database.
    FirebaseDatabase firebaseDatabase;

    // creating a variable for our Database
    // Reference for Firebase.
    DatabaseReference databaseReference;

    private GoogleSignInClient mGoogleSignInClient;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_profile, container, false);

        firebaseDatabase = FirebaseDatabase.getInstance();

        // line below is used to get reference for our database.
        databaseReference = firebaseDatabase.getReference("Users");

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();

        // Sign out functionality
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .requestId()
                .requestIdToken("517722316464-2o671af2pt9mmc3ebsm16jgt746i1k7q.apps.googleusercontent.com")
                .requestProfile()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(getActivity(), gso);

        Button signout = (Button) v.findViewById(R.id.signout_btn);
        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                Toast.makeText(getActivity(), "Logout from " + currentUser.getEmail(), Toast.LENGTH_LONG).show();
                mGoogleSignInClient.signOut().addOnCompleteListener(getActivity(), new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                    }
                });
            }
        });

        // Calendar API functionality
        TextView text = (TextView) v.findViewById(R.id.textView2);
        Button button = (Button) v.findViewById(R.id.button3);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_INSERT);
                intent.setType("vnd.android.cursor.item/event");
                intent.putExtra(CalendarContract.Events.TITLE, "Learn Android");
                intent.putExtra(CalendarContract.Events.EVENT_LOCATION, "Home suit home");
                intent.putExtra(CalendarContract.Events.DESCRIPTION, "Download Examples");

                // Setting dates
                GregorianCalendar calDate = new GregorianCalendar(2022, 11, 12);
                intent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME,
                        calDate.getTimeInMillis());
                intent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME,
                        calDate.getTimeInMillis());

                // make it a recurring Event
                intent.putExtra(CalendarContract.Events.RRULE, "FREQ=WEEKLY;COUNT=11;WKST=SU;BYDAY=TU,TH");

                // make it a full day event
                intent.putExtra(CalendarContract.EXTRA_EVENT_ALL_DAY, true);
                startActivity(intent);
            }
        });

        // Retrieving user data from firebase
        String username = currentUser.getDisplayName();
        databaseReference.child(username).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {

            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()) {
                    if(task.getResult().exists()) {
                        DataSnapshot dataSnapshot = task.getResult();
                        String problem1 = String.valueOf(dataSnapshot.child("problem").getValue());
                        String problem2 = String.valueOf(dataSnapshot.child("problem2").getValue());
                        text.setText(problem1 + " " + problem2);
                    } else {
                        Toast.makeText(getActivity(), "User does not exist",Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(getActivity(), "Failed to read data",Toast.LENGTH_LONG).show();
                }
            }
        });

        return v;
    }
}