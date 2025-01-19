package com.pg.dormy.repository;

import com.pg.dormy.entity.PgRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PgRoomRepository extends JpaRepository<PgRoom, Integer> {
    List<PgRoom> findByPgId(Integer pgId);

    @Query("SELECT CASE WHEN COUNT(r) > 0 THEN true ELSE false END FROM PgRoom r " +
            "WHERE r.pgRoomId = :roomId AND r.pgData.userId = :userId")
    boolean isRoomOwner(@Param("roomId") Integer roomId, @Param("userId") Integer userId);

    void deleteByPgId(Integer pgId);
}