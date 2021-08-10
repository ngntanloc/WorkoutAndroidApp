package com.example.workoutapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btnSeeAllActivities, btnAboutUs, btnSeeYourPlan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        Utils.initTrainings();

        btnSeeAllActivities.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AllTrainingActivity.class);
                startActivity(intent);
            }
        });

        btnSeeYourPlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PlanActivity.class);
                startActivity(intent);
            }
        });

        btnAboutUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this)
                        .setTitle("About Us")
                        .setMessage("Create by TanLoc with <3\nVisit for more information")
                        .setNegativeButton("Dismiss", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .setPositiveButton("Visit", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(MainActivity.this, WebsiteActivity.class);
                                startActivity(intent);
                            }
                        });
                builder.create().show();
            }

        });

    }

    private void initView() {
        btnSeeYourPlan = findViewById(R.id.btnSeeYourPlan);
        btnSeeAllActivities = findViewById(R.id.btnSeeAllActivities);
        btnAboutUs = findViewById(R.id.btnAboutUs);
    }
}