package edu.wisconsin.databaseclass.pet_connect.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import edu.wisconsin.databaseclass.pet_connect.entities.User;
import edu.wisconsin.databaseclass.pet_connect.entities.Pet;
import edu.wisconsin.databaseclass.pet_connect.entities.Rescuer;
import edu.wisconsin.databaseclass.pet_connect.repositories.UserRepository;
import edu.wisconsin.databaseclass.pet_connect.repositories.PetRepository;
import edu.wisconsin.databaseclass.pet_connect.repositories.RescuerRepository;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class UserService {
    private static final Logger logger = Logger.getLogger(UserService.class.getName());

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private RescuerRepository rescuerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Used my Message and Favorites to get user records from MySQL database via userId
    public User getUserById(int userId) {
        return userRepository.findById(userId).orElse(null);
    }

    // saves user record to the MySQL database
    public User saveUser(User user, String rawPassword) {
        if (rawPassword != null && !passwordEncoder.matches(rawPassword, user.getPassword())) {
            user.setPassword(passwordEncoder.encode(rawPassword)); // hash password and store in User table
        }
        return userRepository.save(user);
    }

    public User updateUser(User user) {
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

    // Get profile image from MySQL database
    public byte[] getProfileImage(int userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        return userOptional.map(User::getProfileImage).orElse(null);
    }

    // Delete profile image from MySQL database
    public void deleteProfileImage(int userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setProfileImage(null);
            userRepository.save(user);
        }
    }

    public void saveProfileImage(int userId, MultipartFile file) throws IOException {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setProfileImage(file.getBytes());
            userRepository.save(user);
        }
    }

    public boolean emailExists(String email) {
        return userRepository.findByEmail(email) != null;
    }

    public boolean convertToRescuer(User user, int rescuerId) {
        Rescuer rescuer = rescuerRepository.findById(rescuerId).orElse(null);
        if (rescuer == null || rescuer.getUser() != null) {
            return false; // Invalid rescuerId or already associated
        }
        user.setRescuer(rescuer);
        rescuer.setUser(user);
        userRepository.save(user);
        rescuerRepository.save(rescuer);
        return true;
    }

    public List<Pet> getPetsByRescuer(Rescuer rescuerId) {
        return petRepository.findByRescuer(rescuerId);
    }
}