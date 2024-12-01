package com.hotel.rating.HotelService.service;

import com.hotel.rating.HotelService.entities.Hotel;

import java.util.List;

public interface HotelService {

    Hotel save(Hotel hotel);
    Hotel find(String id);

    List<Hotel> findAll();

    Hotel update(String id);

    Hotel delete(String id);

}
