package com.pg.dormy.service;

import com.pg.dormy.DTO.*;
import com.pg.dormy.Exception.ResourceNotFoundException;
import com.pg.dormy.Exception.UnauthorizedException;
import com.pg.dormy.entity.PgData;
import com.pg.dormy.entity.PgRoom;
import com.pg.dormy.repository.PgDataRepository;
import com.pg.dormy.repository.PgRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Transactional
public class PgServiceImpl implements PgService{
    @Autowired
    private final PgDataRepository pgRepository;
    private final PgRoomRepository pgRoomRepository;

    public PgServiceImpl(PgDataRepository pgRepository, PgRoomRepository pgRoomRepository) {
        this.pgRepository = pgRepository;
        this.pgRoomRepository = pgRoomRepository;
    }
    @Override
    public Page<PgDataDTO> getAllPgDataPaginated(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("postedDate").descending());
        Page<PgData> pgDataPage = pgRepository.findAllPgDataWithRooms(pageable);

        return pgDataPage.map(this::convertToDTO);
    }

    @Override
    public Page<PgDataDTO> searchPGs(String pgArea,
                                     String gender,
                                     Boolean hasSingle,
                                     Boolean hasDouble,
                                     Boolean hasTriple,
                                     Boolean hasQuadruple,
                                     BigDecimal minRent,
                                     BigDecimal maxRent, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("postedDate").descending());

        Page<PgData> pgDataPage = pgRepository.searchPGs(
                pgArea,
                gender,
                hasSingle,
                hasDouble,
                hasTriple,
                hasQuadruple,
                minRent,
                maxRent,
                pageable
        );

        if(hasSingle == null){
            hasSingle = false;
        }
        if(hasDouble == null){
            hasDouble = false;
        }
        if( hasTriple == null){
            hasTriple = false;
        }
        if(hasQuadruple == null){
            hasQuadruple = false;
        }


        Boolean finalHasSingle = hasSingle;
        Boolean finalHasDouble = hasDouble;
        Boolean finalHasTriple = hasTriple;
        Boolean finalHasQuadruple = hasQuadruple;
        return pgDataPage.map(pgData -> convertToDTO12(pgData, finalHasSingle, finalHasDouble, finalHasTriple,
                finalHasQuadruple, minRent, maxRent));
    }

    @Override
    public String getOwnerPhoneNumber(Integer pgId) {
        return pgRepository.getOwnerPhoneNumber(pgId);
    }
//    public Page<PgDataDTO> getAllPgDataPaginated(int page, int size) {
//        Pageable pageable = PageRequest.of(page, size, Sort.by("postedDate").descending());
//        Page<Object[]> pgDataPage = pgRepository.findAllPgDataWithRooms(pageable);
//
//        return pgDataPage.map(objects -> {
//            PgData pgData = (PgData) objects[0];
//            PgRoom pgRoom = objects[1] != null ? (PgRoom) objects[1] : null;
//            return convertToDTO(pgData, pgRoom);
//        });
//    }


    private PgDataDTO convertToDTO1(PgData pgData,boolean hasSingle,boolean hasDouble,boolean hasTriple,boolean hasQuadruple,BigDecimal minRent, BigDecimal maxRent) {
        PgDataDTO dto = new PgDataDTO();

        int size = pgData.getRooms().size();
//        dto.setRent(pgData.getRooms().get(size-1).getRent());
//
//        if(pgData.getRooms().get(size-1).getOccupancyType().equals("Quadruple")){
//            dto.setRent(pgData.getRooms().get(size-1).getRent());
//
//        }
        for (int i = 0; i < size; i++) {

//            System.out.println(pgData.getRooms().get(i).getPgId());
//            System.out.println(pgData.getRooms().get(i).getPgRoomId());
//            System.out.println(pgData.getRooms().get(i).getOccupancyType());
//            System.out.println(pgData.getRooms().get(i).getRoomAvailability());
//            System.out.println(pgData.getRooms().get(i).getRent());
//            System.out.println(dto.getRent());
//
//            System.out.println("---------------END----------------------");
//            int rent = 0;
            if (pgData.getRooms().get(i).getOccupancyType().equals("Triple")) {
                dto.setRent(pgData.getRooms().get(i).getRent());
//                break;
            } else if (pgData.getRooms().get(i).getOccupancyType().equals("Double")) {
                dto.setRent(pgData.getRooms().get(i).getRent());
//                break;
            }else if(pgData.getRooms().get(i).getOccupancyType().equals("Quadruple")){
                dto.setRent(pgData.getRooms().get(i).getRent());
                break;
            }else {
                dto.setRent(pgData.getRooms().get(i).getRent());
            }
        }

//        System.out.println(dto.getPgId());
        dto.setPgId(pgData.getPgId());
        dto.setPgName(pgData.getPgName());
        dto.setPgLocation(pgData.getPgLocation());
        dto.setGender(String.valueOf(pgData.getGender()));
        dto.setServices(pgData.getServices());
        dto.setPgRules(pgData.getPgRules());
        dto.setOtherRules(pgData.getOtherRules());
        dto.setPropertyDescription(pgData.getPropertyDescription());
        dto.setPgDirectionTip(pgData.getPgDirectionTip());
        dto.setPostedDate(pgData.getPostedDate());
        dto.setPgCity(pgData.getPgCity());
        dto.setPgArea(pgData.getPgArea());
        dto.setMapLink(pgData.getMapLink());
        dto.setPreferredTenants(pgData.getPreferredTenants());
        dto.setFoodAvailability(pgData.getFoodAvailability());
        dto.setGateClosingTime(String.valueOf(pgData.getGateClosingTime()));
        dto.setAvailableDaySchedule(pgData.getAvailableDaySchedule());
        dto.setAvailableTimeSchedule(pgData.getAvailableTimeSchedule());

        // Filter rooms based on parameters
        List<PgRoomDTO> filteredRooms = pgData.getRooms().stream()
                .filter(room -> {
                    boolean typeMatch = (!hasSingle && !hasDouble && !hasTriple && !hasQuadruple) || // no type filter
                            (hasSingle && room.getOccupancyType().equals("Single")) ||
                            (hasDouble && room.getOccupancyType().equals("Double")) ||
                            (hasTriple && room.getOccupancyType().equals("Triple")) ||
                            (hasQuadruple && room.getOccupancyType().equals("Quadruple"));

                    boolean rentMatch = (minRent == null || room.getRent().compareTo(minRent) >= 0) &&
                            (maxRent == null || room.getRent().compareTo(maxRent) <= 0);

                    return typeMatch && rentMatch;
                })
                .map(room -> {
                    PgRoomDTO roomDTO = new PgRoomDTO();
                    roomDTO.setOccupancyType(room.getOccupancyType());
                    roomDTO.setRoomAvailability(room.getRoomAvailability());
                    roomDTO.setRent(room.getRent());
                    roomDTO.setRoomAmenities(room.getRoomAmenities());
                    return roomDTO;
                })
                .collect(Collectors.toList());


        List<PgRoomDTO> allRooms = pgData.getRooms().stream()
                .filter(room -> {
                    boolean rentMatch = (minRent == null || room.getRent().compareTo(minRent) >= 0) &&
                            (maxRent == null || room.getRent().compareTo(maxRent) <= 0);
                    return rentMatch;
                })
                .map(room -> {
                    PgRoomDTO roomDTO = new PgRoomDTO();
                    roomDTO.setOccupancyType(room.getOccupancyType());
                    roomDTO.setRoomAvailability(room.getRoomAvailability());
                    roomDTO.setRent(room.getRent());
                    roomDTO.setRoomAmenities(room.getRoomAmenities());
                    return roomDTO;
                })
                .collect(Collectors.toList());


        dto.setRooms(allRooms);

        // Set minimum rent from filtered rooms
        dto.setRent(filteredRooms.stream()
                .map(PgRoomDTO::getRent)
                .min(BigDecimal::compareTo)
                .orElse(null));

        return dto;
    }


    private PgDataDTO convertToDTO(PgData pgData) {
        PgDataDTO dto = new PgDataDTO();

        int size = pgData.getRooms().size();
//        dto.setRent(pgData.getRooms().get(size-1).getRent());
//
//        if(pgData.getRooms().get(size-1).getOccupancyType().equals("Quadruple")){
//            dto.setRent(pgData.getRooms().get(size-1).getRent());
//
//        }
        for (int i = 0; i < size; i++) {
            if (pgData.getRooms().get(i).getOccupancyType().equals("Triple")) {
                dto.setRent(pgData.getRooms().get(i).getRent());
//                break;
            } else if (pgData.getRooms().get(i).getOccupancyType().equals("Double")) {
                dto.setRent(pgData.getRooms().get(i).getRent());
//                break;
            }else if(pgData.getRooms().get(i).getOccupancyType().equals("Quadruple")){
                dto.setRent(pgData.getRooms().get(i).getRent());
                break;
            }else {
                dto.setRent(pgData.getRooms().get(i).getRent());
            }
        }

//        System.out.println(dto.getPgId());
        dto.setPgId(pgData.getPgId());
        dto.setPgName(pgData.getPgName());
        dto.setPgLocation(pgData.getPgLocation());
        dto.setGender(String.valueOf(pgData.getGender()));
        dto.setServices(pgData.getServices());
        dto.setPgRules(pgData.getPgRules());
        dto.setOtherRules(pgData.getOtherRules());
        dto.setPropertyDescription(pgData.getPropertyDescription());
        dto.setPgDirectionTip(pgData.getPgDirectionTip());
        dto.setPostedDate(pgData.getPostedDate());
        dto.setPgCity(pgData.getPgCity());
        dto.setPgArea(pgData.getPgArea());
        dto.setMapLink(pgData.getMapLink());
        dto.setPreferredTenants(pgData.getPreferredTenants());
        dto.setFoodAvailability(pgData.getFoodAvailability());
        dto.setGateClosingTime(String.valueOf(pgData.getGateClosingTime()));
        dto.setAvailableDaySchedule(pgData.getAvailableDaySchedule());
        dto.setAvailableTimeSchedule(pgData.getAvailableTimeSchedule());

        List<PgRoomDTO> roomDTOs = pgData.getRooms().stream()
                .map(room -> {
                    PgRoomDTO roomDTO = new PgRoomDTO();
                    roomDTO.setOccupancyType(room.getOccupancyType());
                    roomDTO.setRoomAvailability(room.getRoomAvailability());
                    if(Objects.equals(room.getOccupancyType(), "Single")){
                        roomDTO.setRent(room.getRent());
                    } else if(Objects.equals(room.getOccupancyType(), "Double")){
                        roomDTO.setRent(room.getRent());
                    }else if(Objects.equals(room.getOccupancyType(), "Triple")){
                        roomDTO.setRent(room.getRent());
                    }else if(Objects.equals(room.getOccupancyType(), "Quadruple")){
                        roomDTO.setRent(room.getRent());
                    }
//                    roomDTO.setRent(room.getRent());
                    roomDTO.setRoomAmenities(room.getRoomAmenities());
                    return roomDTO;
                })
                .collect(Collectors.toList());
        dto.setRooms(roomDTOs);
        return dto;
    }

    private PgDataDTO convertToDTO12(PgData pgData, boolean hasSingle, boolean hasDouble,
                                   boolean hasTriple, boolean hasQuadruple, BigDecimal minRent, BigDecimal maxRent) {

        PgDataDTO dto = new PgDataDTO();
        dto.setPgId(pgData.getPgId());
        dto.setPgName(pgData.getPgName());
        dto.setGender(pgData.getGender());
        dto.setServices(pgData.getServices());
        dto.setPgRules(pgData.getPgRules());
        dto.setOtherRules(pgData.getOtherRules());
        dto.setPropertyDescription(pgData.getPropertyDescription());
        dto.setPgDirectionTip(pgData.getPgDirectionTip());
        dto.setPostedDate(pgData.getPostedDate());
        dto.setPgCity(pgData.getPgCity());
        dto.setPgArea(pgData.getPgArea());
        dto.setPgLocation(pgData.getPgLocation());
        dto.setMapLink(pgData.getMapLink());
        dto.setPreferredTenants(pgData.getPreferredTenants());
        dto.setFoodAvailability(pgData.getFoodAvailability());
        dto.setGateClosingTime(String.valueOf(pgData.getGateClosingTime()));
        dto.setAvailableDaySchedule(pgData.getAvailableDaySchedule());
        dto.setAvailableTimeSchedule(pgData.getAvailableTimeSchedule());

        List<PgRoom> rooms = pgRepository.findRoomsByPgId(pgData.getPgId());
        List<PgRoomDTO> allRooms = rooms.stream()
                .map(room -> {
                    PgRoomDTO roomDTO = new PgRoomDTO();
                    roomDTO.setOccupancyType(room.getOccupancyType());
                    roomDTO.setRoomAvailability(room.getRoomAvailability());
                    roomDTO.setRent(room.getRent());
                    roomDTO.setRoomAmenities(room.getRoomAmenities());
                    return roomDTO;
                })
                .collect(Collectors.toList());

        // Calculate minimum rent based on selected room types
        BigDecimal minimumRent = rooms.stream()
                .filter(room -> {
                    if (hasSingle && room.getOccupancyType().equals("Single")) return true;
                    if (hasDouble && room.getOccupancyType().equals("Double")) return true;
                    if (hasTriple && room.getOccupancyType().equals("Triple")) return true;
                    if (hasQuadruple && room.getOccupancyType().equals("Quadruple")) return true;
                    return !hasSingle && !hasDouble && !hasTriple && !hasQuadruple;
                })
                .filter(room -> minRent == null || room.getRent().compareTo(minRent) >= 0)
                .filter(room -> maxRent == null || room.getRent().compareTo(maxRent) <= 0)
                .map(PgRoom::getRent)
                .min(BigDecimal::compareTo)
                .orElse(null);

        dto.setRent(minimumRent);
        dto.setRooms(allRooms);

        return dto;
    }

    @Override
    public PgDataResponseDTO createPg(Integer userId, PgDataRequestDTO request) {
        validatePgRequest(request);

        PgData pgData = new PgData();
        pgData.setUserId(userId);
        updatePgDataFromRequest(pgData, request);

        PgData savedPg = pgRepository.save(pgData);

        if (request.getRooms() != null && !request.getRooms().isEmpty()) {
            for (PgRoomRequestDTO roomRequest : request.getRooms()) {
                validateRoomRequest(roomRequest);
                PgRoom room = new PgRoom();
                room.setPgData(savedPg);
                updateRoomFromRequest(room, roomRequest);
                pgRoomRepository.save(room);
//                System.out.println(roomRequest);
            }
        }

        return convertToPgDataResponse(pgRepository.findById(savedPg.getPgId())
                .orElseThrow(() -> new ResourceNotFoundException("PG not found")));
    }

    @Override
    public PgDataResponseDTO updatePg(Integer userId, Integer pgId, PgDataRequestDTO request) {
        if (!pgRepository.isOwner(pgId, userId)) {
            throw new UnauthorizedException("Not authorized to update this PG");
        }

        validatePgRequest(request);

        PgData pgData = pgRepository.findById(pgId)
                .orElseThrow(() -> new ResourceNotFoundException("PG not found"));

        updatePgDataFromRequest(pgData, request);
        return convertToPgDataResponse(pgRepository.save(pgData));
    }

    @Override
    public void deletePg(Integer userId, Integer pgId) {
        if (!pgRepository.isOwner(pgId, userId)) {
            throw new UnauthorizedException("Not authorized to delete this PG");
        }

        pgRoomRepository.deleteByPgId(pgId);
        pgRepository.deleteById(pgId);
    }

    @Override
    public PgRoomResponseDTO addRoom(Integer userId, Integer pgId, PgRoomRequestDTO request) {
        if (!pgRepository.isOwner(pgId, userId)) {
            throw new UnauthorizedException("Not authorized to add room to this PG");
        }

        validateRoomRequest(request);

        PgData pgData = pgRepository.findById(pgId)
                .orElseThrow(() -> new ResourceNotFoundException("PG not found"));

        PgRoom room = new PgRoom();
        room.setPgData(pgData);
        updateRoomFromRequest(room, request);

        return convertToPgRoomResponse(pgRoomRepository.save(room));
    }

    @Override
    public PgRoomResponseDTO updateRoom(Integer userId, Integer pgId, Integer roomId,
                                        PgRoomRequestDTO request) {
        if (!pgRepository.isOwner(pgId, userId)) {
            throw new UnauthorizedException("Not authorized to update this room");
        }

        validateRoomRequest(request);

        PgRoom room = pgRoomRepository.findById(roomId)
                .orElseThrow(() -> new ResourceNotFoundException("Room not found"));

        if (!room.getPgData().getPgId().equals(pgId)) {
            throw new IllegalArgumentException("Room does not belong to this PG");
        }

        updateRoomFromRequest(room, request);
        return convertToPgRoomResponse(pgRoomRepository.save(room));
    }

    @Override
    public void deleteRoom(Integer userId, Integer pgId, Integer roomId) {
        if (!pgRepository.isOwner(pgId, userId)) {
            throw new UnauthorizedException("Not authorized to delete this room");
        }

        PgRoom room = pgRoomRepository.findById(roomId)
                .orElseThrow(() -> new ResourceNotFoundException("Room not found"));

        if (!room.getPgData().getPgId().equals(pgId)) {
            throw new IllegalArgumentException("Room does not belong to this PG");
        }

        pgRoomRepository.deleteById(roomId);
    }

    @Override
    public List<PgDataResponseDTO> getPgsByUserId(Integer userId) {
        return pgRepository.findByUserId(userId).stream()
                .map(this::convertToPgDataResponse)
                .collect(Collectors.toList());
    }

    @Override
    public PgDataResponseDTO getPgById(Integer pgId) {
        return convertToPgDataResponse(pgRepository.findById(pgId)
                .orElseThrow(() -> new ResourceNotFoundException("PG not found")));
    }

    // Helper methods for validation and conversion
    private void validatePgRequest(PgDataRequestDTO request) {
        if (request.getPgName() == null || request.getPgName().trim().isEmpty()) {
            throw new IllegalArgumentException("PG name is required");
        }
        // Add other validation as needed
    }

    private void validateRoomRequest(PgRoomRequestDTO request) {
        if (request.getOccupancyType() == null || request.getOccupancyType().trim().isEmpty()) {
            throw new IllegalArgumentException("Occupancy type is required");
        }
        if (request.getRent() == null || request.getRent().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Valid rent amount is required");
        }
        // Add other validation as needed
    }

    private void updatePgDataFromRequest(PgData pgData, PgDataRequestDTO request) {
        pgData.setPgName(request.getPgName());
        pgData.setGender(request.getGender());
        pgData.setServices(request.getServices());
        pgData.setPgRules(request.getPgRules());
        pgData.setOtherRules(request.getOtherRules());
        pgData.setPropertyDescription(request.getPropertyDescription());
        pgData.setPgDirectionTip(request.getPgDirectionTip());
        pgData.setPgCity(request.getPgCity());
        pgData.setPgArea(request.getPgArea());
        pgData.setPgLocation(request.getPgLocation());
        pgData.setMapLink(request.getMapLink());
        pgData.setPreferredTenants(request.getPreferredTenants());
        pgData.setFoodAvailability(request.getFoodAvailability());
        pgData.setGateClosingTime(request.getGateClosingTime());
        pgData.setPostedDate(request.getPostedDate());
        pgData.setAvailableDaySchedule(request.getAvailableDaySchedule());
        pgData.setAvailableTimeSchedule(request.getAvailableTimeSchedule());
    }

    private void updateRoomFromRequest(PgRoom room, PgRoomRequestDTO request) {
        room.setOccupancyType(request.getOccupancyType());
        room.setRoomAvailability(request.getRoomAvailability());
        room.setRent(request.getRent());
        room.setRoomAmenities(request.getRoomAmenities());
    }

    private PgDataResponseDTO convertToPgDataResponse(PgData pgData) {
        PgDataResponseDTO response = new PgDataResponseDTO();
        response.setPgId(pgData.getPgId());
        response.setUserId(pgData.getUserId());
        response.setPgName(pgData.getPgName());
        response.setGender(pgData.getGender());
        response.setServices(pgData.getServices());
        response.setPgRules(pgData.getPgRules());
        response.setOtherRules(pgData.getOtherRules());
        response.setPropertyDescription(pgData.getPropertyDescription());
        response.setPgDirectionTip(pgData.getPgDirectionTip());
        response.setPgCity(pgData.getPgCity());
        response.setPgArea(pgData.getPgArea());
        response.setPgLocation(pgData.getPgLocation());
        response.setMapLink(pgData.getMapLink());
        response.setPreferredTenants(pgData.getPreferredTenants());
        response.setFoodAvailability(pgData.getFoodAvailability());
        response.setGateClosingTime(pgData.getGateClosingTime());
        response.setPostedDate(pgData.getPostedDate());
        response.setAvailableDaySchedule(pgData.getAvailableDaySchedule());
        response.setAvailableTimeSchedule(pgData.getAvailableTimeSchedule());

        List<PgRoomResponseDTO> rooms = pgRoomRepository.findByPgId(pgData.getPgId())
                .stream()
                .map(this::convertToPgRoomResponse)
                .collect(Collectors.toList());
//        System.out.println(rooms.get(0).getRent()+"-----"+rooms.get(0).getOccupancyType()+"---"+rooms.get(0).getRent()+"----"+rooms.get(0).getRoomAmenities()+"---"+rooms.get(0).getRoomAvailability());
        response.setRooms(rooms);
//        List<PgRoom> rooms = pgRoomRepository.findByPgId(pgData.getPgId());
//        System.out.println(rooms.get(0).getRent()+"----"+rooms.get(0).getPgId()+"-----"+rooms.get(0).getOccupancyType()+"---"+rooms.get(0).getRent()+"----"+rooms.get(0).getRoomAmenities()+"---"+rooms.get(0).getRoomAvailability());

        return response;
    }

    private PgRoomResponseDTO convertToPgRoomResponse(PgRoom room) {
        return new PgRoomResponseDTO(
                room.getPgRoomId(),
                room.getOccupancyType(),
                room.getRoomAvailability(),
                room.getRent(),
                room.getRoomAmenities()
        );
    }


}


