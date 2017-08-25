package com.example.day2Test.Controllers;

import com.example.day2Test.DTO.Status;
import com.example.day2Test.Domains.Category;
import com.example.day2Test.Domains.MapLocation;
import com.example.day2Test.Service.CategoryService;
import com.example.day2Test.Service.MapLocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.NotSupportedException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

@RestController
@RequestMapping(value = "/mapLocation")
public class MapController {

    @Autowired
    private MapLocationService mapLocationService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity addLocation(@RequestBody MapLocation mapLocation, HttpServletRequest request) {
        MapLocation mapLocation1 = null;

        if (mapLocation == null) {
            return new ResponseEntity(new Status("Unauthourized"), HttpStatus.NOT_ACCEPTABLE);
        }
        try {
            mapLocation1 = mapLocationService.addLocation(mapLocation, request);
        } catch (NotSupportedException e) {
            return new ResponseEntity(new Status("Unauthourized"), HttpStatus.NOT_ACCEPTABLE);
        } catch (SQLIntegrityConstraintViolationException e) {
            return new ResponseEntity(new Status("Category should be unique"), HttpStatus.NOT_ACCEPTABLE);
        }

        if (mapLocation1 == null) {
            return new ResponseEntity(new Status("Fill all the Details"), HttpStatus.NOT_ACCEPTABLE);
        }

        return new ResponseEntity(new Status("Map location Added"), HttpStatus.OK);

    }

    @RequestMapping(value = "/getLocationData", method = RequestMethod.GET)
    public ResponseEntity getLocationData(HttpServletRequest request) {
        List<MapLocation> mapLocationList;
        try {
            mapLocationList = mapLocationService.getLocationData(request);
        } catch (NotSupportedException e) {
            return new ResponseEntity(new Status("Fill all the Details"), HttpStatus.NOT_ACCEPTABLE);
        }
        if (mapLocationList == null) {
            return new ResponseEntity(new Status("No data found"), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(HttpStatus.OK);

    }
}