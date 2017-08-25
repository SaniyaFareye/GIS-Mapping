package com.example.day2Test.Repositories;

import com.example.day2Test.Domains.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {
    @Query("select j from Users j where j.username=:username")
    Users findByUsername(@Param("username")String username);

}
