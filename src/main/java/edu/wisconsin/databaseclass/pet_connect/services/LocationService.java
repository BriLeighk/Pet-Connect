package edu.wisconsin.databaseclass.pet_connect.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.wisconsin.databaseclass.pet_connect.entities.Location;
import edu.wisconsin.databaseclass.pet_connect.repositories.LocationRepository;

import java.util.List;

@Service
public class LocationService {

    @Autowired
    private LocationRepository locationRepository;

    public List<Location> getAllLocations() {
        return locationRepository.findAll();
    }

    public Location getLocationById(int locationId) {
        return locationRepository.findById(locationId).orElse(null);
    }

    public Location saveLocation(Location location) {
        return locationRepository.save(location);
    }

    public void deleteLocation(int locationId) {
        locationRepository.deleteById(locationId);
    }
}

