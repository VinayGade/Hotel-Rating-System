package com.hotel.rating.user_service.repositories;


import com.hotel.rating.user_service.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {


}
