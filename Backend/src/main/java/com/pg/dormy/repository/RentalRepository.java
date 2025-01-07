package com.pg.dormy.repository;

import com.pg.dormy.entity.Rental;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;

public interface RentalRepository extends JpaRepository<Rental, Integer> {
    @Query("SELECT r FROM Rental r " +
            "WHERE (:rentalArea IS NULL OR " +
            "LOWER(r.rentalArea) LIKE LOWER(CONCAT('%', :rentalArea, '%'))) " +
            "AND (:roomType IS NULL OR r.roomType = :roomType) " +
            "AND (:tenantType IS NULL OR r.tenantType = :tenantType) " +
            "AND (:minRent IS NULL OR r.expectedRent >= :minRent) " +
            "AND (:maxRent IS NULL OR r.expectedRent <= :maxRent)")
    Page<Rental> searchRentals(
            @Param("rentalArea") String rentalArea,
            @Param("roomType") String roomType,
            @Param("tenantType") String tenantType,
            @Param("minRent") BigDecimal minRent,
            @Param("maxRent") BigDecimal maxRent,
            Pageable pageable
    );
}
