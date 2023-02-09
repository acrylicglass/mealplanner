package com.hita.mealplanner.controller;

import com.hita.mealplanner.exception.MealNotFoundException;
import com.hita.mealplanner.exception.UserNotFoundException;
import com.hita.mealplanner.model.Meal;
import com.hita.mealplanner.model.User;
import com.hita.mealplanner.service.MealService;
import com.hita.mealplanner.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MealController {

    @Autowired
    MealService mealService;
    UserService userService;

    @RequestMapping(value="/meals", method= RequestMethod.POST)
    public Meal createMeal(@RequestBody Meal meal) {
        if(!userService.getUser(meal.getUserId()).isPresent())throw new UserNotFoundException();
        return mealService.createMeal(meal);
    }

    @RequestMapping(value="/meals/{userId}", method=RequestMethod.GET)
    public List<Meal> getMealsByUsers(@PathVariable(value = "userId") Long id) {
        if(!userService.getUser(id).isPresent())throw new UserNotFoundException();
        return mealService.getMealByUser(id);
    }

    @RequestMapping(value="/meals/{mealId}", method=RequestMethod.PUT)
    public Meal updateMealById(@PathVariable(value = "mealId") Long id, @RequestBody Meal meal) {
        if(!mealService.getMealById(id).isPresent())throw new MealNotFoundException();
        return mealService.updateMeal(id, meal);
    }

    @RequestMapping(value="/meals/{mealId}", method=RequestMethod.DELETE)
    public void deleteMeals(@PathVariable(value = "mealId") Long id) {
        if(!mealService.getMealById(id).isPresent())throw new MealNotFoundException();
        mealService.deleteMeal(id);
    }
}
