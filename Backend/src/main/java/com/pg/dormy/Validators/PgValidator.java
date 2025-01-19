package com.pg.dormy.Validators;

import com.pg.dormy.Constant.PgConstants;
import com.pg.dormy.DTO.PgDataRequestDTO;
import com.pg.dormy.DTO.PgRoomRequestDTO;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.Arrays;

@Component
public class PgValidator {

    public void validatePgData(PgDataRequestDTO request) {
        if (request == null) {
            throw new IllegalArgumentException("Request cannot be null");
        }

        if (StringUtils.isEmpty(request.getPgName())) {
            throw new IllegalArgumentException("PG name is required");
        }

        if (!Arrays.asList(PgConstants.VALID_GENDERS).contains(request.getGender())) {
            throw new IllegalArgumentException("Invalid gender specified");
        }

        // Add more validation as needed
    }

    public void validatePgRoom(PgRoomRequestDTO request) {
        if (request == null) {
            throw new IllegalArgumentException("Request cannot be null");
        }

        if (!Arrays.asList(PgConstants.VALID_OCCUPANCY_TYPES).contains(request.getOccupancyType())) {
            throw new IllegalArgumentException("Invalid occupancy type specified");
        }

        if (request.getRent() == null || request.getRent().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Valid rent amount is required");
        }

        // Add more validation as needed
    }
}