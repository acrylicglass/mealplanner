package com.hita.mealplanner.service;

import com.hita.mealplanner.model.Meal;
import com.hita.mealplanner.model.User;
import com.hita.mealplanner.repository.MealRepository;
import com.hita.mealplanner.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MealService {

    @Autowired
    MealRepository mealRepository;

    @Autowired
    UserRepository userRepository;

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

    public Optional<User> getUserById(Long userId) {
        return userRepository.findById(userId);
    }

    // DELETE
    public void deleteMeal(Long id) {
        mealRepository.deleteById(id);
    }

    // UPDATE
    public Meal updateMeal(Long mealId, Meal meal) {
        Meal mealToBeUpdated = mealRepository.findById(mealId).get();
        mealToBeUpdated.setDishName(meal.getDishName());
        return mealRepository.save(mealToBeUpdated);
    }
}
