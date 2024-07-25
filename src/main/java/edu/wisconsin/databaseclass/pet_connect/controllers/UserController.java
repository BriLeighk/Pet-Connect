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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.IOException;
import java.util.logging.Logger;

@Controller
public class UserController {
    private static final Logger logger = Logger.getLogger(UserController.class.getName());

    @Autowired
    private UserService userService; // access to userService where CRUD operation calls are performed

    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @GetMapping("/register") //mapping to register page
    public String showRegistrationForm() {
        return "register";
    }

    @GetMapping("/login") // mapping to login page
    public String loginRegistrationForm() {
        return "login";
    }

    @GetMapping("/dashboard") //mapping to dashboard page
    public String showDashboardForm(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login"; // Redirect to login if user is not in session
        }
        if (user.getProfileImage() == null) {
            model.addAttribute("profileImage", "/images/profile-placeholder.png");
        } else {
            model.addAttribute("profileImage", "/profileImage/" + user.getUserId());
        }
        model.addAttribute("user", user);
        return "dashboard";
    }


    @PostMapping("/register") // contents of user registration
    public String registerUser(@RequestParam("username") String username,
                               @RequestParam("email") String email,
                               @RequestParam("password") String password,
                               Model model) {
        logger.info("Registering user: " + email);
        
        if (userService.emailExists(email)) {
            model.addAttribute("errorMessage", "Account already exists with this email.");
            return "register";
        }
        
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
            session.setAttribute("user", user); // Store user in session
            return "redirect:/dashboard"; // redirects user to dashboard on successful login attempt
        } else {
            // on-screen error message on invalid login credentials
            model.addAttribute("errorMessage", "Invalid email or password. Please try again.");
            return "login";
        }
    }

    @GetMapping("/logout") 
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login?logout=true";
    }

    // Upload profile image to MySQL database call
    @PostMapping("/uploadProfileImage")
    public String handleFileUpload(@RequestParam("file") MultipartFile file,
                                   @RequestParam("userId") int userId,
                                   RedirectAttributes redirectAttributes) {
        try {
            userService.saveProfileImage(userId, file);
            redirectAttributes.addFlashAttribute("message", "You successfully uploaded the profile image.");
        } catch (IOException e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("message", "Failed to upload the profile image.");
        }
        return "redirect:/dashboard";
    }

    @GetMapping("/profileImage/{userId}")
    public ResponseEntity<byte[]> getProfileImage(@PathVariable int userId) {
        byte[] image = userService.getProfileImage(userId);
        if (image != null) {
            return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "image/jpeg").body(image);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/deleteProfileImage/{userId}")
    public String deleteProfileImage(@PathVariable int userId, RedirectAttributes redirectAttributes) {
        userService.deleteProfileImage(userId);
        redirectAttributes.addFlashAttribute("message", "Profile image deleted successfully.");
        return "redirect:/dashboard";
    }

    @PostMapping("/updateProfile")
    public String updateProfile(@RequestParam("userId") int userId,
                                @RequestParam("username") String username,
                                @RequestParam("file") MultipartFile file,
                                @RequestParam("clearImageFlag") boolean clearImageFlag,
                                RedirectAttributes redirectAttributes,
                                HttpSession session) {
        try {
            User user = userService.getUserById(userId);
            if (user != null) {
                user.setUsername(username);
                if (clearImageFlag) {
                    user.setProfileImage(null);
                } else if (!file.isEmpty()) {
                    user.setProfileImage(file.getBytes());
                }
                userService.updateUser(user); // Use the updateUser method to avoid re-encoding the password
                session.setAttribute("user", user); // Update the user in the session
                redirectAttributes.addFlashAttribute("message", "Profile updated successfully.");
            }
        } catch (IOException e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("message", "Failed to update profile.");
        }
        return "redirect:/dashboard";
    }
}