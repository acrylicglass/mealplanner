package com.hita.mealplanner.service;

import com.hita.mealplanner.managers.PlanManager;
import com.hita.mealplanner.model.Meal;
import com.hita.mealplanner.model.Plan;
import com.hita.mealplanner.repository.MealRepository;
import com.hita.mealplanner.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlanService {

    PlanManager planManager = new PlanManager();

    @Autowired
    MealRepository mealRepository;

    @Autowired
    UserRepository userRepository;

    // READ meals per user
    private List<Meal> getMealByUser(Long userId) {
        return mealRepository.findByUserId(userId);
    }

    public String getUserById(Long userId) {
        return userRepository.findById(userId).toString();
    }

    public String createPlan(Plan plan) throws Exception {
        List meals = getMealByUser(plan.getUserId());
        return planManager.planJsonConvertor(meals);
    }
}
