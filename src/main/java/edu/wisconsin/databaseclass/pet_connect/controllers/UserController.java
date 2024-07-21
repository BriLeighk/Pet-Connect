package edu.wisconsin.databaseclass.pet_connect.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import edu.wisconsin.databaseclass.pet_connect.entities.User;
import edu.wisconsin.databaseclass.pet_connect.services.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.logging.Logger;

@Controller
public class UserController {
    private static final Logger logger = Logger.getLogger(UserController.class.getName());

    @Autowired
    private UserService userService; // access to userService where CRUD operation calls are performed

    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @GetMapping("/register")
    public String showRegistrationForm() {
        return "register";
    }

    @PostMapping("/register") // contents of user registration
    public String registerUser(@RequestParam("username") String username,
                               @RequestParam("email") String email,
                               @RequestParam("password") String password) {
        logger.info("Registering user: " + email);
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        userService.saveUser(user, password); // call to save user in database (pass raw password to hash in Service file)
        logger.info("User registered successfully: " + email);
        return "redirect:/login"; // navigates user to login page on successful account creation
    }

    @PostMapping("/perform_login") // checks if validity of user login credentials (if record exists in database and if passwords match)
    public String loginUser(@RequestParam("email") String email,
                            @RequestParam("password") String password,
                            HttpSession session,
                            Model model) {
        User user = userService.findByEmail(email);
        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            return "redirect:/home"; // redirects user to homepage on successful login attempt
        } else {
            // on-screen error message on invalid login credentials
            model.addAttribute("errorMessage", "Invalid email or password. Please try again.");
            return "login";
        }
    }

    @GetMapping("/logout") // TODO: implement logout handling
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login?logout=true";
    }


}
