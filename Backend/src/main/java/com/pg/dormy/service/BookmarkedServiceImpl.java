package com.pg.dormy.service;

import com.pg.dormy.DTO.BookmarkedDTO;
import com.pg.dormy.DTO.PgDataDTO;
import com.pg.dormy.DTO.PgRoomDTO;
import com.pg.dormy.DTO.RentalDTO;
import com.pg.dormy.entity.Bookmarked;
import com.pg.dormy.entity.PgData;
import com.pg.dormy.entity.Rental;
import com.pg.dormy.repository.BookmarkedRepository;
import com.pg.dormy.repository.PgDataRepository;
import com.pg.dormy.repository.RentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Transactional
public class BookmarkedServiceImpl implements BookmarkedService {

    private final BookmarkedRepository bookmarkedRepository;
    private final PgDataRepository pgDataRepository;
    private final RentalRepository rentalRepository;

    @Autowired
    public BookmarkedServiceImpl(BookmarkedRepository bookmarkedRepository,
                                 PgDataRepository pgDataRepository,
                                 RentalRepository rentalRepository) {
        this.bookmarkedRepository = bookmarkedRepository;
        this.pgDataRepository = pgDataRepository;
        this.rentalRepository = rentalRepository;
    }

    @Override
    public List<BookmarkedDTO> getBookmarksByUserId(Integer userId) {
        List<Bookmarked> bookmarks = bookmarkedRepository.findByUserId(userId);
        List<BookmarkedDTO> bookmarkedDTOs = new ArrayList<>();

        for (Bookmarked bookmark : bookmarks) {
            BookmarkedDTO dto = new BookmarkedDTO();
            dto.setBookmarkedId(bookmark.getBookmarkedId());

            if (bookmark.getPgId() != null) {
                PgData pgData = pgDataRepository.findById(bookmark.getPgId())
                        .orElse(null);
                if (pgData != null) {

                    dto.setPgData(convertToPgDTO(pgData));
                }
            }

            if (bookmark.getRentalId() != null) {
                Rental rental = rentalRepository.findById(bookmark.getRentalId())
                        .orElse(null);
                if (rental != null) {
                    dto.setRental(convertToRentalDTO(rental));
                }
            }

            bookmarkedDTOs.add(dto);
        }

        return bookmarkedDTOs;
    }

    @Override
    public void addBookmark(Integer userId, Integer pgId, Integer rentalId) {

        System.out.println(userId+"--------"+pgId+"-----------"+rentalId);
//         if (rentalId != null && pgId != null) {
            //adding both pg and rental to the database
            Bookmarked bookmark = new Bookmarked();
            bookmark.setUserId(userId);
            bookmark.setPgId(pgId);
            bookmark.setRentalId(rentalId);
            bookmarkedRepository.save(bookmark);

//            Bookmarked bookmark1 = new Bookmarked();
//            bookmark.setUserId(userId);
//            bookmark.setPgId(pgId);
//            bookmark.setRentalId(null);
//            bookmarkedRepository.save(bookmark1);
//        }else if(pgId == null){
//            Bookmarked bookmark = new Bookmarked();
//            bookmark.setUserId(userId);
//            bookmark.setPgId(null);
//            bookmark.setRentalId(rentalId);
//            bookmarkedRepository.save(bookmark);
//        } else if (rentalId == null) {
//            Bookmarked bookmark = new Bookmarked();
//            bookmark.setUserId(userId);
//            bookmark.setPgId(pgId);
//            bookmark.setRentalId(null);
//            bookmarkedRepository.save(bookmark);
//        }
    }

    @Override
    public void removeBookmark(Integer userId, Integer pgId, Integer rentalId) {
        bookmarkedRepository.deleteBookmark (userId, pgId, rentalId);
    }

    @Override
    public boolean isBookmarked(Integer userId, Integer pgId, Integer rentalId) {
        return bookmarkedRepository.existsByUserIdAndPgIdOrRentalId(userId, pgId, rentalId);
    }


    private PgDataDTO convertToPgDTO(PgData pgData) {
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

    private RentalDTO convertToRentalDTO(Rental rental) {
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
