package com.pg.dormy.entity;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "pg_data")
public class PgData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pg_id")
    private Integer pgId;

    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User userData;

    @Column(name = "pg_name", nullable = false, length = 100)
    private String pgName;

    @Column(name = "user_id")
    private Integer userId;


    @Column(name = "image_1")
    private String image1;

    @Column(name = "image_2")
    private String image2;

    @Column(name = "image_3")
    private String image3;

    @Column(name = "image_4")
    private String image4;

    @Column(name = "gender", nullable = false)
    private String gender;

    @Column(name = "services", columnDefinition = "TEXT")
    private String services;

    @Column(name = "pg_rules", columnDefinition = "TEXT")
    private String pgRules;

    @Column(name = "other_rules", columnDefinition = "TEXT")
    private String otherRules;

    @Column(name = "property_description", columnDefinition = "TEXT")
    private String propertyDescription;

    @Column(name = "pg_direction_tip", columnDefinition = "TEXT")
    private String pgDirectionTip;

    @Column(name = "posted_date")
    @Temporal(TemporalType.DATE)
    private Date postedDate;

    @Column(name = "pg_city", nullable = false)
    private String pgCity;

    @Column(name = "pg_area", nullable = false)
    private String pgArea;

    @Column(name = "pg_location", nullable = false)
    private String pgLocation;

    @Column(name = "map_link")
    private String mapLink;

    @Column(name = "preferred_tenants", length = 100)
    private String preferredTenants;

    @Column(name = "food_availability")
    private String foodAvailability;

    @Column(name = "gate_closing_time")
    @Temporal(TemporalType.TIME)
    private LocalTime gateClosingTime;

    @Column(name = "available_day_schedule", length = 100)
    private String availableDaySchedule;

    @Column(name = "available_time_schedule", length = 100)
    private String availableTimeSchedule;

    @OneToMany
    @JoinColumn(name = "pg_id")
    private List<PgRoom> rooms;

//    @OneToMany(mappedBy = "pg_data", fetch = FetchType.LAZY)
//    private List<PgRoom> rooms;

    public enum Gender {
        Male, Female, Any
    }

    public Integer getPgId() {
        return pgId;
    }

    public void setPgId(Integer pgId) {
        this.pgId = pgId;
    }

    public User getUserData() {
        return userData;
    }

    public void setUserData(User userData) {
        this.userData = userData;
    }

    public String getPgName() {
        return pgName;
    }

    public void setPgName(String pgName) {
        this.pgName = pgName;
    }

    public String getPgLocation() {
        return pgLocation;
    }

    public void setPgLocation(String pgLocation) {
        this.pgLocation = pgLocation;
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

    public String getGender() {
        return gender;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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

    public Date getPostedDate() {
        return postedDate;
    }

    public void setPostedDate(Date postedDate) {
        this.postedDate = postedDate;
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

    public List<PgRoom> getRooms() {
        return rooms;
    }

    public void setRooms(List<PgRoom> rooms) {
        this.rooms = rooms;
    }

    public String getAvailableTimeSchedule() {
        return availableTimeSchedule;
    }

    public void setAvailableTimeSchedule(String availableTimeSchedule) {
        this.availableTimeSchedule = availableTimeSchedule;
    }

}
