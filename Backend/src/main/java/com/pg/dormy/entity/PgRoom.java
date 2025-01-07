package com.pg.dormy.entity;
import com.pg.dormy.model.OccupancyType;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.math.BigDecimal;
@Data
@Entity
@Table(name = "pg_room")
public class PgRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pg_room_id")
    private Integer pgRoomId;

    @Column(name = "pg_id", insertable = false, updatable = false)
    private Integer pgId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pg_id")
    private PgData pgData;

    @Column(name = "occupancy_type", nullable = false, length = 50)
    private String occupancyType;

    @Column(name = "room_availability")
    private Boolean roomAvailability;

    @Column(name = "rent", nullable = false, precision = 10, scale = 2)
    private BigDecimal rent;

    @Column(name = "room_amenities", columnDefinition = "TEXT")
    private String roomAmenities;

    public Integer getPgId() {
        return pgId;
    }

    public void setPgId(Integer pgId) {
        this.pgId = pgId;
    }

    public Integer getPgRoomId() {
        return pgRoomId;
    }

    public void setPgRoomId(Integer pgRoomId) {
        this.pgRoomId = pgRoomId;
    }

    public PgData getPgData() {
        return pgData;
    }

    public void setPgData(PgData pgData) {
        this.pgData = pgData;
    }

    public String getOccupancyType() {
        return occupancyType;
    }

    public void setOccupancyType(String occupancyType) {
        this.occupancyType = occupancyType;
    }

    public Boolean getRoomAvailability() {
        return roomAvailability;
    }

    public void setRoomAvailability(Boolean roomAvailability) {
        this.roomAvailability = roomAvailability;
    }

    public BigDecimal getRent() {
        return rent;
    }

    public void setRent(BigDecimal rent) {
        this.rent = rent;
    }

    public String getRoomAmenities() {
        return roomAmenities;
    }

    public void setRoomAmenities(String roomAmenities) {
        this.roomAmenities = roomAmenities;
    }
}
