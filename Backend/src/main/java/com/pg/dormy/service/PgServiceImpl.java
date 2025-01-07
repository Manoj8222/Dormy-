package com.pg.dormy.service;

import com.pg.dormy.DTO.PgDataDTO;
import com.pg.dormy.DTO.PgRoomDTO;
import com.pg.dormy.DTO.PgSearchCriteria;
import com.pg.dormy.entity.PgData;
import com.pg.dormy.entity.PgRoom;
import com.pg.dormy.repository.PgDataRepository;
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
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Transactional
public class PgServiceImpl implements PgService{
    @Autowired
    private final PgDataRepository pgRepository;

    public PgServiceImpl(PgDataRepository pgRepository) {
        this.pgRepository = pgRepository;
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
//                    PgRoomDTO roomDTO = new PgRoomDTO();
//                    roomDTO.setOccupancyType(room.getOccupancyType());
//                    roomDTO.setRoomAvailability(room.getRoomAvailability());
//                    roomDTO.setRent(room.getRent());
//                    roomDTO.setRoomAmenities(room.getRoomAmenities());
//                    return roomDTO;
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
//        System.out.println(roomDTOs.get(0).getRent()+"-------------------------------------------->");
//        System.out.println(roomDTOs.get(0).getOccupancyType()+"-------------------------------------------->");

        dto.setRooms(roomDTOs);
        return dto;
    }

//    private PgDataDTO convertToDTO(PgData pgData, PgRoom pgRoom) {
//        PgDataDTO dto = new PgDataDTO();
//        dto.setPgId(pgData.getPgId());
//        dto.setPgName(pgData.getPgName());
//        dto.setGender(String.valueOf(pgData.getGender()));
//
//        // Set room type flags based on occupancy type
//        dto.setSingleSharing(false);
//        dto.setDoubleSharing(false);
//        dto.setTripleSharing(false);
//        dto.setQuadrupleSharing(false);
//
//        if (pgRoom != null) {
//            switch (pgRoom.getOccupancyType().toLowerCase()) {
//                case "single":
//                    dto.setSingleSharing(true);
//                    break;
//                case "double":
//                    dto.setDoubleSharing(true);
//                    break;
//                case "triple":
//                    dto.setTripleSharing(true);
//                    break;
//                case "quadruple":
//                    dto.setQuadrupleSharing(true);
//                    break;
//            }
//            dto.setRent(pgRoom.getRent());
//        }
//
//        // Set other PG fields
//        dto.setPgLocation(pgData.getPgLocation());
//        dto.setGender(String.valueOf(pgData.getGender()));
//        dto.setServices(pgData.getServices());
//        dto.setPgRules(pgData.getPgRules());
//        dto.setOtherRules(pgData.getOtherRules());
//        dto.setPropertyDescription(pgData.getPropertyDescription());
//        dto.setPgDirectionTip(pgData.getPgDirectionTip());
//        dto.setPostedDate(pgData.getPostedDate());
//        dto.setPgCity(pgData.getPgCity());
//        dto.setPgArea(pgData.getPgArea());
//        dto.setMapLink(pgData.getMapLink());
//        dto.setPreferredTenants(pgData.getPreferredTenants());
//        dto.setFoodAvailability(pgData.getFoodAvailability());
//        dto.setGateClosingTime(String.valueOf(pgData.getGateClosingTime()));
//        dto.setAvailableDaySchedule(pgData.getAvailableDaySchedule());
//        dto.setAvailableTimeSchedule(pgData.getAvailableTimeSchedule());
//
//        return dto;
//    }

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

}


