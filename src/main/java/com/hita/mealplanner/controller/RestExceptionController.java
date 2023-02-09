package com.hita.mealplanner.controller;

import com.hita.mealplanner.exception.MealNotFoundException;
import com.hita.mealplanner.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestExceptionController {

    @ExceptionHandler(value = UserNotFoundException.class)
    public ResponseEntity<Object> exception(UserNotFoundException exception) {
        return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = MealNotFoundException.class)
    public ResponseEntity<Object> exception(MealNotFoundException exception) {
        return new ResponseEntity<>("Meal not found", HttpStatus.NOT_FOUND);
    }
}
