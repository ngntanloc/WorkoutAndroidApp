package com.example.workoutapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import static com.example.workoutapp.TrainingAdapter.TRAINING_KEY;

public class TrainingItemActivity extends AppCompatActivity implements DialogAddPlan.PassPlanInterface {

    @Override
    public void getPlan(Plan plan) {
        Log.d(TAG, "getPlan: started");

        if (Utils.addPlain(plan)) {

            Toast.makeText(this, plan.getTraining().getName() + " Added Successfully", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(this, PlanActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }

    }

    private static final String TAG = "TrainingItemActivity";

    private Button btnAddToPlan;
    private TextView trainingName;
    private TextView description;
    private ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training_item);

        initView();
        Intent intent = getIntent();

        if (intent != null) {
            Training training = intent.getParcelableExtra(TRAINING_KEY);
            if (training != null) {
                trainingName.setText(training.getName());
                description.setText(training.getLongDesc());

                Glide.with(this)
                        .asBitmap()
                        .load(training.getImageUrl())
                        .into(image);

            }

            btnAddToPlan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DialogAddPlan dialogAddPlan = new DialogAddPlan();
                    Bundle bundle = new Bundle();
                    bundle.putParcelable(TRAINING_KEY, training);
                    dialogAddPlan.setArguments(bundle);
                    dialogAddPlan.show(getSupportFragmentManager(), "plane detail dialog");
                }
            });

        }

    }

    private void initView() {

        btnAddToPlan = findViewById(R.id.btnToAddPlan);
        trainingName = findViewById(R.id.txtTrainingItemName);
        description = findViewById(R.id.descriptionTrainingItem);
        image = findViewById(R.id.imgTrainingItem);

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}