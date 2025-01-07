package com.pg.dormy.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RentalSearchCriteria {
    private String rentalArea;
    private String roomType;
    private String tenantType;
    private BigDecimal minRent;
    private BigDecimal maxRent;
}

