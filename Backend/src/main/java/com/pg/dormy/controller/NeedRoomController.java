package com.pg.dormy.controller;

import com.pg.dormy.DTO.NeedRoomRequestDTO;
import com.pg.dormy.DTO.NeedRoomResponseDTO;
import com.pg.dormy.Exception.ResourceNotFoundException;
import com.pg.dormy.Exception.UnauthorizedException;
import com.pg.dormy.service.NeedRoomService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/need-room")
public class NeedRoomController {
    private final NeedRoomService needRoomService;

    @Autowired
    public NeedRoomController(NeedRoomService needRoomService) {
        this.needRoomService = needRoomService;
    }

    @PostMapping
    public ResponseEntity<?> createNeedRoom(
            @RequestHeader("User-Id") Integer userId,
            @Valid @RequestBody NeedRoomRequestDTO request) {
        try {
            NeedRoomResponseDTO response = needRoomService.createNeedRoom(userId, request);
            return ResponseEntity.ok(response);
        } catch (UnauthorizedException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @PutMapping("/{roomId}")
    public ResponseEntity<?> updateNeedRoom(
            @RequestHeader("User-Id") Integer userId,
            @PathVariable Integer roomId,
            @Valid @RequestBody NeedRoomRequestDTO request) {
        try {
            NeedRoomResponseDTO response = needRoomService.updateNeedRoom(userId, roomId, request);
            return ResponseEntity.ok(response);
        } catch (UnauthorizedException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @DeleteMapping("/{roomId}")
    public ResponseEntity<?> deleteNeedRoom(
            @RequestHeader("User-Id") Integer userId,
            @PathVariable Integer roomId) {
        try {
            needRoomService.deleteNeedRoom(userId, roomId);
            return ResponseEntity.ok().build();
        } catch (UnauthorizedException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchNeedRooms(
            @RequestParam(required = false) String roomArea,
            @RequestParam(required = false) String lookingFor,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        try {
            Page<NeedRoomResponseDTO> response = needRoomService.searchNeedRooms(
                    roomArea, lookingFor, page, size);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
    @GetMapping("/my-post")
    public ResponseEntity<?> getUserPost(@RequestHeader("User-Id") Integer userId) {
        try {
            NeedRoomResponseDTO response = needRoomService.getUserPost(userId);
            return ResponseEntity.ok(response);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}
