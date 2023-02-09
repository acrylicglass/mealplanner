package com.hita.mealplanner.service;

import com.hita.mealplanner.model.Meal;
import com.hita.mealplanner.repository.MealRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MealService {

    @Autowired
    MealRepository mealRepository;

    // CREATE
    public Meal createMeal(Meal meal) {
        return mealRepository.save(meal);
    }

    // READ meals per user
    public List<Meal> getMealByUser(Long userId) {
        return mealRepository.findByUserId(userId);
    }

    // READ meals per ID
    public Optional<Meal> getMealById(Long mealId) {
        return mealRepository.findById(mealId);
    }

    // DELETE
    public void deleteMeal(Long id) {
        mealRepository.deleteById(id);
    }

    // UPDATE
    public Meal updateMeal(Long mealId, Meal meal) {
        Meal mealToBeUpdated = mealRepository.findById(mealId).get();
        mealToBeUpdated.setName(meal.getName());
        return mealRepository.save(mealToBeUpdated);
    }
}
