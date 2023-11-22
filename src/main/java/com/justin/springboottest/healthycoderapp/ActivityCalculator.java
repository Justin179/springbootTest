package com.justin.springboottest.healthycoderapp;

public class ActivityCalculator {

    private static final int WORKOUT_DURATION_MIN = 45;

    public static String rateActivityLevel(int weeklyCardioMin, int weeklyWorkoutSessions){

        if (weeklyCardioMin<0 || weeklyWorkoutSessions<0) {
            throw new RuntimeException();
        }

        int totalMinutes = weeklyCardioMin + weeklyWorkoutSessions*45;
        double avgDailyActivityMins = totalMinutes / 7.0;

        if (avgDailyActivityMins<20){
            return "bad";
        } else if (avgDailyActivityMins>=20 && avgDailyActivityMins<40){
            return "average";
        } else if (avgDailyActivityMins>=40){
            return "good";
        }

        return "";
    }

}
