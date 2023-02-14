package com.hita.mealplanner.managers;

import com.google.gson.Gson;
import com.hita.mealplanner.model.Meal;

import java.util.*;

public class PlanManager {

    int totalItems;
    String dayOfTheWeek;

    public String planJsonConvertor(List meals) throws Exception {
        if (meals.size()>=14) {
            LinkedHashMap plan = planGenerator(meals);
            Gson gson = new Gson();
            return gson.toJson(plan);
        }
        else {
            return ("Not enough unique meals found in the database");
        }
    }

    private LinkedHashMap planGenerator (List meals) throws Exception {
        Random rand = new Random();
        if(meals.size() < 14) {
            throw new Exception("Not enough meals for the user");
        }
        if(meals.size() >14) {
            totalItems = 14;
        }
        LinkedHashMap map = new LinkedHashMap();
        int totalDays = 7;
        //get elements without repetitions
        for (int i =0; i< totalDays; i++) {
            switch (i) {
                case 0:
                    dayOfTheWeek = "Sunday";
                    break;
                case 1:
                    dayOfTheWeek = "Monday";
                    break;
                case 2:
                    dayOfTheWeek = "Tuesday";
                    break;
                case 3:
                    dayOfTheWeek = "Wednesday";
                    break;
                case 4:
                    dayOfTheWeek = "Thursday";
                    break;
                case 5:
                    dayOfTheWeek = "Friday";
                    break;
                case 6:
                    dayOfTheWeek = "Saturday";
                    break;
            }
            int mealIndex1 = rand.nextInt(meals.size());
            String meal1 = ((Meal) meals.get(mealIndex1)).getDishName();
            meals.remove(mealIndex1);

            int mealIndex2 = rand.nextInt(meals.size());
            String meal2 = ((Meal) meals.get(mealIndex2)).getDishName();
            meals.remove(mealIndex2);
            String[] mealArray = {meal1, meal2};

            map.put(dayOfTheWeek, mealArray);

        }
        return map;
    }
}
