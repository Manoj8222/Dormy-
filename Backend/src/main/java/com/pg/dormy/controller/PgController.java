package com.pg.dormy.controller;

import com.pg.dormy.DTO.PgDataRequestDTO;
import com.pg.dormy.DTO.PgDataResponseDTO;
import com.pg.dormy.DTO.PgRoomRequestDTO;
import com.pg.dormy.DTO.PgRoomResponseDTO;
import com.pg.dormy.Exception.ResourceNotFoundException;
import com.pg.dormy.Exception.UnauthorizedException;
import com.pg.dormy.service.PgService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pg")
public class PgController {

    private final PgService pgService;

    @Autowired
    public PgController(PgService pgService) {
        this.pgService = pgService;
    }

    @PostMapping
    public ResponseEntity<?> createPg(
            @RequestHeader("User-Id") Integer userId,
            @Valid @RequestBody PgDataRequestDTO request) {
        try {
            PgDataResponseDTO response = pgService.createPg(userId, request);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body("Error creating PG: " + e.getMessage());
        }
    }

    @PutMapping("/{pgId}")
    public ResponseEntity<?> updatePg(
            @RequestHeader("User-Id") Integer userId,
            @PathVariable Integer pgId,
            @Valid @RequestBody PgDataRequestDTO request) {
        try {
            PgDataResponseDTO response = pgService.updatePg(userId, pgId, request);
            return ResponseEntity.ok(response);
        } catch (UnauthorizedException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body("Error updating PG: " + e.getMessage());
        }
    }

    @DeleteMapping("/{pgId}")
    public ResponseEntity<?> deletePg(
            @RequestHeader("User-Id") Integer userId,
            @PathVariable Integer pgId) {
        try {
            pgService.deletePg(userId, pgId);
            return ResponseEntity.ok().build();
        } catch (UnauthorizedException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body("Error deleting PG: " + e.getMessage());
        }
    }

    @PostMapping("/{pgId}/rooms")
    public ResponseEntity<?> addRoom(
            @RequestHeader("User-Id") Integer userId,
            @PathVariable Integer pgId,
            @Valid @RequestBody PgRoomRequestDTO request) {
        try {
            PgRoomResponseDTO response = pgService.addRoom(userId, pgId, request);
            return ResponseEntity.ok(response);
        } catch (UnauthorizedException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body("Error adding room: " + e.getMessage());
        }
    }

    @PutMapping("/{pgId}/rooms/{roomId}")
    public ResponseEntity<?> updateRoom(
            @RequestHeader("User-Id") Integer userId,
            @PathVariable Integer pgId,
            @PathVariable Integer roomId,
            @Valid @RequestBody PgRoomRequestDTO request) {
        try {
            PgRoomResponseDTO response = pgService.updateRoom(userId, pgId, roomId, request);
            return ResponseEntity.ok(response);
        } catch (UnauthorizedException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body("Error updating room: " + e.getMessage());
        }
    }

    @DeleteMapping("/{pgId}/rooms/{roomId}")
    public ResponseEntity<?> deleteRoom(
            @RequestHeader("User-Id") Integer userId,
            @PathVariable Integer pgId,
            @PathVariable Integer roomId) {
        try {
            pgService.deleteRoom(userId, pgId, roomId);
            return ResponseEntity.ok().build();
        } catch (UnauthorizedException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body("Error deleting room: " + e.getMessage());
        }
    }

    @GetMapping("/user")
    public ResponseEntity<?> getUserPgs(@RequestHeader("User-Id") Integer userId) {
        try {
            List<PgDataResponseDTO> response = pgService.getPgsByUserId(userId);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body("Error fetching user PGs: " + e.getLocalizedMessage());
        }
    }

    @GetMapping("/{pgId}")
    public ResponseEntity<?> getPgById(@PathVariable Integer pgId) {
        try {
            PgDataResponseDTO response = pgService.getPgById(pgId);
            return ResponseEntity.ok(response);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body("Error fetching PG: " + e.getMessage());
        }
    }
}