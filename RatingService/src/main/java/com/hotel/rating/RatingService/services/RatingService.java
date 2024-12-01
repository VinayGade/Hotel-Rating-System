package com.hotel.rating.RatingService.services;

import com.hotel.rating.RatingService.entities.Rating;
import com.hotel.rating.RatingService.repository.RatingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RatingService {

    Rating save(Rating rating);

    List<Rating> findAll();

    List<Rating> findByUserId(String userId);

    List<Rating> findByHotelId(String hotelId);

}
