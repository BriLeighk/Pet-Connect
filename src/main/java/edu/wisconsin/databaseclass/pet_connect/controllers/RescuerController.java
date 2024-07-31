package edu.wisconsin.databaseclass.pet_connect.controllers;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import edu.wisconsin.databaseclass.pet_connect.entities.User;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class RescuerController {

    private static final Logger logger = LoggerFactory.getLogger(RescuerController.class);

    @GetMapping("/rescuerDashboard")
    public String showRescuerDashboard(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null || user.getRescuer() == null) {
            return "redirect:/login"; // Redirect to login if user is not in session or not a rescuer
        }
        Integer rescuerId = user.getRescuer().getRescuerId();
        logger.info("Rescuer ID: " + rescuerId);
        model.addAttribute("rescuerId", rescuerId);
        model.addAttribute("user", user); // Add user to the model
        if (user.getProfileImage() == null) {
            model.addAttribute("profileImage", "/images/profile-placeholder.png");
        } else {
            model.addAttribute("profileImage", "/profileImage/" + user.getUserId());
        }
        return "rescuerDashboard";
    }
}