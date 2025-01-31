package com.pg.dormy.entity;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "need_roommate")
public class NeedRoommate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "roommate_id")
    private Integer roommateId;

    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User userData;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "room_city", nullable = false, length = 100)
    private String roomCity;


    @Column(name = "room_area")
    private String roomArea;

    @Column(name = "location", nullable = false, length = 100)
    private String location;

    @Column(name = "room_type", nullable = false, length = 50)
    private String roomType;

    @Column(name = "rent", nullable = false, precision = 10, scale = 2)
    private BigDecimal rent;

    @Column(name = "looking_for", nullable = false, length = 100)
    private String lookingFor;

    @Column(name = "property_highlights", columnDefinition = "TEXT")
    private String propertyHighlights;

    @Column(name = "image_1")
    private String image1;

    @Column(name = "image_2")
    private String image2;

    @Column(name = "image_3")
    private String image3;

    @Column(name = "property_amenities", columnDefinition = "TEXT")
    private String propertyAmenities;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "mobile_no_visibility")
    private Boolean mobileNoVisibility;

    // Getters and setters

    public Integer getRoommateId() {
        return roommateId;
    }

    public void setRoommateId(Integer roommateId) {
        this.roommateId = roommateId;
    }

    public User getUserData() {
        return userData;
    }

    public void setUserData(User userData) {
        this.userData = userData;
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
}
