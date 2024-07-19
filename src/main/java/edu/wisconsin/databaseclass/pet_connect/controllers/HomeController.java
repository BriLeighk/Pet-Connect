package edu.wisconsin.databaseclass.pet_connect.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("message", "Welcome to Pet Connect!");
        return "home";
    }

    @GetMapping("/home")
    public String homePage() {
        return "home";
    }
}

