package com.magmutual.userapi.repository;

import com.magmutual.userapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

     @Query("""
    SELECT u FROM User u
    WHERE LOWER(u.firstname) LIKE LOWER(CONCAT('%', :q, '%'))
       OR LOWER(u.lastname) LIKE LOWER(CONCAT('%', :q, '%'))
       OR LOWER(u.email) LIKE LOWER(CONCAT('%', :q, '%'))
       OR LOWER(u.profession) LIKE LOWER(CONCAT('%', :q, '%'))
       OR LOWER(u.country) LIKE LOWER(CONCAT('%', :q, '%'))
       OR LOWER(u.city) LIKE LOWER(CONCAT('%', :q, '%'))""")
     List<User> search(@Param("q") String q);


    List<User> findByDateCreatedBetween(LocalDate startDate, LocalDate endDate);


}