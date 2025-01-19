package com.pg.dormy.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PgDataRequestDTO {
    private String pgName;
    private String gender;
    private String services;
    private String pgRules;
    private String otherRules;
    private String propertyDescription;
    private String pgDirectionTip;
    private String pgCity;
    private String pgArea;
    private String pgLocation;
    private String mapLink;
    private String preferredTenants;
    private String foodAvailability;
    private LocalTime gateClosingTime;
    private Date postedDate;
    private String availableDaySchedule;
    private String availableTimeSchedule;
    private List<PgRoomRequestDTO> rooms;

    public String getPgName() {
        return pgName;
    }

    public void setPgName(String pgName) {
        this.pgName = pgName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getServices() {
        return services;
    }

    public void setServices(String services) {
        this.services = services;
    }

    public String getPgRules() {
        return pgRules;
    }

    public void setPgRules(String pgRules) {
        this.pgRules = pgRules;
    }

    public String getOtherRules() {
        return otherRules;
    }

    public void setOtherRules(String otherRules) {
        this.otherRules = otherRules;
    }

    public String getPropertyDescription() {
        return propertyDescription;
    }

    public void setPropertyDescription(String propertyDescription) {
        this.propertyDescription = propertyDescription;
    }

    public String getPgDirectionTip() {
        return pgDirectionTip;
    }

    public void setPgDirectionTip(String pgDirectionTip) {
        this.pgDirectionTip = pgDirectionTip;
    }

    public String getPgCity() {
        return pgCity;
    }

    public void setPgCity(String pgCity) {
        this.pgCity = pgCity;
    }

    public String getPgArea() {
        return pgArea;
    }

    public void setPgArea(String pgArea) {
        this.pgArea = pgArea;
    }

    public String getPgLocation() {
        return pgLocation;
    }

    public void setPgLocation(String pgLocation) {
        this.pgLocation = pgLocation;
    }

    public String getMapLink() {
        return mapLink;
    }

    public void setMapLink(String mapLink) {
        this.mapLink = mapLink;
    }

    public String getPreferredTenants() {
        return preferredTenants;
    }

    public void setPreferredTenants(String preferredTenants) {
        this.preferredTenants = preferredTenants;
    }

    public String getFoodAvailability() {
        return foodAvailability;
    }

    public void setFoodAvailability(String foodAvailability) {
        this.foodAvailability = foodAvailability;
    }

    public LocalTime getGateClosingTime() {
        return gateClosingTime;
    }

    public void setGateClosingTime(LocalTime gateClosingTime) {
        this.gateClosingTime = gateClosingTime;
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

    public List<PgRoomRequestDTO> getRooms() {
        return rooms;
    }

    public void setRooms(List<PgRoomRequestDTO> rooms) {
        this.rooms = rooms;
    }

    public Date getPostedDate() {
        return postedDate;
    }

    public void setPostedDate(Date postedDate) {
        this.postedDate = postedDate;
    }
}
