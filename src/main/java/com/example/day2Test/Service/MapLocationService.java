package com.example.day2Test.Service;

import com.example.day2Test.Domains.Category;
import com.example.day2Test.Domains.MapLocation;
import com.example.day2Test.Repositories.CategoryRepository;
import com.example.day2Test.Repositories.LoginRepository;
import com.example.day2Test.Repositories.MapLocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.NotSupportedException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.EmptyStackException;
import java.util.List;

@Service
public class MapLocationService {


    @Autowired
    private MapLocationRepository mapLocationRepository;
    @Autowired
    private LoginRepository loginRepository;

    @Autowired
    private Authorization authorization;

    public MapLocation addLocation(MapLocation mapLocation, HttpServletRequest request) throws SQLIntegrityConstraintViolationException,NotSupportedException,EmptyStackException {


        Boolean authorized = authorization.checkAuthorization("Data Analyst",request);

        if(!authorized){
            throw new NotSupportedException("Unauthorized");
        }
        if(mapLocation.getCategory()==null || mapLocation.getApproval_status()==null ||mapLocation.getLatitude()==null||mapLocation.getLocationName()==null || mapLocation.getLongitude()==null)
            return null;


        MapLocation mapLocation1=mapLocationRepository.save(mapLocation);
        return mapLocation1;

    }

    public List<MapLocation> getLocationData(HttpServletRequest request) throws  NotSupportedException{
        Boolean authorized = authorization.checkAuthorization("Data Analyst",request);

        if(!authorized){
            throw new NotSupportedException("Unauthorized");
        }
        List<MapLocation> list=mapLocationRepository.findAll();
        return list;

    }
}
