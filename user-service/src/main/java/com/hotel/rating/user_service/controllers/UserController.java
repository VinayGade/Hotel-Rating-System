package com.hotel.rating.user_service.controllers;

import com.hotel.rating.user_service.entities.User;
import com.hotel.rating.user_service.service.UserService;
import com.hotel.rating.user_service.service.UserServiceImpl;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @PostMapping
    public ResponseEntity<User> create(@RequestBody User user){
        User userToSave = userService.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(userToSave);
    }

    int retryCount = 0;

    // findById
    //@CircuitBreaker(name="hotelRatingBreaker", fallbackMethod="ratingHotelFallback")
    //@Retry(name="ratingHotelService", fallbackMethod = "ratingHotelFallback")
    @RateLimiter(name="userRateLimiter", fallbackMethod = "ratingHotelFallback")
    @GetMapping("/{id}")
    public ResponseEntity<User> search(@PathVariable("id") String id){
        logger.info("find single user by id: UserController");
        logger.info("Retry count: {}", ++retryCount);
        User user = userService.find(id);
        return ResponseEntity.ok(user);
    }

    public ResponseEntity<User> ratingHotelFallback(String id, Exception e){
        logger.info("Fallback is executed because service is down.", e.getMessage());
        User user = User.builder()
                .name("Bob")
                .email("bob.p@gmail.com")
                .about("This is a dummy user created by system because some service is down")
                .userId("123abc9x")
                .build();
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<User>> findAll(){
        List<User> allUsers = userService.getAll();
        return ResponseEntity.ok(allUsers);
    }

    // update

    // delete
}
