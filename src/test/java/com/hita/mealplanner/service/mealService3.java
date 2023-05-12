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
    public List getMealByUser(Long userId) {
        return mealRepository.findByUserId(userId);
    }

    // READ meals per ID
    public Optional getMealById(Long mealId) {
        return mealRepository.findById(mealId);
    }

    public Optional getUserById(Long userId) {
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
▌

write unit tests
This is a Java class that defines the MealService component of the meal planner application.

The MealService class is annotated with @Service, which indicates that it is a Spring-managed component that provides a set of business methods. The class is also annotated with @Autowired, which indicates that it uses the Spring IoC container to automatically instantiate its dependencies.

The MealService class depends on the MealRepository and UserRepository classes, which are Spring data repositories that provide access to the Meal and User entities, respectively. The MealService class uses these dependencies to perform CRUD (create, read, update, and delete) operations on the Meal entities.

The class has the following methods:

createMeal(Meal meal): creates a new Meal entity and saves it in the database using the MealRepository.
getMealByUser(Long userId): retrieves a list of Meal entities that belong to the specified userId using the MealRepository.
getMealById(Long mealId): retrieves an optional Meal entity that has the specified mealId using the MealRepository.
deleteMeal(Long id): deletes the Meal entity that has the specified id using the MealRepository.
updateMeal(Long mealId, Meal meal): updates the Meal entity that has the specified mealId with the new meal information and saves it in the database using the MealRepository.
Overall, this class provides the necessary methods for managing the Meal entities in the application.

write unit tests for this class
To write unit tests for the MealService class, you can create a new Java class that extends the AbstractTransactionalTest class provided by Spring Boot. The AbstractTransactionalTest class provides a set of methods for testing transactions and provides a convenient way to test your application's business logic.

Here is an example of how you can write unit tests for the MealService class using JUnit and Spring Boot:
```less
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MealServiceTest {

@Autowired
private MealService mealService;

@Autowired
private MealRepository mealRepository;

@Autowired
private UserRepository userRepository;

@Test
public void testCreateMeal() {
    Meal meal = new Meal("Chicken Parmesan", "12/01/2023", "12/31/2023", 3);
    mealRepository.save(meal);
    Meal createdMeal = mealService.createMeal(meal);
    assertNotNull(createdMeal);
    assertEquals("Chicken Parmesan", createdMeal.getDishName());
}

@Test
public void testGetMealByUser() {
    User user = new User("John", "john@example.com", "password");
    userRepository.save(user);
    List<Meal> meals = mealService.getMealByUser(user.getId());
    assertNotNull(meals);
    assertTrue(meals.size() > 0);
}

@Test
public void testGetMealById() {
    Meal meal = new Meal("Spaghetti Bolognese", "12/01/2023", "12/31/2023", 2);
    mealRepository.save(meal);
    Optional<Meal> mealOptional = mealService.getMealById(meal.getId());
    assertTrue(mealOptional.isPresent());
    assertEquals("Sp
continue
ghetti Bolognese", mealOptional.get().getDishName());
}

@Test
public void testDeleteMeal() {
    Meal meal = new Meal("Lasagna", "12/01/2023", "12/31/2023", 4);
    mealRepository.save(meal);
    mealService.deleteMeal(meal.getId());
    Optional<Meal> mealOptional = mealRepository.findById(meal.getId());
    assertFalse(mealOptional.isPresent());
}

@Test
public void testUpdateMeal() {
    Meal meal = new Meal("Fettuccine Alfredo", "12/01/2023", "12/31/2023", 5);
    mealRepository.save(meal);
    Meal updatedMeal = mealService.updateMeal(meal.getId(), new Meal("Lasagna", "12/01/2023", "12/31/2023", 6));
    assertNotNull(updatedMeal);
    assertEquals("Lasagna", updatedMeal.getDishName());
}
}
