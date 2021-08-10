package com.example.workoutapp;

import android.content.Context;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Utils {

    private static ArrayList<Training> trainings;
    private static ArrayList<Plan> plans;

    public static ArrayList<Plan> getPlans() {
        return plans;
    }

    public static void setPlans(ArrayList<Plan> plans) {
        Utils.plans = plans;
    }

    public static void initTrainings() {
        if (trainings == null) {
            trainings = new ArrayList<>();
        }

        Training pushUp = new Training(1, "Push Up", "An exercise in which a person lies facing the floor and, keeping their back straight"
                , "A push-up (sometimes called a press-up in British English) is a common calisthenics exercise beginning from the prone position."
                , "https://cdn1.coachmag.co.uk/sites/coachmag/files/2018/01/push-up-top.jpg");

        Training squat = new Training(2, "Squat", "A position in which one's knees are bent and one's heels are close to or touching"
                , "crouch or sit with one's knees bent and one's heels close to or touching one's buttocks or the back of one's thighs, unlawfully occupy an uninhabited building or settle on a piece of land"
                , "https://media.self.com/photos/5ea9bc77bb9c6b75996c7e91/16:9/w_4510,h_2537,c_limit/squats_woman_exercise.jpg");

        Training legPress = new Training(3, "Leg Press", "The leg press is a compound weight training exercise in which the individual pushes a"
                , "The leg press is a popular piece of gym equipment that can help build key muscles in your legs. There are two types of leg press machines commonly found in",
                "https://cdn1.coachmag.co.uk/sites/coachmag/files/legpress1.png");

        Training pectorals = new Training(4, "Pectorals", "Pectoralis muscle, any of the muscles that connect the front walls of the chest with the bones of the upper arm and shoulder"
                , "Pectoralis muscle, any of the muscles that connect the front walls of the chest with the bones of the upper arm and shoulder. There are two such muscles on each side of the sternum (breastbone) in the human body: pectoralis major and pectoralis minor."
                , "https://cdn2.coachmag.co.uk/sites/coachmag/files/2017/05/bench-press_0.jpg");

        Training pullUps = new Training(5, "Pull ups", "A pull-up is an upper-body strength exercise"
                , "A pull-up is an upper-body strength exercise. The pull-up is a closed-chain movement where the body is suspended by the hands and pulls up. As this happens, the elbows flex and the shoulders adduct and extend to bring the elbows to the torso."
                , "https://images04.military.com/sites/default/files/styles/full/public/media/military-fitness/2014/07/tips-for-better-pullups-image.jpg?itok=jf24qI0E");


        trainings.add(pushUp);
        trainings.add(squat);
        trainings.add(legPress);
        trainings.add(pectorals);
        trainings.add(pullUps);

    }

    public static boolean addPlain(Plan plan) {
        if (null == plans) {
            plans = new ArrayList<>();
        }
        return plans.add(plan);
    }

    public static boolean removePlan(Plan plan) {
        return plans.remove(plan);
    }


    public static ArrayList<Training> getTrainings() {
        return trainings;
    }

    public static void setTrainings(ArrayList<Training> trainings) {
        Utils.trainings = trainings;
    }
}
