package com.pg.dormy.repository;

import com.pg.dormy.entity.NeedRoommate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NeedRoommateRepository extends JpaRepository<NeedRoommate, Integer> {
    boolean existsByUserId(Integer userId);

    @Query("SELECT n FROM NeedRoommate n WHERE n.userId = :userId")
    Optional<NeedRoommate> findByUserId(@Param("userId") Integer userId);

    @Query("SELECT n FROM NeedRoommate n WHERE " +
            "(:roomArea IS NULL OR LOWER(n.roomArea) LIKE LOWER(CONCAT('%', :roomArea, '%'))) " +
            "AND (:lookingFor IS NULL OR n.lookingFor = :lookingFor)")
    Page<NeedRoommate> searchRoommates(
            @Param("roomArea") String roomArea,
            @Param("lookingFor") String lookingFor,
            Pageable pageable
    );
}
