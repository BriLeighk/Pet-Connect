package edu.wisconsin.databaseclass.pet_connect.entities;

public class Pet {

    private Long petId;
    private String name;
    private String type;
    private String breed;
    private int age;
    private String gender;
    private String size;
    private String color;
    private String healthStatus;
    private String vaccinationStatus;
    private String adoptionStatus;
    private String description;
    private int photoAmount;
    private int videoAmount;
    private String furLength;
    private boolean dewormed;
    private boolean sterilized;
    private String maturitySize;
    private double fee;

    // Getters and setters

    public Long getPetId() {
        return petId;
    }

    public void setPetId(Long petId) {
        this.petId = petId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getHealthStatus() {
        return healthStatus;
    }

    public void setHealthStatus(String healthStatus) {
        this.healthStatus = healthStatus;
    }

    public String getVaccinationStatus() {
        return vaccinationStatus;
    }

    public void setVaccinationStatus(String vaccinationStatus) {
        this.vaccinationStatus = vaccinationStatus;
    }

    public String getAdoptionStatus() {
        return adoptionStatus;
    }

    public void setAdoptionStatus(String adoptionStatus) {
        this.adoptionStatus = adoptionStatus;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPhotoAmount() {
        return photoAmount;
    }

    public void setPhotoAmount(int photoAmount) {
        this.photoAmount = photoAmount;
    }

    public int getVideoAmount() {
        return videoAmount;
    }

    public void setVideoAmount(int videoAmount) {
        this.videoAmount = videoAmount;
    }

    public String getFurLength() {
        return furLength;
    }

    public void setFurLength(String furLength) {
        this.furLength = furLength;
    }

    public boolean isDewormed() {
        return dewormed;
    }

    public void setDewormed(boolean dewormed) {
        this.dewormed = dewormed;
    }

    public boolean isSterilized() {
        return sterilized;
    }

    public void setSterilized(boolean sterilized) {
        this.sterilized = sterilized;
    }

    public String getMaturitySize() {
        return maturitySize;
    }

    public void setMaturitySize(String maturitySize) {
        this.maturitySize = maturitySize;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }
  
}
