package edu.wisconsin.databaseclass.pet_connect.entities;

import jakarta.persistence.*;
import edu.wisconsin.databaseclass.pet_connect.repositories.LocationRepository;
import edu.wisconsin.databaseclass.pet_connect.repositories.RescuerRepository;
import edu.wisconsin.databaseclass.pet_connect.repositories.BreedRepository;
import edu.wisconsin.databaseclass.pet_connect.repositories.ColorRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;

// Model for the Pet Entity
@Entity
@Table(name = "pet")
public class Pet {

    private static final Logger logger = LoggerFactory.getLogger(Pet.class);

    @Id
    private String petId; // Change to String to accommodate hash IDs

    @Column
    private String name;

    @Column(nullable = false)
    private int breed1;

    @Column
    private Integer breed2;

    @Column(nullable = false)
    private int type;

    @Column(nullable = false)
    private int maturitySize;

    @Column(nullable = false)
    private int age;

    @Column(nullable = false)
    private int gender;

    @Column(nullable = false)
    private String adoptionStatus = "Available";

    @Column(nullable = false)
    private int color1;

    @Column
    private Integer color2;

    @Column
    private Integer color3;

    @Column(nullable = false)
    private int healthStatus;

    @Column(nullable = false)
    private int vaccinationStatus;

    @Column(nullable = false)
    private int dewormed;

    @Column(nullable = false)
    private int sterilized;

    @Column(nullable = false)
    private double fee;

    @Column(nullable = false)
    private int furLength;

    @Column(nullable = false)
    private int videoAmount = 0;

    @Column(nullable = false)
    private int photoAmount = 0;

    @Lob
    @Column(name = "photos", columnDefinition = "LONGBLOB")
    private byte[] photos;

    @ManyToOne
    @JoinColumn(name = "location_id", nullable = true)
    private Location location;

    @ManyToOne
    @JoinColumn(name = "rescuer_id")
    private Rescuer rescuer;

    @Transient
    private String breed1Name;

    @Transient
    private String breed2Name;

    @Column
    private String description;

    @Transient
    private double longitude;

    @Transient
    private double latitude;

    // getters and setters

    public String getPetId() {
        return petId;
    }

    public void setPetId(String petId) {
        this.petId = petId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBreed1() {
        return breed1;
    }

    public void setBreed1(int breed1) {
        this.breed1 = breed1;
    }

    public Integer getBreed2() {
        return breed2;
    }

    public void setBreed2(Integer breed2) {
        this.breed2 = breed2;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getMaturitySize() {
        return maturitySize;
    }

    public void setMaturitySize(int maturitySize) {
        this.maturitySize = maturitySize;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getAdoptionStatus() {
        return adoptionStatus;
    }

    public void setAdoptionStatus(String adoptionStatus) {
        this.adoptionStatus = adoptionStatus;
    }

    public int getColor1() {
        return color1;
    }

    public void setColor1(int color1) {
        this.color1 = color1;
    }

    public Integer getColor2() {
        return color2;
    }

    public void setColor2(Integer color2) {
        this.color2 = color2;
    }

    public Integer getColor3() {
        return color3;
    }

    public void setColor3(Integer color3) {
        this.color3 = color3;
    }

    public int getHealthStatus() {
        return healthStatus;
    }

    public void setHealthStatus(int healthStatus) {
        this.healthStatus = healthStatus;
    }

    public int getVaccinationStatus() {
        return vaccinationStatus;
    }

    public void setVaccinationStatus(int vaccinationStatus) {
        this.vaccinationStatus = vaccinationStatus;
    }

    public int getDewormed() {
        return dewormed;
    }

    public void setDewormed(int dewormed) {
        this.dewormed = dewormed;
    }

    public int getSterilized() {
        return sterilized;
    }

    public void setSterilized(int sterilized) {
        this.sterilized = sterilized;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    public int getFurLength() {
        return furLength;
    }

    public void setFurLength(int furLength) {
        this.furLength = furLength;
    }

    public int getVideoAmount() {
        return videoAmount;
    }

    public void setVideoAmount(int videoAmount) {
        this.videoAmount = videoAmount;
    }

    public int getPhotoAmount() {
        return photoAmount;
    }

    public void setPhotoAmount(int photoAmount) {
        this.photoAmount = photoAmount;
    }

    public byte[] getPhotos() {
        return photos;
    }

    public void setPhotos(byte[] photos) {
        this.photos = photos;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Rescuer getRescuer() {
        return rescuer;
    }

    public void setRescuer(Rescuer rescuer) {
        this.rescuer = rescuer;
    }

    public String getBreed1Name() {
        return breed1Name;
    }

    public void setBreed1Name(String breed1Name) {
        this.breed1Name = breed1Name;
    }

    public String getBreed2Name() {
        return breed2Name;
    }

    public void setBreed2Name(String breed2Name) {
        this.breed2Name = breed2Name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    // New method to set fields directly from CSV values
    public void setFieldsFromCsv(String[] values, LocationRepository locationRepository, RescuerRepository rescuerRepository, BreedRepository breedRepository, ColorRepository colorRepository) {
        try {
            this.type = Integer.parseInt(values[0]); // int 1 or 2
            this.name = values[1]; // String
            this.age = Integer.parseInt(values[2]); // int (months)
            
            this.breed1 = Integer.parseInt(values[3]); // int (corresponding breed ID)
            
            // Validate breed2
            if (!values[4].isEmpty()) {
                int breed2Id = Integer.parseInt(values[4]);
                if (breed2Id == 0) {
                    this.breed2 = null;
                } else if (breedRepository.existsById(breed2Id)) {
                    this.breed2 = breed2Id;
                } else {
                    throw new IllegalArgumentException("Invalid breed2 ID: " + breed2Id);
                }
            } else {
                this.breed2 = null;
            }
            
            this.gender = Integer.parseInt(values[5]); // int 1, 2, or 3
            
            this.color1 = Integer.parseInt(values[6]);
            
            // Validate color2
            if (!values[7].isEmpty()) {
                int color2Id = Integer.parseInt(values[7]);
                if (color2Id == 0) {
                    this.color2 = null;
                } else if (colorRepository.existsById(color2Id)) {
                    this.color2 = color2Id;
                } else {
                    throw new IllegalArgumentException("Invalid color2 ID: " + color2Id);
                }
            } else {
                this.color2 = null;
            }
            
            // Validate color3
            if (!values[8].isEmpty()) {
                int color3Id = Integer.parseInt(values[8]);
                if (color3Id == 0) {
                    this.color3 = null;
                } else if (colorRepository.existsById(color3Id)) {
                    this.color3 = color3Id;
                } else {
                    throw new IllegalArgumentException("Invalid color3 ID: " + color3Id);
                }
            } else {
                this.color3 = null;
            }

            this.maturitySize = Integer.parseInt(values[9]); // int
            this.furLength = Integer.parseInt(values[10]); // int
            this.vaccinationStatus = Integer.parseInt(values[11]);
            this.dewormed = Integer.parseInt(values[12]);
            this.sterilized = Integer.parseInt(values[13]);
            this.healthStatus = Integer.parseInt(values[14]);
            
            this.fee = Double.parseDouble(values[15]);
            
            // Set location
            int locationId = Integer.parseInt(values[16]);
            logger.info("Checking location ID: {}", locationId);
            this.location = locationRepository.findById(locationId).orElseThrow(() -> new IllegalArgumentException("Invalid location ID: " + locationId));

            // Set rescuer
            String rescuerId = values[17];
            logger.info("Checking rescuer ID: {}", rescuerId);
            this.rescuer = rescuerRepository.findById(rescuerId).orElseThrow(() -> new IllegalArgumentException("Invalid rescuer ID: " + rescuerId));
            
            this.description = values[18]; 
            this.petId = values[19]; // Directly set the petId as a String

            this.photoAmount = values[20].isEmpty() ? 0 : Integer.parseInt(values[20]);
            this.videoAmount = values[21].isEmpty() ? 0 : Integer.parseInt(values[21]);

            // Load photo from file
            String photoFilePath = "src/main/resources/static/images/" + this.petId + "-1.jpg";
            try {
                this.photos = Files.readAllBytes(Paths.get(photoFilePath));
            } catch (IOException e) {
                logger.error("Error reading photo file for pet ID: {}", this.petId, e);
                this.photos = null;
            }

            logger.info("Fields set for pet ID: {}", this.petId);
        } catch (NumberFormatException e) {
            logger.error("Error parsing CSV values: {}", (Object) values, e);
            throw e;
        } catch (ArrayIndexOutOfBoundsException e) {
            logger.error("Error parsing CSV values: {}", (Object) values, e);
            throw e;
        }
    }


    @Override
    public String toString() {
        return "Pet{" +
                "petId=" + petId +
                ", name='" + name + '\'' +
                ", breed1=" + breed1 +
                ", breed2=" + breed2 +
                ", type=" + type +
                ", maturitySize=" + maturitySize +
                ", age=" + age +
                ", gender=" + gender +
                ", adoptionStatus='" + adoptionStatus + '\'' +
                ", color1=" + color1 +
                ", color2=" + color2 +
                ", color3=" + color3 +
                ", healthStatus=" + healthStatus +
                ", vaccinationStatus=" + vaccinationStatus +
                ", sterilized=" + sterilized +
                ", dewormed=" + dewormed +
                ", fee=" + fee +
                ", furLength=" + furLength +
                ", videoAmount=" + videoAmount +
                ", photoAmount=" + photoAmount +
                ", description='" + description + '\'' +
                ", location=" + location +
                ", rescuer=" + rescuer +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                '}';
    }
}