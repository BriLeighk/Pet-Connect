// CLASS TO CONVERT DATASET CSV S TO LISTS FOR ADDING IN DATABASE
package edu.wisconsin.databaseclass.pet_connect.utils;

import edu.wisconsin.databaseclass.pet_connect.entities.Breed;
import edu.wisconsin.databaseclass.pet_connect.entities.Color;
import edu.wisconsin.databaseclass.pet_connect.entities.Location;
import edu.wisconsin.databaseclass.pet_connect.entities.Rescuer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class CsvReader {

    private static final Random random = new Random();
    private static final Set<String> uniqueCoordinates = new HashSet<>();

    public static List<Breed> readBreedsCsv(String filePath) {
        List<Breed> breeds = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(CsvReader.class.getResourceAsStream(filePath)))) {
            String line;
            br.readLine(); // Skip header
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                Breed breed = new Breed();
                breed.setBreedId(Integer.parseInt(values[0]));
                breed.setType(Integer.parseInt(values[1]));
                breed.setName(values[2].replace("\"", ""));
                breeds.add(breed);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return breeds;
    }

    public static List<Color> readColorsCsv(String filePath) {
        List<Color> colors = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(CsvReader.class.getResourceAsStream(filePath)))) {
            String line;
            br.readLine(); // Skip header
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                Color color = new Color();
                color.setColorId(Integer.parseInt(values[0]));
                color.setName(values[1].replace("\"", ""));
                colors.add(color);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return colors;
    }

    public static List<Location> readLocationsCsv(String filePath) {
        List<Location> locations = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(CsvReader.class.getResourceAsStream(filePath)))) {
            String line;
            br.readLine(); // Skip header
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                Location location = new Location();
                location.setLocationId(Integer.parseInt(values[0]));
                location.setState(values[1].replace("\"", ""));
                if (values.length > 2) {
                    location.setLongitude(Double.parseDouble(values[2]));
                    location.setLatitude(Double.parseDouble(values[3]));
                } else {
                    double[] uniqueCoords = generateUniqueCoordinates();
                    location.setLongitude(uniqueCoords[0]);
                    location.setLatitude(uniqueCoords[1]);
                }
                locations.add(location);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return locations;
    }

    public static List<Rescuer> readRescuersCsv(String filePath) {
        List<Rescuer> rescuers = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(CsvReader.class.getResourceAsStream(filePath)))) {
            String line;
            br.readLine(); // Skip header
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                Rescuer rescuer = new Rescuer();
                rescuer.setRescuerId(values[0]);
                // Set other fields if necessary
                rescuers.add(rescuer);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rescuers;
    }

    private static double[] generateUniqueCoordinates() {
        double longitude, latitude;
        String coordKey;
        do {
            longitude = -180 + (180 - (-180)) * random.nextDouble();
            latitude = -90 + (90 - (-90)) * random.nextDouble();
            coordKey = longitude + "," + latitude;
        } while (uniqueCoordinates.contains(coordKey));
        uniqueCoordinates.add(coordKey);
        return new double[]{longitude, latitude};
    }
}