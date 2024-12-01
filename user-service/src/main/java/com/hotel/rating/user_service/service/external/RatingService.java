package com.hotel.rating.user_service.service.external;

import com.hotel.rating.user_service.entities.Rating;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.Map;
import java.util.Objects;

@FeignClient("RATINGSERVICE")
public interface RatingService {

    @PostMapping("/ratings")
    ResponseEntity<Rating> create(Rating rating);

    @PutMapping("/ratings/{id}")
    ResponseEntity<Rating> update(@PathVariable("id") String id, Rating rating);

    @DeleteMapping("/ratings/{id}")
    void delete(@PathVariable String id);
}
