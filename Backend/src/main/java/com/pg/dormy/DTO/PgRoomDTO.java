package com.pg.dormy.DTO;

import lombok.Data;
import lombok.Getter;

import java.math.BigDecimal;
@Data
@Getter
public class PgRoomDTO {
    private String occupancyType;
    private Boolean roomAvailability;
    private BigDecimal rent;
    private String roomAmenities;

    public void setOccupancyType(String occupancyType) {
        this.occupancyType = occupancyType;
    }

    public void setRoomAvailability(Boolean roomAvailability) {
        this.roomAvailability = roomAvailability;
    }

    public void setRent(BigDecimal rent) {
        this.rent = rent;
    }

    public void setRoomAmenities(String roomAmenities) {
        this.roomAmenities = roomAmenities;
    }

    public String getOccupancyType() {
        return occupancyType;
    }

    public Boolean getRoomAvailability() {
        return roomAvailability;
    }

    public BigDecimal getRent() {
        return rent;
    }

    public String getRoomAmenities() {
        return roomAmenities;
    }
}