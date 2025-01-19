package com.pg.dormy.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
public class PgRoomResponseDTO {
    private Integer pgRoomId;
    private String occupancyType;
    private Boolean roomAvailability;
    private BigDecimal rent;
    private String roomAmenities;

    public PgRoomResponseDTO() {
    }

    public PgRoomResponseDTO(Integer pgRoomId, String occupancyType, Boolean roomAvailability, BigDecimal rent, String roomAmenities) {
        this.pgRoomId = pgRoomId;
        this.occupancyType = occupancyType;
        this.roomAvailability = roomAvailability;
        this.rent = rent;
        this.roomAmenities = roomAmenities;
    }

    public Integer getPgRoomId() {
        return pgRoomId;
    }

    public void setPgRoomId(Integer pgRoomId) {
        this.pgRoomId = pgRoomId;
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