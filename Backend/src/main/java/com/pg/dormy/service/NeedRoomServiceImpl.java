package com.pg.dormy.service;

import com.pg.dormy.DTO.NeedRoomRequestDTO;
import com.pg.dormy.DTO.NeedRoomResponseDTO;
import com.pg.dormy.Exception.ResourceNotFoundException;
import com.pg.dormy.Exception.UnauthorizedException;
import com.pg.dormy.entity.NeedRoom;
import com.pg.dormy.entity.User;
import com.pg.dormy.repository.NeedRoomRepository;
import com.pg.dormy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;


@Service
@Transactional
public class NeedRoomServiceImpl implements NeedRoomService {
    private final NeedRoomRepository needRoomRepository;
    private final UserRepository userRepository;

    @Autowired
    public NeedRoomServiceImpl(NeedRoomRepository needRoomRepository, UserRepository userRepository) {
        this.needRoomRepository = needRoomRepository;
        this.userRepository = userRepository;
    }

    @Override
    public NeedRoomResponseDTO createNeedRoom(Integer userId, NeedRoomRequestDTO request) {
        // Check if user is owner
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        if ("owner".equals(user.getUserType())) {
            throw new UnauthorizedException("Owners cannot create need room posts");
        }

        // Check if user already has a post
        if (hasExistingPost(userId)) {
            throw new IllegalStateException("User already has an active need room post");
        }

        NeedRoom needRoom = new NeedRoom();
        needRoom.setUserId(userId);
        updateNeedRoomFromRequest(needRoom, request);

        return convertToDTO(needRoomRepository.save(needRoom));
    }

    @Override
    public NeedRoomResponseDTO updateNeedRoom(Integer userId, Integer roomId, NeedRoomRequestDTO request) {
        NeedRoom needRoom = needRoomRepository.findByUserIdAndRoomId(userId, roomId)
                .orElseThrow(() -> new UnauthorizedException("Not authorized to update this post"));

        updateNeedRoomFromRequest(needRoom, request);
        return convertToDTO(needRoomRepository.save(needRoom));
    }

    @Override
    public void deleteNeedRoom(Integer userId, Integer roomId) {
        NeedRoom needRoom = needRoomRepository.findByUserIdAndRoomId(userId, roomId)
                .orElseThrow(() -> new UnauthorizedException("Not authorized to delete this post"));

        needRoomRepository.delete(needRoom);
    }

    @Override
    public Page<NeedRoomResponseDTO> searchNeedRooms(String roomArea, String lookingFor, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("roomId").descending());
        return needRoomRepository.searchRooms(roomArea, lookingFor, pageable)
                .map(this::convertToDTO);
    }

    @Override
    public boolean hasExistingPost(Integer userId) {
        return needRoomRepository.existsByUserId(userId);
    }

    private void updateNeedRoomFromRequest(NeedRoom needRoom, NeedRoomRequestDTO request) {
        needRoom.setRoomCity(request.getRoomCity());
        needRoom.setRoomArea(request.getRoomArea());
        needRoom.setLocation(request.getLocation());
        needRoom.setRoomType(request.getRoomType());
        needRoom.setRent(request.getRent());
        needRoom.setLookingFor(request.getLookingFor());
        needRoom.setSelfHighlights(request.getSelfHighlights());
        needRoom.setDescription(request.getDescription());
        needRoom.setMobileNoVisibility(request.getMobileNoVisibility());
    }

    private NeedRoomResponseDTO convertToDTO(NeedRoom needRoom) {
        return new NeedRoomResponseDTO(
                needRoom.getRoomId(),
                needRoom.getUserId(),
                needRoom.getRoomCity(),
                needRoom.getRoomArea(),
                needRoom.getLocation(),
                needRoom.getRoomType(),
                needRoom.getRent(),
                needRoom.getLookingFor(),
                needRoom.getSelfHighlights(),
                needRoom.getDescription(),
                needRoom.getMobileNoVisibility()
        );
    }
    @Override
    public NeedRoomResponseDTO getUserPost(Integer userId) {
        NeedRoom needRoom = needRoomRepository.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("No need room post found for this user"));
        return convertToDTO(needRoom);
    }
}
