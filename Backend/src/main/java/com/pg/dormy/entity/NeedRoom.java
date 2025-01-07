package com.pg.dormy.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
@Table(name = "need_room")
public class NeedRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_id")
    private Integer roomId;

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

    @Column(name = "self_highlights", columnDefinition = "TEXT")
    private String selfHighlights;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "mobile_no_visibility")
    private Boolean mobileNoVisibility;

    // Getters and setters
}
