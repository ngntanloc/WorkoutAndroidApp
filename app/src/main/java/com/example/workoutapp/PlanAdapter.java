package com.example.workoutapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;

import static com.example.workoutapp.TrainingAdapter.TRAINING_KEY;

public class PlanAdapter extends RecyclerView.Adapter<PlanAdapter.ViewHolder> {

    public interface RemovePlan {
        void onRemovePlanResult(Plan plan);
    }

    private RemovePlan removePlan;

    private static final String TAG = "PlanAdapter";
    private ArrayList<Plan> plans = new ArrayList<>();
    private Context context;
    private String type = "";

    public PlanAdapter(Context context) {
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.plan_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlanAdapter.ViewHolder holder, int position) {
        holder.txtDescription.setText(plans.get(position).getTraining().getShortDesc());
        holder.txtTrainingName.setText(plans.get(position).getTraining().getName());
        holder.txtTime.setText(String.valueOf(plans.get(position).getMinutes()));

        Glide.with(context)
                .asBitmap()
                .load(plans.get(position).getTraining().getImageUrl())
                .into(holder.imgTraining);

        if (plans.get(position).isAccomplished()) {
            holder.checkedCircle.setVisibility(View.VISIBLE);
            holder.emptyCircle.setVisibility(View.GONE);
        } else {
            holder.checkedCircle.setVisibility(View.GONE);
            holder.emptyCircle.setVisibility(View.VISIBLE);
        }

        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, TrainingItemActivity.class);
                intent.putExtra(TRAINING_KEY, plans.get(position).getTraining());
                context.startActivity(intent);
            }
        });


        if (type.equals("edit")) {
            holder.emptyCircle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(context)
                            .setTitle("Finish")
                            .setMessage("Have you finished " + plans.get(position).getTraining().getName() + "?")
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            })
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    for (Plan p : Utils.getPlans()) {
                                        if (p.equals(plans.get(position))) {
                                            p.setAccomplished(true);
                                        }
                                    }
                                    notifyDataSetChanged();
                                }
                            });
                    builder.create().show();
                }
            });
            holder.parent.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(context)
                            .setTitle("Remove")
                            .setMessage("Are you sure you want to delete " + plans.get(position).getTraining().getName() +" from your plan?")
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            })
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    try {
                                        removePlan = (RemovePlan) context;
                                        removePlan.onRemovePlanResult(plans.get(position));
                                    } catch (ClassCastException e) {
                                        e.printStackTrace();
                                    }
                                }
                            });
                    builder.create().show();
                    return true;
                }
            });
        }


    }

    @Override
    public int getItemCount() {
        return plans.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView txtTime, txtTrainingName, txtDescription;
        private ImageView imgTraining, emptyCircle, checkedCircle;
        private MaterialCardView parent;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtTime = itemView.findViewById(R.id.txtTime);
            txtTrainingName = itemView.findViewById(R.id.txtNamePlan);
            txtDescription = itemView.findViewById(R.id.txtDescriptionInPlan);
            imgTraining = itemView.findViewById(R.id.imgPlanItem);
            emptyCircle = itemView.findViewById(R.id.emptyCircle);
            checkedCircle = itemView.findViewById(R.id.checkedCircle);
            parent = itemView.findViewById(R.id.parentTraining);

        }
    }

    public void setType(String type) {
        this.type = type;
    }

    public ArrayList<Plan> getPlans() {
        return plans;
    }

    public void setPlans(ArrayList<Plan> plans) {
        this.plans = plans;
        notifyDataSetChanged();
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }
}
