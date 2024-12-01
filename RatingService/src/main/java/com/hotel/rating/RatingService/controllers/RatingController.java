package com.hotel.rating.RatingService.controllers;

import com.hotel.rating.RatingService.entities.Rating;
import com.hotel.rating.RatingService.services.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ratings")
public class RatingController {

    @Autowired
    private RatingService ratingService;

    @PostMapping
    public ResponseEntity<Rating> save(@RequestBody Rating rating){
        return ResponseEntity.status(HttpStatus.CREATED).body(
                ratingService.save(rating));
    }

    @GetMapping
    public ResponseEntity<List<Rating>> getAll(){
        return ResponseEntity.ok(ratingService.findAll());
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<List<Rating>> findByUserId(@PathVariable("id") String userId){
        return ResponseEntity.ok(ratingService.findByUserId(userId));
    }

    @GetMapping("/hotels/{id}")
    public ResponseEntity<List<Rating>> findByHotelId(@PathVariable("id") String hotelId){
        return ResponseEntity.ok(ratingService.findByHotelId(hotelId));
    }

}