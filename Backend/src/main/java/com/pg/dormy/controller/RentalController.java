package com.pg.dormy.controller;

import com.pg.dormy.DTO.RentalDTO;
import com.pg.dormy.DTO.RentalRequestDTO;
import com.pg.dormy.DTO.RentalResponseDTO;
import com.pg.dormy.DTO.RentalSearchCriteria;
import com.pg.dormy.Exception.ResourceNotFoundException;
import com.pg.dormy.Exception.UnauthorizedException;
import com.pg.dormy.service.RentalService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/rentals")
public class RentalController {

    private final RentalService rentalService;

    @Autowired
    public RentalController(RentalService rentalService) {
        this.rentalService = rentalService;
    }

    @PostMapping
    public ResponseEntity<?> createRental(
            @RequestHeader("User-Id") Integer userId,
            @Valid @RequestBody RentalRequestDTO request) {
        try {
            RentalResponseDTO response = rentalService.createRental(userId, request);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body("Error creating rental: " + e.getMessage());
        }
    }

    @PutMapping("/{rentalId}")
    public ResponseEntity<?> updateRental(
            @RequestHeader("User-Id") Integer userId,
            @PathVariable Integer rentalId,
            @Valid @RequestBody RentalRequestDTO request) {
        try {
            RentalResponseDTO response = rentalService.updateRental(userId, rentalId, request);
            return ResponseEntity.ok(response);
        } catch (UnauthorizedException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body("Error updating rental: " + e.getMessage());
        }
    }

    @DeleteMapping("/{rentalId}")
    public ResponseEntity<?> deleteRental(
            @RequestHeader("User-Id") Integer userId,
            @PathVariable Integer rentalId) {
        try {
            rentalService.deleteRental(userId, rentalId);
            return ResponseEntity.ok().build();
        } catch (UnauthorizedException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body("Error deleting rental: " + e.getMessage());
        }
    }

    @GetMapping("/user")
    public ResponseEntity<?> getUserRentals(
            @RequestHeader("User-Id") Integer userId) {
        try {
            List<RentalResponseDTO> response = rentalService.getRentalsByUserId(userId);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body("Error fetching user rentals: " + e.getMessage());
        }
    }

    @GetMapping("/{rentalId}")
    public ResponseEntity<?> getRentalById(
            @PathVariable Integer rentalId) {
        try {
            RentalResponseDTO response = rentalService.getRentalById(rentalId);
            return ResponseEntity.ok(response);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body("Error fetching rental: " + e.getMessage());
        }
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