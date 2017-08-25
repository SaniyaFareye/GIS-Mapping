package com.example.day2Test.Repositories;


import com.example.day2Test.Domains.MapLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MapLocationRepository extends JpaRepository<MapLocation,Long> {
}
