package com.hotel.rating.HotelService.repositories;

import com.hotel.rating.HotelService.entities.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRepository extends JpaRepository<Hotel, String> {
}
