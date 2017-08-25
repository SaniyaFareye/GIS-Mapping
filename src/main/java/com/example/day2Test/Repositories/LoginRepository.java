package com.example.day2Test.Repositories;

import com.example.day2Test.Domains.Login;
import com.example.day2Test.Domains.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface LoginRepository extends JpaRepository<Login, Long> {
    @Query("select j from Login j where j.cookie=:cookie")
    Login getByCookie(@Param("cookie")String cookie);

    @Transactional
    @Modifying
    @Query("delete  from Login j where j.cookie=:cookie")
    void deleteByCookie(@Param("cookie")String cookie);
}
