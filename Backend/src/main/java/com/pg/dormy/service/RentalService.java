package com.pg.dormy.service;

import com.pg.dormy.DTO.RentalDTO;
import com.pg.dormy.DTO.RentalRequestDTO;
import com.pg.dormy.DTO.RentalResponseDTO;
import com.pg.dormy.DTO.RentalSearchCriteria;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;
import java.util.List;

public interface RentalService {
    Page<RentalDTO> searchRentals(String rentalArea, String roomType, String tenantType, BigDecimal minRent,BigDecimal maxRent, int page, int size);

    RentalResponseDTO createRental(Integer userId, RentalRequestDTO request);
    RentalResponseDTO updateRental(Integer userId, Integer rentalId, RentalRequestDTO request);
    void deleteRental(Integer userId, Integer rentalId);
    List<RentalResponseDTO> getRentalsByUserId(Integer userId);
    RentalResponseDTO getRentalById(Integer rentalId);
}
