package com.example.vitamin_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.auth.api.identity.BeginSignInRequest;
import com.google.android.gms.auth.api.identity.BeginSignInResult;
import com.google.android.gms.auth.api.identity.Identity;
import com.google.android.gms.auth.api.identity.SignInClient;
import com.google.android.gms.auth.api.identity.SignInCredential;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.auth.SignInMethodQueryResult;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity {
    public static final String ONETAP = "oneTap";

    //Flags
    public static boolean signedIn;
    private static final int RC_SIGN_IN = 100;
    private static final int REQ_ONE_TAP = 2;

    //Variables for Google OAuth
    private GoogleSignInClient mGoogleSignInClient;
    private SignInClient oneTapClient;
    private BeginSignInRequest signInRequest;
    private FirebaseAuth mAuth;
    private boolean showOneTapUI;

    //Error String
    private String genericError = "An Error Occurred, Please Try Again Later";

    //Arrays for user information (gender and age)
    String[] genderList = new String[] {"Male", "Female", "Other"};
    String[] ageList = new String[43];

    // creating a variable for our
    // Firebase Database.
    FirebaseDatabase firebaseDatabase;

    // creating a variable for our Database
    // Reference for Firebase.
    DatabaseReference databaseReference;
    Users user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Set up drop down menus for gender and age
        Spinner s = (Spinner) findViewById(R.id.gender);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, genderList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s.setAdapter(adapter);

        for(int i = 18; i < 61; i++){
            ageList[i - 18] = String.valueOf(i);
        }
        ageList[42] = "60+";

        Spinner s2 = (Spinner) findViewById(R.id.age);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, ageList);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s2.setAdapter(adapter2);

        //set initial flags
        signedIn = false;
        if (getIntent().getExtras() != null) {
            showOneTapUI = savedInstanceState.getBoolean(ONETAP, true);
        } else{
            showOneTapUI = true;
        }

        //Get references to buttons
        Button toHome = (Button) findViewById(R.id.toHome);
        Button google = (Button) findViewById(R.id.button2);

        //Build objects for google OAuth
        googleSetUp();

        //Get last client to sign in and go to homepage if already logged in
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null){
            signedIn = true;
            goHome(currentUser);
        }

        //If flag is true, show Google OneTap login
        if (showOneTapUI) {
            oneTapClient.beginSignIn(signInRequest)
                    .addOnSuccessListener(this, new OnSuccessListener<BeginSignInResult>() {
                        @Override
                        public void onSuccess(BeginSignInResult result) {
                            //create google intent to sign in and handle request
                            try {
                                startIntentSenderForResult(
                                        result.getPendingIntent().getIntentSender(), REQ_ONE_TAP,
                                        null, 0, 0, 0);
                            } catch (IntentSender.SendIntentException e) {
                                //Error occured with starting One Tap UI
                            }
                        }
                    })
                    .addOnFailureListener(this, new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            // No saved credentials found.
                            //Don't do anything
                        }
                    });
        }

        //Set listener for google login button and skip button
        google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                defaultGoogleLogin();
            }
        });

        toHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goHome(null);
            }
        });
    }

    //Build relevant objects for Google OAuth
    public void googleSetUp() {
        //Build One Tap Signin Object with proper settings
        String key = "517722316464-2o671af2pt9mmc3ebsm16jgt746i1k7q.apps.googleusercontent.com";
        oneTapClient = Identity.getSignInClient(this);
        signInRequest = BeginSignInRequest.builder()
                .setPasswordRequestOptions(BeginSignInRequest.PasswordRequestOptions.builder()
                        .setSupported(true)
                        .build())
                .setGoogleIdTokenRequestOptions(BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                        .setSupported(true)
                        // Your server's client ID, not your Android client ID.
                        .setServerClientId(key)
                        // Only show accounts previously used to sign in.
                        .setFilterByAuthorizedAccounts(true)
                        .build())
                // Automatically sign in when exactly one credential is retrieved.
                .setAutoSelectEnabled(true)
                .build();

        //Get signin options with google
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .requestId()
                .requestIdToken(key)
                .requestProfile()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        mAuth = FirebaseAuth.getInstance();
    }

    public void defaultGoogleLogin(){
        //prompt user to log in through standard google login
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    public void errorPrompt(String error){
        Toast.makeText(LoginActivity.this, error, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode){
            // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            case RC_SIGN_IN:
                Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
                handleSignInResult(task);
                break;
            // Result from Google One Tap
            //Get credentials and sign in to firebase with them
            case REQ_ONE_TAP:
                try {
                    SignInCredential credential = oneTapClient.getSignInCredentialFromIntent(data);
                    String idToken = credential.getGoogleIdToken();
                    if (idToken !=  null) {
                        // Got an ID token from Google. Use it to authenticate
                        // with your backend.
                        handleSignInResult(idToken);
                    }
                } catch (ApiException e){
                    switch (e.getStatusCode()) {
                        case CommonStatusCodes.CANCELED:
                            // One tap dialog closed, don't re-prompt the user.
                            showOneTapUI = false;
                            break;
                        case CommonStatusCodes.NETWORK_ERROR:
                            //Ignore and do nothing.
                            Log.d("TAG", "One-tap encountered a network error.");
                            break;
                        default:
                            Log.d("TAG", "Couldn't get credential from result."
                                    + e.getLocalizedMessage());
                            errorPrompt(genericError);
                            break;
                    }
                }
                break;
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            Log.d("TAG", "handleSignInResult: " + account.getIdToken());
            AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
            firebaseCredentialAuth(credential);
            // Signed in successfully, show authenticated UI.
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w("TAG", "signInResult:failed code=" + e.getStatusCode());
            errorPrompt(genericError);

        }

    }

    public void handleSignInResult(String idToken){
        //Get firebase credentials from idToken and login to firebase.
        AuthCredential firebaseCredential = GoogleAuthProvider.getCredential(idToken, null);
        firebaseCredentialAuth(firebaseCredential);
    }

    private void goHome(FirebaseUser currentUser){
        // Redirect user to home page if logging in
        // If skipping, send to general fix page and don't close login activity.
        Intent intent;
        if (currentUser != null){
            intent = new Intent(this, HomeActivity.class);
            Toast.makeText(LoginActivity.this, "Welcome " + currentUser.getEmail(), Toast.LENGTH_LONG).show();
            finish();

        } else {
            intent = new Intent(this, GeneralListActivity.class);
        }

        this.startActivity(intent);
    }

    private void firebaseCredentialAuth(AuthCredential credential){
        //Attempt to sign in to Firebase with google credentials
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            //Flag for detecting if user is logged in.
                            signedIn = true;

                            //Retrieve information related to user
                            FirebaseUser currentUser = mAuth.getCurrentUser();
                            String email = currentUser.getEmail();
                            String userId = currentUser.getDisplayName();
                            firebaseDatabase = FirebaseDatabase.getInstance();
                            databaseReference = firebaseDatabase.getReference("Users");

                            // Checking if user exists in database
                            databaseReference.child(userId).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<DataSnapshot> task) {
                                    //Ping the database without any issue
                                    if (task.isSuccessful()) {
                                        //If user does not exist in database yet, create new user and add to database
                                        if(!(task.getResult().exists())) {
                                            Toast.makeText(LoginActivity.this, "User does not exist in database",Toast.LENGTH_LONG).show();

                                            user = new Users(email, userId);
                                            databaseReference.addValueEventListener(new ValueEventListener() {

                                                @Override
                                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                    databaseReference.child(userId).setValue(user);
                                                }
                                                @Override
                                                public void onCancelled(@NonNull DatabaseError error) {}
                                            });
                                        }
                                    } else {
                                        //Error occurred with reading the database
                                        Toast.makeText(LoginActivity.this, "Failed to read data",Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                            goHome(currentUser);
                        } else {
                            // If sign in fails, display a message to the user.
                            errorPrompt(genericError);
                        }
                    }
                });
    }
}

