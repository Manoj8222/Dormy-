package com.pg.dormy.service;

import com.pg.dormy.DTO.NeedRoomRequestDTO;
import com.pg.dormy.DTO.NeedRoomResponseDTO;
import org.springframework.data.domain.Page;

public interface NeedRoomService {
    NeedRoomResponseDTO createNeedRoom(Integer userId, NeedRoomRequestDTO request);
    NeedRoomResponseDTO updateNeedRoom(Integer userId, Integer roomId, NeedRoomRequestDTO request);
    void deleteNeedRoom(Integer userId, Integer roomId);
    Page<NeedRoomResponseDTO> searchNeedRooms(String roomArea, String lookingFor, int page, int size);
    boolean hasExistingPost(Integer userId);

    NeedRoomResponseDTO getUserPost(Integer userId);
}
