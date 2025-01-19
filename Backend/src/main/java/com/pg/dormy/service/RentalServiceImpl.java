package com.pg.dormy.service;

import com.pg.dormy.DTO.RentalDTO;
import com.pg.dormy.DTO.RentalRequestDTO;
import com.pg.dormy.DTO.RentalResponseDTO;
import com.pg.dormy.Exception.ResourceNotFoundException;
import com.pg.dormy.Exception.UnauthorizedException;
import com.pg.dormy.entity.Rental;
import com.pg.dormy.repository.RentalRepository;
import com.pg.dormy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class RentalServiceImpl implements RentalService {

    private final RentalRepository rentalRepository;
    private final UserRepository userRepository;

    @Autowired
    public RentalServiceImpl(RentalRepository rentalRepository, UserRepository userRepository) {
        this.rentalRepository = rentalRepository;
        this.userRepository = userRepository;
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
        dto.setUserId(rental.getUser().getUserId());
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

    @Override
    public RentalResponseDTO createRental(Integer userId, RentalRequestDTO request) {
        validateRentalRequest(request);

        Rental rental = new Rental();
        rental.setUserId(userId);
        updateRentalFromRequest(rental, request);

        return convertToResponseDTO(rentalRepository.save(rental));
    }

    @Override
    public RentalResponseDTO updateRental(Integer userId, Integer rentalId, RentalRequestDTO request) {
        if (!rentalRepository.isOwner(rentalId, userId)) {
            throw new UnauthorizedException("Not authorized to update this rental");
        }

        validateRentalRequest(request);

        Rental rental = rentalRepository.findById(rentalId)
                .orElseThrow(() -> new ResourceNotFoundException("Rental not found"));

        updateRentalFromRequest(rental, request);
        return convertToResponseDTO(rentalRepository.save(rental));
    }

    @Override
    public void deleteRental(Integer userId, Integer rentalId) {
        if (!rentalRepository.isOwner(rentalId, userId)) {
            throw new UnauthorizedException("Not authorized to delete this rental");
        }

        rentalRepository.deleteById(rentalId);
    }

    @Override
    public List<RentalResponseDTO> getRentalsByUserId(Integer userId) {
        return rentalRepository.findByUserId(userId).stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public RentalResponseDTO getRentalById(Integer rentalId) {
        return convertToResponseDTO(rentalRepository.findById(rentalId)
                .orElseThrow(() -> new ResourceNotFoundException("Rental not found")));
    }

    private void validateRentalRequest(RentalRequestDTO request) {
        if (request.getRoomType() == null || request.getRoomType().trim().isEmpty()) {
            throw new IllegalArgumentException("Room type is required");
        }
        if (request.getExpectedRent() == null || request.getExpectedRent().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Valid expected rent is required");
        }
        // Add more validation as needed
    }

    private void updateRentalFromRequest(Rental rental, RentalRequestDTO request) {
        rental.setRoomType(request.getRoomType());
        rental.setTenantType(request.getTenantType());
        rental.setPropertyType(request.getPropertyType());
        rental.setBhkType(request.getBhkType());
        rental.setPropertySize(request.getPropertySize());
        rental.setFacing(request.getFacing());
        rental.setPropertyAge(request.getPropertyAge());
        rental.setAvailableFrom(request.getAvailableFrom());
        rental.setVacancyFloor(request.getVacancyFloor());
        rental.setTotalFloor(request.getTotalFloor());
        rental.setRentalCity(request.getRentalCity());
        rental.setRentalArea(request.getRentalArea());
        rental.setLocationStreet(request.getLocationStreet());
        rental.setExpectedRent(request.getExpectedRent());
        rental.setNegotiable(request.getNegotiable());
        rental.setExpectedDeposit(request.getExpectedDeposit());
        rental.setMonthlyMaintenance(request.getMonthlyMaintenance());
        rental.setFurnishing(request.getFurnishing());
        rental.setParking(request.getParking());
        rental.setRoomDetails(request.getRoomDetails());
        rental.setPropertyRules(request.getPropertyRules());
        rental.setPropertyDescription(request.getPropertyDescription());
        rental.setAvailableDaySchedule(request.getAvailableDaySchedule());
        rental.setAvailableTimeSchedule(request.getAvailableTimeSchedule());
    }

    private RentalResponseDTO convertToResponseDTO(Rental rental) {
        RentalResponseDTO dto = new RentalResponseDTO();
        dto.setRentalId(rental.getRentalId());
        dto.setUserId(rental.getUserId());
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
