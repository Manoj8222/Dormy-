package com.pg.dormy.service;

import com.pg.dormy.DTO.*;
import com.pg.dormy.entity.PgData;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.List;

@Service
public interface PgService {
    Page<PgDataDTO> getAllPgDataPaginated(int page, int size);
    Page<PgDataDTO> searchPGs(String pgArea,
                              String gender,
                              Boolean hasSingle,
                              Boolean hasDouble,
                              Boolean hasTriple,
                              Boolean hasQuadruple,
                              BigDecimal minRent,
                              BigDecimal maxRent, int page, int size);

    String getOwnerPhoneNumber(Integer pgId);

    PgDataResponseDTO createPg(Integer userId, PgDataRequestDTO request);
    PgDataResponseDTO updatePg(Integer userId, Integer pgId, PgDataRequestDTO request);
    void deletePg(Integer userId, Integer pgId);
    PgRoomResponseDTO addRoom(Integer userId, Integer pgId, PgRoomRequestDTO request);
    PgRoomResponseDTO updateRoom(Integer userId, Integer pgId, Integer roomId, PgRoomRequestDTO request);
    void deleteRoom(Integer userId, Integer pgId, Integer roomId);
    List<PgDataResponseDTO> getPgsByUserId(Integer userId);
    PgDataResponseDTO getPgById(Integer pgId);
}
