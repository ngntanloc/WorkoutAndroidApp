package com.example.workoutapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class EditActivity extends AppCompatActivity implements PlanAdapter.RemovePlan {

    @Override
    public void onRemovePlanResult(Plan plan) {
        if (Utils.removePlan(plan)) {
            Toast.makeText(this, "Training removed from your plan", Toast.LENGTH_SHORT).show();
            adapter.setPlans(getPlansByDay(plan.getDay()));
        }
    }

    private Button btnAddPlan;
    private TextView txtDay;


    private RecyclerView recyclerView;

    private PlanAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        initView();

        adapter = new PlanAdapter(this);
        adapter.setType("edit");
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Intent intent = getIntent();
        if (intent != null) {
            String day = intent.getStringExtra("day");
            if (day != null) {
                txtDay.setText(day);
                ArrayList<Plan> plans = getPlansByDay(day);
                adapter.setPlans(plans);
            }
            btnAddPlan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(EditActivity.this, AllTrainingActivity.class);
                    startActivity(intent);
                }
            });
        }

    }

    private ArrayList<Plan> getPlansByDay(String day) {
        ArrayList<Plan> allPlans = Utils.getPlans();
        ArrayList<Plan> plans = new ArrayList<>();

        for (Plan p : allPlans) {
            if (p.getDay().equalsIgnoreCase(day)) {
                plans.add(p);
            }
        }
        return plans;
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, PlanActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    private void initView() {
        btnAddPlan = findViewById(R.id.btnAddPlan);
        txtDay = findViewById(R.id.txtDay);
        recyclerView = findViewById(R.id.recyclerViewEdit);
    }

}