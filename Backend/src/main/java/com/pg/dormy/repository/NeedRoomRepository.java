package com.pg.dormy.repository;

import com.pg.dormy.entity.NeedRoom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.Optional;

@Repository
public interface NeedRoomRepository extends JpaRepository<NeedRoom, Integer> {
    boolean existsByUserId(Integer userId);

    @Query("SELECT n FROM NeedRoom n WHERE n.userId = :userId AND n.roomId = :roomId")
    Optional<NeedRoom> findByUserIdAndRoomId(@Param("userId") Integer userId, @Param("roomId") Integer roomId);

    @Query("SELECT n FROM NeedRoom n WHERE " +
            "(:roomArea IS NULL OR LOWER(n.roomArea) LIKE LOWER(CONCAT('%', :roomArea, '%'))) " +
            "AND (:lookingFor IS NULL OR n.lookingFor = :lookingFor)")
    Page<NeedRoom> searchRooms(
            @Param("roomArea") String roomArea,
            @Param("lookingFor") String lookingFor,
            Pageable pageable
    );
    @Query("SELECT n FROM NeedRoom n WHERE n.userId = :userId")
    Optional<NeedRoom> findByUserId(@Param("userId") Integer userId);
}
