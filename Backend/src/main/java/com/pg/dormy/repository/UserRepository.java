package com.pg.dormy.repository;

import com.pg.dormy.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {
    @Query(value = "SELECT * FROM user_data WHERE phone_number = :phoneNumber", nativeQuery = true)
    User findUserByPhoneNumber(@Param("phoneNumber") String phoneNumber);
}
