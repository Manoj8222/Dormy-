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
    @JoinColumn(name = "user_id")
    private User userData;

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
}
