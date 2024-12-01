package com.hotel.rating.user_service.service.external;

import com.hotel.rating.user_service.entities.Hotel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("HOTELSERVICE")
public interface HotelService {

    @GetMapping("/hotels/{id}")
    Hotel getHotel(@PathVariable("id") String id);  //declarative approach
}
