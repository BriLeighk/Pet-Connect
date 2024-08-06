package edu.wisconsin.databaseclass.pet_connect.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import edu.wisconsin.databaseclass.pet_connect.entities.Color;
import edu.wisconsin.databaseclass.pet_connect.repositories.ColorRepository;

@Service
public class ColorService {

    @Autowired
    private ColorRepository colorRepository;

    public List<Color> getAllColors() {
        return colorRepository.findAll();
    }
}