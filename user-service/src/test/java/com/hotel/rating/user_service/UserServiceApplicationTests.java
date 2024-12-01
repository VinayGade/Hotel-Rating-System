package com.hotel.rating.user_service;

import com.hotel.rating.user_service.entities.Rating;
import com.hotel.rating.user_service.service.external.RatingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;

@Service
@SpringBootTest
class UserServiceApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	private RatingService ratingService;

	// To test if feign client working correctly
	// inserting records in ratings db
	/*
	@Test
	void createRating(){
		Rating rating = Rating
				.builder()
				.rating(10).userId("").hotelId("").feedback("this is created using feign client.")
				.build();
		Rating savedRating = ratingService.create(rating).getBody();
		System.out.println("New Rating created");
	}
	 */

}