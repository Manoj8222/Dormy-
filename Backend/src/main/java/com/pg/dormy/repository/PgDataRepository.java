package com.pg.dormy.repository;

import com.pg.dormy.entity.PgData;
import com.pg.dormy.entity.PgRoom;
import com.pg.dormy.entity.Rental;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.List;

public interface PgDataRepository extends JpaRepository<PgData,Integer> {
//    @Query("SELECT p, r FROM PgData p LEFT JOIN p.rooms r ORDER BY p.postedDate DESC")
//    Page<Object[]> findAllPgDataWithRooms(Pageable pageable);

    @Query("SELECT DISTINCT p FROM PgData p LEFT JOIN FETCH p.rooms ORDER BY p.postedDate DESC")
    Page<PgData> findAllPgDataWithRooms(Pageable pageable);

    @Query("SELECT r FROM PgRoom r WHERE r.pgData.pgId = :pgId")
    List<PgRoom> findRoomsByPgId(@Param("pgId") Integer pgId);
    @Query("SELECT DISTINCT p FROM PgData p LEFT JOIN PgRoom r ON p.pgId = r.pgId " +
            "WHERE (:pgArea IS NULL OR LOWER(p.pgArea) LIKE LOWER(CONCAT('%', :pgArea, '%'))) " +
            "AND (:gender IS NULL OR p.gender = :gender) " +
            "AND (:hasSingle IS NULL OR EXISTS (SELECT 1 FROM PgRoom r2 WHERE r2.pgId = p.pgId AND r2.occupancyType = 'Single')) " +
            "AND (:hasDouble IS NULL OR EXISTS (SELECT 1 FROM PgRoom r2 WHERE r2.pgId = p.pgId AND r2.occupancyType = 'Double')) " +
            "AND (:hasTriple IS NULL OR EXISTS (SELECT 1 FROM PgRoom r2 WHERE r2.pgId = p.pgId AND r2.occupancyType = 'Triple')) " +
            "AND (:hasQuadruple IS NULL OR EXISTS (SELECT 1 FROM PgRoom r2 WHERE r2.pgId = p.pgId AND r2.occupancyType = 'Quadruple')) " +
            "AND (:minRent IS NULL OR EXISTS (SELECT 1 FROM PgRoom r2 WHERE r2.pgId = p.pgId AND r2.rent >= :minRent)) " +
            "AND (:maxRent IS NULL OR EXISTS (SELECT 1 FROM PgRoom r2 WHERE r2.pgId = p.pgId AND r2.rent <= :maxRent))")
    Page<PgData> searchPGs(
            @Param("pgArea") String pgArea,
            @Param("gender") String gender,
            @Param("hasSingle") Boolean hasSingle,
            @Param("hasDouble") Boolean hasDouble,
            @Param("hasTriple") Boolean hasTriple,
            @Param("hasQuadruple") Boolean hasQuadruple,
            @Param("minRent") BigDecimal minRent,
            @Param("maxRent") BigDecimal maxRent,
            Pageable pageable
    );
    @Query("SELECT u.phoneNumber FROM PgData p JOIN p.userData u WHERE p.pgId = :pgId")
    String getOwnerPhoneNumber(@Param("pgId")Integer pgId);


}



