package com.pg.dormy.controller;

import com.pg.dormy.DTO.RentalDTO;
import com.pg.dormy.DTO.RentalSearchCriteria;
import com.pg.dormy.service.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/rentals")
public class RentalController {

    private final RentalService rentalService;

    @Autowired
    public RentalController(RentalService rentalService) {
        this.rentalService = rentalService;
    }

    @GetMapping("/search")
    public ResponseEntity<Map<String, Object>> searchRentals(
            @RequestParam(required = false) String rentalArea,
            @RequestParam(required = false) String roomType,
            @RequestParam(required = false) String tenantType,
            @RequestParam(required = false) BigDecimal minRent,
            @RequestParam(required = false) BigDecimal maxRent,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

//        RentalSearchCriteria criteria = new RentalSearchCriteria(
//                rentalArea, roomType, tenantType, minRent, maxRent
//        );

        Page<RentalDTO> rentalPage = rentalService.searchRentals(rentalArea, roomType, tenantType, minRent, maxRent, page, size);

        Map<String, Object> response = new HashMap<>();
        response.put("data", rentalPage.getContent());
        response.put("currentPage", rentalPage.getNumber());
        response.put("totalItems", rentalPage.getTotalElements());
        response.put("totalPages", rentalPage.getTotalPages());

        return ResponseEntity.ok(response);
    }
}