package com.hita.mealplanner.controller;

import com.hita.mealplanner.exception.UserNotFoundException;
import com.hita.mealplanner.model.Plan;

import com.hita.mealplanner.service.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class PlanController {

    @Autowired
    PlanService planService;

    @RequestMapping(value="/mealplan", method= RequestMethod.POST)
    public String createMealPlan(@RequestBody Plan plan) throws Exception {
        Long userIdInRequest = plan.getUserId();
        if(planService.getUserById(userIdInRequest).isEmpty())throw new UserNotFoundException();
        return planService.createPlan(plan);
    }
}
