package com.pg.dormy.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "rental")
public class Rental {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rental_id")
    private Integer rentalId;

    @Column(name = "room_type", nullable = false, length = 50)
    private String roomType;

    @Column(name = "tenant_type", nullable = false, length = 50)
    private String tenantType;

    @Column(name = "property_type", nullable = false, length = 50)
    private String propertyType;

    @Column(name = "bhk_type", nullable = false, length = 20)
    private String bhkType;

    @Column(name = "property_size")
    private Integer propertySize;

    @Column(name = "facing", length = 50)
    private String facing;

    @Column(name = "property_age")
    private Integer propertyAge;

    @Column(name = "available_from")
    @Temporal(TemporalType.DATE)
    private Date availableFrom;

    @Column(name = "vacancy_floor")
    private Integer vacancyFloor;

    @Column(name = "total_floor")
    private Integer totalFloor;

    @Column(name = "rental_city", nullable = false)
    private String rentalCity;

    @Column(name = "rental_area", nullable = false)
    private String rentalArea;

    @Column(name = "location_street", nullable = false)
    private String locationStreet;

    @Column(name = "map_link")
    private String mapLink;

    @Column(name = "direction_tip", columnDefinition = "TEXT")
    private String directionTip;

    @Column(name = "expected_rent", nullable = false, precision = 10, scale = 2)
    private BigDecimal expectedRent;

    @Column(name = "negotiable")
    private Boolean negotiable;

    @Column(name = "expected_deposit", nullable = false, precision = 10, scale = 2)
    private BigDecimal expectedDeposit;

    @Column(name = "monthly_maintenance", precision = 10, scale = 2)
    private BigDecimal monthlyMaintenance;

    @Column(name = "furnishing", length = 50)
    private String furnishing;

    @Column(name = "parking", length = 50)
    private String parking;

    @Column(name = "room_details", columnDefinition = "TEXT")
    private String roomDetails;

    @Column(name = "property_rules", columnDefinition = "TEXT")
    private String propertyRules;

    @Column(name = "property_description", columnDefinition = "TEXT")
    private String propertyDescription;

    @Column(name = "image_1")
    private String image1;

    @Column(name = "image_2")
    private String image2;

    @Column(name = "image_3")
    private String image3;

    @Column(name = "image_4")
    private String image4;

    @Column(name = "available_day_schedule", length = 100)
    private String availableDaySchedule;

    @Column(name = "available_time_schedule", length = 100)
    private String availableTimeSchedule;

    // Getters and setters


    public Integer getRentalId() {
        return rentalId;
    }

    public void setRentalId(Integer rentalId) {
        this.rentalId = rentalId;
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

    public String getMapLink() {
        return mapLink;
    }

    public void setMapLink(String mapLink) {
        this.mapLink = mapLink;
    }

    public String getDirectionTip() {
        return directionTip;
    }

    public void setDirectionTip(String directionTip) {
        this.directionTip = directionTip;
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

    public String getImage4() {
        return image4;
    }

    public void setImage4(String image4) {
        this.image4 = image4;
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
