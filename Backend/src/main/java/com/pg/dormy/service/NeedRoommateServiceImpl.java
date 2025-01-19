package com.pg.dormy.service;

import com.pg.dormy.DTO.NeedRoommateRequestDTO;
import com.pg.dormy.DTO.NeedRoommateResponseDTO;
import com.pg.dormy.Exception.ResourceNotFoundException;
import com.pg.dormy.Exception.UnauthorizedException;
import com.pg.dormy.entity.NeedRoommate;
import com.pg.dormy.entity.User;
import com.pg.dormy.repository.NeedRoommateRepository;
import com.pg.dormy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class NeedRoommateServiceImpl implements NeedRoommateService {
    private final NeedRoommateRepository needRoommateRepository;
    private final UserRepository userRepository;

    @Autowired
    public NeedRoommateServiceImpl(NeedRoommateRepository needRoommateRepository,
                                   UserRepository userRepository) {
        this.needRoommateRepository = needRoommateRepository;
        this.userRepository = userRepository;
    }

    @Override
    public NeedRoommateResponseDTO createNeedRoommate(Integer userId, NeedRoommateRequestDTO request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        if ("owner".equals(user.getUserType())) {
            throw new UnauthorizedException("Owners cannot create roommate posts");
        }

        if (hasExistingPost(userId)) {
            throw new IllegalStateException("User already has an active roommate post");
        }

        NeedRoommate needRoommate = new NeedRoommate();
        needRoommate.setUserId(userId);
        updateNeedRoommateFromRequest(needRoommate, request);

        return convertToDTO(needRoommateRepository.save(needRoommate));
    }

    @Override
    public NeedRoommateResponseDTO updateNeedRoommate(Integer userId, Integer roommateId,
                                                      NeedRoommateRequestDTO request) {
        NeedRoommate needRoommate = needRoommateRepository.findById(roommateId)
                .orElseThrow(() -> new ResourceNotFoundException("Roommate post not found"));

        if (!needRoommate.getUserId().equals(userId)) {
            throw new UnauthorizedException("Not authorized to update this post");
        }

        updateNeedRoommateFromRequest(needRoommate, request);
        return convertToDTO(needRoommateRepository.save(needRoommate));
    }

    @Override
    public void deleteNeedRoommate(Integer userId, Integer roommateId) {
        NeedRoommate needRoommate = needRoommateRepository.findById(roommateId)
                .orElseThrow(() -> new ResourceNotFoundException("Roommate post not found"));

        if (!needRoommate.getUserId().equals(userId)) {
            throw new UnauthorizedException("Not authorized to delete this post");
        }

        needRoommateRepository.delete(needRoommate);
    }

    @Override
    public Page<NeedRoommateResponseDTO> searchNeedRoommates(String roomArea, String lookingFor,
                                                             int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("roommateId").descending());
        return needRoommateRepository.searchRoommates(roomArea, lookingFor, pageable)
                .map(this::convertToDTO);
    }

    @Override
    public NeedRoommateResponseDTO getUserPost(Integer userId) {
        return needRoommateRepository.findByUserId(userId)
                .map(this::convertToDTO)
                .orElseThrow(() -> new ResourceNotFoundException("No roommate post found for this user"));
    }

    @Override
    public boolean hasExistingPost(Integer userId) {
        return needRoommateRepository.existsByUserId(userId);
    }

    private void updateNeedRoommateFromRequest(NeedRoommate needRoommate, NeedRoommateRequestDTO request) {
        needRoommate.setRoomCity(request.getRoomCity());
        needRoommate.setRoomArea(request.getRoomArea());
        needRoommate.setLocation(request.getLocation());
        needRoommate.setRoomType(request.getRoomType());
        needRoommate.setRent(request.getRent());
        needRoommate.setLookingFor(request.getLookingFor());
        needRoommate.setPropertyHighlights(request.getPropertyHighlights());
        needRoommate.setPropertyAmenities(request.getPropertyAmenities());
        needRoommate.setDescription(request.getDescription());
        needRoommate.setMobileNoVisibility(request.getMobileNoVisibility());
        needRoommate.setImage1(request.getImage1());
        needRoommate.setImage2(request.getImage2());
        needRoommate.setImage3(request.getImage3());
    }

    private NeedRoommateResponseDTO convertToDTO(NeedRoommate needRoommate) {
        return new NeedRoommateResponseDTO(
                needRoommate.getRoommateId(),
                needRoommate.getUserId(),
                needRoommate.getRoomCity(),
                needRoommate.getRoomArea(),
                needRoommate.getLocation(),
                needRoommate.getRoomType(),
                needRoommate.getRent(),
                needRoommate.getLookingFor(),
                needRoommate.getPropertyHighlights(),
                needRoommate.getPropertyAmenities(),
                needRoommate.getDescription(),
                needRoommate.getMobileNoVisibility(),
                needRoommate.getImage1(),
                needRoommate.getImage2(),
                needRoommate.getImage3()
        );
    }
}
