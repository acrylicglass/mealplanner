package com.hita.mealplanner.model;

import jakarta.persistence.Id;

public class Plan {

    @Id
    private Long id;

    private Long userId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long id) {
        this.userId = id;
    }
}
