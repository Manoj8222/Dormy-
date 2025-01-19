package com.pg.dormy.DTO;

import java.math.BigDecimal;

public class NeedRoomRequestDTO {
    private String roomCity;
    private String roomArea;
    private String location;
    private String roomType;
    private BigDecimal rent;
    private String lookingFor;
    private String selfHighlights;
    private String description;
    private Boolean mobileNoVisibility;

    public NeedRoomRequestDTO() {
    }

    public NeedRoomRequestDTO(String roomCity, String roomArea, String location, String roomType, BigDecimal rent, String lookingFor, String selfHighlights, String description, Boolean mobileNoVisibility) {
        this.roomCity = roomCity;
        this.roomArea = roomArea;
        this.location = location;
        this.roomType = roomType;
        this.rent = rent;
        this.lookingFor = lookingFor;
        this.selfHighlights = selfHighlights;
        this.description = description;
        this.mobileNoVisibility = mobileNoVisibility;
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

    public String getSelfHighlights() {
        return selfHighlights;
    }

    public void setSelfHighlights(String selfHighlights) {
        this.selfHighlights = selfHighlights;
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
