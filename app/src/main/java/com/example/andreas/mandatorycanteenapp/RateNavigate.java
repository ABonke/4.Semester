package com.example.andreas.mandatorycanteenapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.gesture.GestureOverlayView;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RateNavigate extends AppCompatActivity implements GestureDetector.OnGestureListener {

    private GestureDetector gestureDetector;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    public static final String PREF_FILE_NAME = "loginPref";
    public static final String USERNAME = "USERNAME";
    private SharedPreferences preferences;
    private EditText emailEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_navigate);
        gestureDetector = new GestureDetector(this, this);

        preferences = getSharedPreferences(PREF_FILE_NAME, MODE_PRIVATE);
        emailEditText = (EditText) findViewById(R.id.Email_text);
        String username = preferences.getString(USERNAME, null);
        if (username != null) {
            emailEditText.setText(username);
        }


        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d("User signed in!", "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Log.d("User signed out!", "onAuthStateChanged:signed_out");
                }
            }
        };
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }

    public void SignUp_Button(View view) {
        EditText emailEditText = (EditText) findViewById(R.id.Email_text);
        EditText passwordEditText = (EditText) findViewById(R.id.Password_text);
        signUp(emailEditText.getText().toString().trim(), passwordEditText.getText().toString().trim());
    }

    public void signUp(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d("Log in Complete!", "createUserWithEmail:onComplete:" + task.isSuccessful());
                        if (!task.isSuccessful()) {
                            Toast.makeText(RateNavigate.this, "Account creation failed",
                                    Toast.LENGTH_SHORT).show();
                        }
                        if (task.isSuccessful()) {
                            Toast.makeText(RateNavigate.this, "Account creation success!",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    public void Login_Button(View view) {
        EditText emailEditText = (EditText) findViewById(R.id.Email_text);
        EditText passwordEditText = (EditText) findViewById(R.id.Password_text);
        signIn(emailEditText.getText().toString(), passwordEditText.getText().toString());
    }

    public void signIn(final String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d("SignInComplete", "signInWithEmail:onComplete:" + task.isSuccessful());

                        if (!task.isSuccessful()) {
                            Log.w("Sign in failed!", "signInWithEmail:failed", task.getException());
                            Toast.makeText(RateNavigate.this, "Authentication Failed!",
                                    Toast.LENGTH_SHORT).show();
                        }
                        if (task.isSuccessful()) {
                            Log.w("Sign in failed!", "signInWithEmail:failed", task.getException());
                            Toast.makeText(RateNavigate.this, "Authentication Great Success!",
                                    Toast.LENGTH_SHORT).show();
                            CheckBox checkBox = (CheckBox) findViewById(R.id.RememberCheckbox);
                            SharedPreferences.Editor editor = preferences.edit();
                            if (checkBox.isChecked()) {
                                editor = preferences.edit();
                                editor.putString(USERNAME, emailEditText.getText().toString());
                            } else {
                                editor.remove(USERNAME);
                            }
                            editor.apply();
                            Intent intent = new Intent(RateNavigate.this, LoggedInActivity.class);
                            intent.putExtra("Email", email);
                            startActivity(intent);
                        }
                    }
                });
    }

    //region OnGestureDetectionMethods
    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }
    //endregion

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        Log.d("Gesture!", "onFling " + e1.toString() + "::::" + e2.toString());
        boolean leftSwipe = e1.getX() > e2.getX();
        Log.d("Gesture!", "onFling left: " + leftSwipe);
        if (leftSwipe) {
            Intent intent = new Intent(this, RateInformationActivity.class);
            startActivity(intent);
        }
        return true;
    }
}





