package com.pg.dormy.repository;

import com.pg.dormy.entity.Bookmarked;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookmarkedRepository extends JpaRepository<Bookmarked,Integer> {
    @Query("SELECT b FROM Bookmarked b WHERE b.userId = :userId")
    List<Bookmarked> findByUserId(@Param("userId") Integer userId);

    @Query("SELECT CASE WHEN COUNT(b) > 0 THEN true ELSE false END FROM Bookmarked b " +
            "WHERE b.userId = :userId AND (b.pgId = :pgId OR b.rentalId = :rentalId)")
    boolean existsByUserIdAndPgIdOrRentalId(
            @Param("userId") Integer userId,
            @Param("pgId") Integer pgId,
            @Param("rentalId") Integer rentalId
    );

    @Modifying
    @Query("DELETE FROM Bookmarked b WHERE b.userId = :userId AND " +
            "((:pgId IS NOT NULL AND b.pgId = :pgId) OR (:rentalId IS NOT NULL AND b.rentalId = :rentalId))")
    void deleteBookmark(
            @Param("userId") Integer userId,
            @Param("pgId") Integer pgId,
            @Param("rentalId") Integer rentalId
    );
}
