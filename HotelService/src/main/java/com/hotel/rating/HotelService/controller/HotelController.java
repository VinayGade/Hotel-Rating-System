package com.hotel.rating.HotelService.controller;

import com.hotel.rating.HotelService.entities.Hotel;
import com.hotel.rating.HotelService.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotels")
public class HotelController {

    @Autowired
    HotelService hotelService;

    // save
    @PostMapping
    public ResponseEntity<Hotel> save(@RequestBody Hotel hotel){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(hotelService.save(hotel));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Hotel> find(@PathVariable("id") String id){
        return ResponseEntity.status(HttpStatus.OK)
                .body(hotelService.find(id));
    }

    @GetMapping
    public ResponseEntity<List<Hotel>> findAll(){
        return ResponseEntity.ok(hotelService.findAll());
    }
}
