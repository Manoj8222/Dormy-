package com.pg.dormy.service;

import com.pg.dormy.DTO.NeedRoommateRequestDTO;
import com.pg.dormy.DTO.NeedRoommateResponseDTO;
import org.springframework.data.domain.Page;

public interface NeedRoommateService {
    NeedRoommateResponseDTO createNeedRoommate(Integer userId, NeedRoommateRequestDTO request);
    NeedRoommateResponseDTO updateNeedRoommate(Integer userId, Integer roommateId, NeedRoommateRequestDTO request);
    void deleteNeedRoommate(Integer userId, Integer roommateId);
    Page<NeedRoommateResponseDTO> searchNeedRoommates(String roomArea, String lookingFor, int page, int size);
    NeedRoommateResponseDTO getUserPost(Integer userId);
    boolean hasExistingPost(Integer userId);
}
