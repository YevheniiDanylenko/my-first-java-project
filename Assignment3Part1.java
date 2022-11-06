package com.shpp.p2p.cs.ydanylenko.assignment3;
import com.shpp.cs.a.console.TextProgram;

/** Assignment3Part1 class allows to monitor user's weekly physical activity
 * and give recommendation to change it depending on the discrepancies between
 * the normative and actual value of cardio activity */
public class Assignment3Part1 extends TextProgram {
    //Days in the week.
    private static final int DAYS_IN_WEEK = 7;
    //Normative weekly activity in days of minimal cardio training.
    private static final int MINIMAL_CARDIO_TRAINING_NORMATIVE = 5;
    //Normative weekly activity in days of minimal low blood pressure training.
    private static final int MINIMAL_LOW_PRESSURE_TRAINING_NORMATIVE = 3;
    //Normative daily activity in minutes of minimal cardio training.
    private static final int DAILY_CARDIO_TRAINING_MINUTES_NORMATIVE = 30;
    //Normative daily activity in minutes of low blood pressure training.
    private static final int DAILY_LOW_PRESSURE_TRAINING_MINUTES_NORMATIVE = 40;
    //Minutes in the day.
    private static final int MINUTES_PER_DAY = 24 * 60;
    //Counter for DAILY_CARDIO_TRAINING_MINUTES_NORMATIVE.
    double weekly_cardio_activity_counter;
    //Counter for DAILY_LOW_PRESSURE_TRAINING_MINUTES_NORMATIVE.
    double weekly_pressure_activity_counter;

    public void run() {
        getRecommendation();
    }

    private void getRecommendation() {
        setDailyActivity();
        resumeActivity(weekly_cardio_activity_counter, weekly_pressure_activity_counter);
    }
    /* Allows inputting a weekly activity from user and proceed counters. */
    private void setDailyActivity() {
        int day = 1; //days counter.
        while (day <= DAYS_IN_WEEK) {
            double daily_activity_time = readDouble("How many minutes did you" +
                    " do on " +
                    "day " + day + " ?");
            while (daily_activity_time < 0 || daily_activity_time > MINUTES_PER_DAY) {
                daily_activity_time = readDouble("Enter appropriate value");
            }
            if (daily_activity_time >= DAILY_CARDIO_TRAINING_MINUTES_NORMATIVE) {
                weekly_cardio_activity_counter++;
            }
            if (daily_activity_time >= DAILY_LOW_PRESSURE_TRAINING_MINUTES_NORMATIVE) {
                weekly_pressure_activity_counter++;
            }
            day++;
        }
    }

    /** Allows outputting a result of activity in a form of recommendation
     * message.
     * @param cardio - counter of up normative cardio training activity.
     * @param pressure - counter of up normative low blood pressure training
     activity. */
    private void resumeActivity(double cardio, double pressure) {
        if (cardio >= MINIMAL_CARDIO_TRAINING_NORMATIVE) {
            println("Cardiovascular health:");
            println("Great job! You've done enough exercise for cardiovascular health.");
        } else {
            println("Cardiovascular health:");
            println("You needed to train hard for at least " + (int)(MINIMAL_CARDIO_TRAINING_NORMATIVE - cardio) + " more day(s) a week!");
        }
        if (pressure >= MINIMAL_LOW_PRESSURE_TRAINING_NORMATIVE) {
            println("Blood pressure:");
            println("Great job! You've done enough exercise to keep a low blood pressure.");
        } else {
            println("Blood pressure:");
            println("You needed to train hard for at least " + (int)(MINIMAL_LOW_PRESSURE_TRAINING_NORMATIVE - pressure) + " more day(s) a week!");
        }
    }
}
