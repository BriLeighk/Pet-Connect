package edu.wisconsin.databaseclass.pet_connect.entities;

import jakarta.persistence.*;

// Model for the Pet Entity
@Entity
@Table(name = "pet")
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int petId;

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
    private String adoptionStatus = "Available"; // only displays in edit modal (creating a pet profile assumes the pet needs to be adopted)

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
    private int sterilized;

    @Column(nullable = false)
    private int dewormed;

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
    @JoinColumn(name = "location_id", nullable = false)
    private Location location;

    @ManyToOne
    @JoinColumn(name = "rescuer_id", nullable = false)
    private Rescuer rescuer;

    @Transient
    private String breed1Name;

    @Transient
    private String breed2Name;

    @Column
    private String description;

    // getters and setters

    public int getPetId() {
        return petId;
    }

    public void setPetId(int petId) {
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

    public int getSterilized() {
        return sterilized;
    }

    public void setSterilized(int sterilized) {
        this.sterilized = sterilized;
    }

    public int getDewormed() {
        return dewormed;
    }

    public void setDewormed(int dewormed) {
        this.dewormed = dewormed;
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
}