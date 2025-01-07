package com.pg.dormy.service;

import com.pg.dormy.DTO.PgDataDTO;
import com.pg.dormy.DTO.PgSearchCriteria;
import com.pg.dormy.entity.PgData;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

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
}
