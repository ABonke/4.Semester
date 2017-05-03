package com.example.andreas.mandatorycanteenapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
    }

    public void MenuNavigate(View view) {
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }

    public void TakeAwayNavigate(View view) {
        Intent intent = new Intent(this, TakeAwayActivity.class);
        startActivity(intent);
    }


    public void RateNavigate(View view) {
        Intent intent = new Intent(this, RateNavigate.class);
        startActivity(intent);

    }

    public void CalorieNavigate(View view) {
        Intent intent = new Intent(this, CalorieNavigate.class);
        startActivity(intent);
    }
}
