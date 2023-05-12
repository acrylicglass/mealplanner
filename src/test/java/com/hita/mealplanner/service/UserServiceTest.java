package com.hita.mealplanner.service;

import com.hita.mealplanner.model.User;
import com.hita.mealplanner.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetUser() {
        User user = new User();
        user.setId(1L);
        user.setName("Test User");

        when(userRepository.findById(any(Long.class))).thenReturn(Optional.of(user));

        Optional<User> retrievedUser = userService.getUser(1L);

        assertEquals("Test User", retrievedUser.get().getName());
    }
}
