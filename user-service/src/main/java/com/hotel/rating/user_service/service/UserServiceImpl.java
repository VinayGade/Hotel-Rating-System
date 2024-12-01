package com.hotel.rating.user_service.service;

import com.hotel.rating.user_service.entities.Hotel;
import com.hotel.rating.user_service.entities.Rating;
import com.hotel.rating.user_service.entities.User;
import com.hotel.rating.user_service.exceptions.ResourceNotFoundException;
import com.hotel.rating.user_service.repositories.UserRepository;
import com.hotel.rating.user_service.service.external.HotelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private HotelService hotelService;

    @Autowired
    private RestTemplate restTemplate;

    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public User save(User user) {
        String userId = UUID.randomUUID().toString();
        user.setUserId(userId);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAll() {
        //TODO: Implement Rating Service call using RestTemplate
        return userRepository.findAll();
    }

    @Override
    public User find(String userId) {
        User user = userRepository.findById(userId).orElseThrow(
                ()->new ResourceNotFoundException("User with given id not found!!"));

        /*
        USER-SERVICE="http://localhost:8081/users"
        RATINGSERVICE="http://localhost:8083/ratings"
        HOTELSERVICE="http://localhost:8082/hotels"
        * */

        Rating[] userRatings = restTemplate
                .getForObject("http://RATINGSERVICE/ratings/users/"+user.getUserId(),
                        Rating[].class);
        logger.info("{} ", (Object) userRatings);

        List<Rating> userRatingList = Arrays.stream(userRatings).toList();
        //String hotelId = "e75c3ed4-f0e3-40d7-86ff-04612c72e621";

        userRatingList.stream().map(rating -> {
            //Old approach
            /*
            ResponseEntity<Hotel> forEntity = restTemplate.getForEntity("http://HOTELSERVICE/hotels/"+rating.getHotelId(), Hotel.class);
            Hotel hotel = forEntity.getBody();
            logger.info("response status code: ", forEntity.getStatusCode())
             */
            Hotel hotel = hotelService.getHotel(rating.getHotelId());
            rating.setHotel(hotel);
            return rating;
        }).collect(Collectors.toList());

        user.setRatings(userRatingList);

        return user;
    }

    @Override
    public User delete(String userId) {
        User userToDelete = find(userId);
        userRepository.delete(userToDelete);
        return userToDelete;
    }

    @Override
    public User update(String userId, User user) {


        return null;
    }
}
