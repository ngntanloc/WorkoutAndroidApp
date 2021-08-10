package com.example.workoutapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class AllTrainingActivity extends AppCompatActivity {



    private RecyclerView recyclerView;
    private TrainingAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_training);

        recyclerView = findViewById(R.id.recyclerView);
        adapter = new TrainingAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        // đưa dữ liệu vào - dữ liệu static
        ArrayList<Training> trainings = Utils.getTrainings();
        if (trainings != null) {
            adapter.setTrainings(trainings);
        }

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

        startActivity(intent);
    }

}