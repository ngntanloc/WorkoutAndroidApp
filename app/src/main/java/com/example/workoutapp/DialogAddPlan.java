package com.example.workoutapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.sql.CallableStatement;

import static com.example.workoutapp.TrainingAdapter.TRAINING_KEY;

public class DialogAddPlan extends DialogFragment {

    public interface PassPlanInterface {
        void getPlan(Plan plan);
    }

    private PassPlanInterface passPlanInterface;

    private TextView trainingName;
    private EditText minute;
    private Spinner spinnerDays;
    private Button btnAdd, btnDissmiss;


    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        LayoutInflater inflater = LayoutInflater.from(getActivity());
        View view = inflater.inflate(R.layout.dialog_add_plan, null);

        initView(view);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
                .setView(view)
                .setTitle("Planing you plan");

        Bundle bundle = getArguments();
        if (builder != null) {
            Training training = bundle.getParcelable(TRAINING_KEY);
            if (training != null) {
                trainingName.setText(training.getName());

                btnDissmiss.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
                btnAdd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String day = spinnerDays.getSelectedItem().toString();
                        int miutes = Integer.valueOf(minute.getText().toString());

                        Plan plan = new Plan(training, miutes, day, false);

                        try {
                            passPlanInterface = (PassPlanInterface) getActivity();
                            passPlanInterface.getPlan(plan);
                            dismiss();
                        } catch (ClassCastException e) {
                            e.printStackTrace();
                            dismiss();
                        }

                    }
                });
            }
        }


        return builder.create();
    }

    private void initView(View view) {

        trainingName = view.findViewById(R.id.txtName);
        minute = view.findViewById(R.id.edtTxtMinutes);
        spinnerDays = view.findViewById(R.id.spinnerDays);
        btnAdd = view.findViewById(R.id.btnAdd);
        btnDissmiss = view.findViewById(R.id.btnDissmiss);

    }


}
