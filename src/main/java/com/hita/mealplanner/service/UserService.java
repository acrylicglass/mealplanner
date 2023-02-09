package com.hita.mealplanner.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hita.mealplanner.model.User;
import com.hita.mealplanner.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    // CREATE
    public User createUser(User user) {
        return userRepository.save(user);
    }

    // READ
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // READ 1
    public Optional<User> getUser(Long id) {
        return userRepository.findById(id);
    }

    // DELETE
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    // UPDATE
    public User updateUser(Long userId, User user) {
        User usrToBeUpdated = userRepository.findById(userId).get();
        usrToBeUpdated.setName(user.getName());
        return userRepository.save(usrToBeUpdated);
    }
}
