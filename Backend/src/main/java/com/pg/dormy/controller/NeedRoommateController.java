package com.pg.dormy.controller;

import com.pg.dormy.DTO.NeedRoommateRequestDTO;
import com.pg.dormy.DTO.NeedRoommateResponseDTO;
import com.pg.dormy.Exception.ResourceNotFoundException;
import com.pg.dormy.Exception.UnauthorizedException;
import com.pg.dormy.service.NeedRoommateService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/need-roommate")
public class NeedRoommateController {
    private final NeedRoommateService needRoommateService;

    @Autowired
    public NeedRoommateController(NeedRoommateService needRoommateService) {
        this.needRoommateService = needRoommateService;
    }

    @PostMapping
    public ResponseEntity<?> createNeedRoommate(
            @RequestHeader("User-Id") Integer userId,
            @Valid @RequestBody NeedRoommateRequestDTO request) {
        try {
            NeedRoommateResponseDTO response = needRoommateService.createNeedRoommate(userId, request);
            return ResponseEntity.ok(response);
        } catch (UnauthorizedException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @PutMapping("/{roommateId}")
    public ResponseEntity<?> updateNeedRoommate(
            @RequestHeader("User-Id") Integer userId,
            @PathVariable Integer roommateId,
            @Valid @RequestBody NeedRoommateRequestDTO request) {
        try {
            NeedRoommateResponseDTO response = needRoommateService.updateNeedRoommate(
                    userId, roommateId, request);
            return ResponseEntity.ok(response);
        } catch (UnauthorizedException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @DeleteMapping("/{roommateId}")
    public ResponseEntity<?> deleteNeedRoommate(
            @RequestHeader("User-Id") Integer userId,
            @PathVariable Integer roommateId) {
        try {
            needRoommateService.deleteNeedRoommate(userId, roommateId);
            return ResponseEntity.ok().build();
        } catch (UnauthorizedException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchNeedRoommates(
            @RequestParam(required = false) String roomArea,
            @RequestParam(required = false) String lookingFor,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        try {
            Page<NeedRoommateResponseDTO> response = needRoommateService.searchNeedRoommates(
                    roomArea, lookingFor, page, size);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping("/my-post")
    public ResponseEntity<?> getUserPost(@RequestHeader("User-Id") Integer userId) {
        try {
            NeedRoommateResponseDTO response = needRoommateService.getUserPost(userId);
            return ResponseEntity.ok(response);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}
