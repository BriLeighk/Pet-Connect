package edu.wisconsin.databaseclass.pet_connect.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import edu.wisconsin.databaseclass.pet_connect.entities.Location;
import edu.wisconsin.databaseclass.pet_connect.services.LocationService;

@Controller
public class LocationController {

    @Autowired
    private LocationService locationService;

    @GetMapping("/locations")
    public String getAllLocations(Model model) {
        model.addAttribute("locations", locationService.getAllLocations());
        return "locations";
    }

    @PostMapping("/location")
    public String saveLocation(@RequestParam("state") String state, @RequestParam("longitude") double longitude,
                               @RequestParam("latitude") double latitude) {
        Location location = new Location();
        location.setState(state);
        location.setLongitude(longitude);
        location.setLatitude(latitude);
        locationService.saveLocation(location);
        return "redirect:/locations";
    }
}

