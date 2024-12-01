package com.hotel.rating.HotelService.service;

import com.hotel.rating.HotelService.entities.Hotel;
import com.hotel.rating.HotelService.exceptions.ResourceNotFoundException;
import com.hotel.rating.HotelService.repositories.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    HotelRepository hotelRepository;

    @Override
    public Hotel save(Hotel hotel) {
        String id = UUID.randomUUID().toString();
        hotel.setId(id);
        return hotelRepository.save(hotel);
    }

    @Override
    public Hotel find(String id) {
        return hotelRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Hotel with given id not found!!"));
    }

    @Override
    public List<Hotel> findAll() {
        return hotelRepository.findAll();
    }

    @Override
    public Hotel update(String id) {
        return null;
    }

    @Override
    public Hotel delete(String id) {
        return null;
    }
}
