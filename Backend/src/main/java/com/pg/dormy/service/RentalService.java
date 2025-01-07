package com.pg.dormy.service;

import com.pg.dormy.DTO.RentalDTO;
import com.pg.dormy.DTO.RentalSearchCriteria;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;

public interface RentalService {
    Page<RentalDTO> searchRentals(String rentalArea, String roomType, String tenantType, BigDecimal minRent,BigDecimal maxRent, int page, int size);
}
