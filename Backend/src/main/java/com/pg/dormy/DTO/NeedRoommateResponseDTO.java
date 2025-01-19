package com.pg.dormy.DTO;

import java.math.BigDecimal;

public class NeedRoommateResponseDTO {
    private Integer roommateId;
    private Integer userId;
    private String roomCity;
    private String roomArea;
    private String location;
    private String roomType;
    private BigDecimal rent;
    private String lookingFor;
    private String propertyHighlights;
    private String propertyAmenities;
    private String description;
    private Boolean mobileNoVisibility;
    private String image1;
    private String image2;
    private String image3;

    public NeedRoommateResponseDTO(Integer roommateId, Integer userId, String roomCity, String roomArea, String location, String roomType, BigDecimal rent, String lookingFor, String propertyHighlights, String propertyAmenities, String description, Boolean mobileNoVisibility, String image1, String image2, String image3) {
        this.roommateId = roommateId;
        this.userId = userId;
        this.roomCity = roomCity;
        this.roomArea = roomArea;
        this.location = location;
        this.roomType = roomType;
        this.rent = rent;
        this.lookingFor = lookingFor;
        this.propertyHighlights = propertyHighlights;
        this.propertyAmenities = propertyAmenities;
        this.description = description;
        this.mobileNoVisibility = mobileNoVisibility;
        this.image1 = image1;
        this.image2 = image2;
        this.image3 = image3;
    }

    public Integer getRoommateId() {
        return roommateId;
    }

    public void setRoommateId(Integer roommateId) {
        this.roommateId = roommateId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getRoomCity() {
        return roomCity;
    }

    public void setRoomCity(String roomCity) {
        this.roomCity = roomCity;
    }

    public String getRoomArea() {
        return roomArea;
    }

    public void setRoomArea(String roomArea) {
        this.roomArea = roomArea;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public BigDecimal getRent() {
        return rent;
    }

    public void setRent(BigDecimal rent) {
        this.rent = rent;
    }

    public String getLookingFor() {
        return lookingFor;
    }

    public void setLookingFor(String lookingFor) {
        this.lookingFor = lookingFor;
    }

    public String getPropertyHighlights() {
        return propertyHighlights;
    }

    public void setPropertyHighlights(String propertyHighlights) {
        this.propertyHighlights = propertyHighlights;
    }

    public String getPropertyAmenities() {
        return propertyAmenities;
    }

    public void setPropertyAmenities(String propertyAmenities) {
        this.propertyAmenities = propertyAmenities;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getMobileNoVisibility() {
        return mobileNoVisibility;
    }

    public void setMobileNoVisibility(Boolean mobileNoVisibility) {
        this.mobileNoVisibility = mobileNoVisibility;
    }

    public String getImage1() {
        return image1;
    }

    public void setImage1(String image1) {
        this.image1 = image1;
    }

    public String getImage2() {
        return image2;
    }

    public void setImage2(String image2) {
        this.image2 = image2;
    }

    public String getImage3() {
        return image3;
    }

    public void setImage3(String image3) {
        this.image3 = image3;
    }
}
