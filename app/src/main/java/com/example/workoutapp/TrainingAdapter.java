package com.example.workoutapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class TrainingAdapter extends RecyclerView.Adapter<TrainingAdapter.ViewHolder> {

    private static final String TAG = "TrainingAdapter";
    public static final String TRAINING_KEY = "training";

    private ArrayList<Training> trainings = new ArrayList<>();

    private Context context;

    public TrainingAdapter(Context context) {
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.training_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TrainingAdapter.ViewHolder holder, int position) {

        holder.name.setText(trainings.get(position).getName());
        holder.desription.setText(trainings.get(position).getShortDesc());

        Glide.with(context)
                .asBitmap()
                .load(trainings.get(position).getImageUrl())
                .into(holder.image);

        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, TrainingItemActivity.class);
                intent.putExtra(TRAINING_KEY, trainings.get(position));
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return trainings.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private CardView parent;
        private ImageView image;
        private TextView name, desription;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            parent = itemView.findViewById(R.id.parent);
            image = itemView.findViewById(R.id.imgView);
            name = itemView.findViewById(R.id.txtName);
            desription = itemView.findViewById(R.id.txtDescription);

        }
    }

    public ArrayList<Training> getTrainings() {
        return trainings;
    }

    public void setTrainings(ArrayList<Training> trainings) {
        this.trainings = trainings;
        notifyDataSetChanged();
    }
}
