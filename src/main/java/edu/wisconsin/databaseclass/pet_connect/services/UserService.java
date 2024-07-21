package edu.wisconsin.databaseclass.pet_connect.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import edu.wisconsin.databaseclass.pet_connect.entities.User;
import edu.wisconsin.databaseclass.pet_connect.repositories.UserRepository;

import java.util.logging.Logger;

@Service
public class UserService {
    private static final Logger logger = Logger.getLogger(UserService.class.getName());

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Used my Message and Favorites to get user records from MySQL database via userId
    public User getUserById(int userId) {
        return userRepository.findById(userId).orElse(null);
    }

    // saves user record to the MySQL database
    public User saveUser(User user, String rawPassword) {
        user.setPassword(passwordEncoder.encode(rawPassword)); // hash password and store in User table
        return userRepository.save(user);
    }

    public void deleteUser(int userId) {
        userRepository.deleteById(userId);
    }

    public User findByEmail(String email) {
        logger.info("Finding user by email: " + email);
        User user = userRepository.findByEmail(email);
        if (user != null) {
            logger.info("User found: " + user.getEmail());
        } else {
            logger.info("User not found with email: " + email);
        }
        return user;
    }
}
