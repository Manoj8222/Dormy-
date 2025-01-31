package com.pg.dormy.DTO;

import java.math.BigDecimal;
import java.util.Date;

public class RentalResponseDTO {
    private Integer rentalId;
    private Integer userId;
    private String roomType;
    private String tenantType;
    private String propertyType;
    private String bhkType;
    private Integer propertySize;
    private String facing;
    private Integer propertyAge;
    private Date availableFrom;
    private Integer vacancyFloor;
    private Integer totalFloor;
    private String rentalCity;
    private String rentalArea;
    private String locationStreet;
    private BigDecimal expectedRent;
    private Boolean negotiable;
    private BigDecimal expectedDeposit;
    private BigDecimal monthlyMaintenance;
    private String furnishing;
    private String parking;
    private String roomDetails;
    private String propertyRules;
    private String propertyDescription;
    private String availableDaySchedule;
    private String availableTimeSchedule;

    public RentalResponseDTO() {
    }

    public RentalResponseDTO(Integer rentalId, Integer userId, String roomType, String tenantType, String propertyType, String bhkType, Integer propertySize, String facing, Integer propertyAge, Date availableFrom, Integer vacancyFloor, Integer totalFloor, String rentalCity, String rentalArea, String locationStreet, BigDecimal expectedRent, Boolean negotiable, BigDecimal expectedDeposit, BigDecimal monthlyMaintenance, String furnishing, String parking, String roomDetails, String propertyRules, String propertyDescription, String availableDaySchedule, String availableTimeSchedule) {
        this.rentalId = rentalId;
        this.userId = userId;
        this.roomType = roomType;
        this.tenantType = tenantType;
        this.propertyType = propertyType;
        this.bhkType = bhkType;
        this.propertySize = propertySize;
        this.facing = facing;
        this.propertyAge = propertyAge;
        this.availableFrom = availableFrom;
        this.vacancyFloor = vacancyFloor;
        this.totalFloor = totalFloor;
        this.rentalCity = rentalCity;
        this.rentalArea = rentalArea;
        this.locationStreet = locationStreet;
        this.expectedRent = expectedRent;
        this.negotiable = negotiable;
        this.expectedDeposit = expectedDeposit;
        this.monthlyMaintenance = monthlyMaintenance;
        this.furnishing = furnishing;
        this.parking = parking;
        this.roomDetails = roomDetails;
        this.propertyRules = propertyRules;
        this.propertyDescription = propertyDescription;
        this.availableDaySchedule = availableDaySchedule;
        this.availableTimeSchedule = availableTimeSchedule;
    }

    public Integer getRentalId() {
        return rentalId;
    }

    public void setRentalId(Integer rentalId) {
        this.rentalId = rentalId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public String getTenantType() {
        return tenantType;
    }

    public void setTenantType(String tenantType) {
        this.tenantType = tenantType;
    }

    public String getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }

    public String getBhkType() {
        return bhkType;
    }

    public void setBhkType(String bhkType) {
        this.bhkType = bhkType;
    }

    public Integer getPropertySize() {
        return propertySize;
    }

    public void setPropertySize(Integer propertySize) {
        this.propertySize = propertySize;
    }

    public String getFacing() {
        return facing;
    }

    public void setFacing(String facing) {
        this.facing = facing;
    }

    public Integer getPropertyAge() {
        return propertyAge;
    }

    public void setPropertyAge(Integer propertyAge) {
        this.propertyAge = propertyAge;
    }

    public Date getAvailableFrom() {
        return availableFrom;
    }

    public void setAvailableFrom(Date availableFrom) {
        this.availableFrom = availableFrom;
    }

    public Integer getVacancyFloor() {
        return vacancyFloor;
    }

    public void setVacancyFloor(Integer vacancyFloor) {
        this.vacancyFloor = vacancyFloor;
    }

    public Integer getTotalFloor() {
        return totalFloor;
    }

    public void setTotalFloor(Integer totalFloor) {
        this.totalFloor = totalFloor;
    }

    public String getRentalCity() {
        return rentalCity;
    }

    public void setRentalCity(String rentalCity) {
        this.rentalCity = rentalCity;
    }

    public String getRentalArea() {
        return rentalArea;
    }

    public void setRentalArea(String rentalArea) {
        this.rentalArea = rentalArea;
    }

    public String getLocationStreet() {
        return locationStreet;
    }

    public void setLocationStreet(String locationStreet) {
        this.locationStreet = locationStreet;
    }

    public BigDecimal getExpectedRent() {
        return expectedRent;
    }

    public void setExpectedRent(BigDecimal expectedRent) {
        this.expectedRent = expectedRent;
    }

    public Boolean getNegotiable() {
        return negotiable;
    }

    public void setNegotiable(Boolean negotiable) {
        this.negotiable = negotiable;
    }

    public BigDecimal getExpectedDeposit() {
        return expectedDeposit;
    }

    public void setExpectedDeposit(BigDecimal expectedDeposit) {
        this.expectedDeposit = expectedDeposit;
    }

    public BigDecimal getMonthlyMaintenance() {
        return monthlyMaintenance;
    }

    public void setMonthlyMaintenance(BigDecimal monthlyMaintenance) {
        this.monthlyMaintenance = monthlyMaintenance;
    }

    public String getFurnishing() {
        return furnishing;
    }

    public void setFurnishing(String furnishing) {
        this.furnishing = furnishing;
    }

    public String getParking() {
        return parking;
    }

    public void setParking(String parking) {
        this.parking = parking;
    }

    public String getRoomDetails() {
        return roomDetails;
    }

    public void setRoomDetails(String roomDetails) {
        this.roomDetails = roomDetails;
    }

    public String getPropertyRules() {
        return propertyRules;
    }

    public void setPropertyRules(String propertyRules) {
        this.propertyRules = propertyRules;
    }

    public String getPropertyDescription() {
        return propertyDescription;
    }

    public void setPropertyDescription(String propertyDescription) {
        this.propertyDescription = propertyDescription;
    }

    public String getAvailableDaySchedule() {
        return availableDaySchedule;
    }

    public void setAvailableDaySchedule(String availableDaySchedule) {
        this.availableDaySchedule = availableDaySchedule;
    }

    public String getAvailableTimeSchedule() {
        return availableTimeSchedule;
    }

    public void setAvailableTimeSchedule(String availableTimeSchedule) {
        this.availableTimeSchedule = availableTimeSchedule;
    }
}