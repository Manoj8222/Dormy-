package com.pg.dormy.service;

import com.pg.dormy.DTO.RentalDTO;
import com.pg.dormy.DTO.RentalSearchCriteria;
import com.pg.dormy.entity.Rental;
import com.pg.dormy.repository.RentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.yaml.snakeyaml.events.StreamEndEvent;

import java.math.BigDecimal;

@Service
@Transactional
public class RentalServiceImpl implements RentalService {

    private final RentalRepository rentalRepository;

    @Autowired
    public RentalServiceImpl(RentalRepository rentalRepository) {
        this.rentalRepository = rentalRepository;
    }

    @Override
    public Page<RentalDTO> searchRentals(String rentalArea, String roomType, String tenantType, BigDecimal minRent, BigDecimal maxRent, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("availableFrom").descending());

        Page<Rental> rentals = rentalRepository.searchRentals(
                rentalArea,
                roomType,
                tenantType,
                minRent,
                maxRent,
                pageable
        );

        return rentals.map(this::convertToDTO);
    }

    private RentalDTO convertToDTO(Rental rental) {
        RentalDTO dto = new RentalDTO();

        dto.setRentalId(rental.getRentalId());
        dto.setRoomType(rental.getRoomType());
        dto.setTenantType(rental.getTenantType());
        dto.setPropertyType(rental.getPropertyType());
        dto.setBhkType(rental.getBhkType());
        dto.setPropertySize(rental.getPropertySize());
        dto.setFacing(rental.getFacing());
        dto.setPropertyAge(rental.getPropertyAge());
        dto.setAvailableFrom(rental.getAvailableFrom());
        dto.setVacancyFloor(rental.getVacancyFloor());
        dto.setTotalFloor(rental.getTotalFloor());
        dto.setRentalCity(rental.getRentalCity());
        dto.setRentalArea(rental.getRentalArea());
        dto.setLocationStreet(rental.getLocationStreet());
        dto.setMapLink(rental.getMapLink());
        dto.setDirectionTip(rental.getDirectionTip());
        dto.setExpectedRent(rental.getExpectedRent());
        dto.setNegotiable(rental.getNegotiable());
        dto.setExpectedDeposit(rental.getExpectedDeposit());
        dto.setMonthlyMaintenance(rental.getMonthlyMaintenance());
        dto.setFurnishing(rental.getFurnishing());
        dto.setParking(rental.getParking());
        dto.setRoomDetails(rental.getRoomDetails());
        dto.setPropertyRules(rental.getPropertyRules());
        dto.setPropertyDescription(rental.getPropertyDescription());
        dto.setAvailableDaySchedule(rental.getAvailableDaySchedule());
        dto.setAvailableTimeSchedule(rental.getAvailableTimeSchedule());
        return dto;
    }
}
