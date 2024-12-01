package com.hotel.rating.user_service.service;

import com.hotel.rating.user_service.entities.User;

import java.util.List;

public interface UserService {

    User save(User user);

    List<User> getAll();

    User find(String userId);

    User delete(String userId);

    User update(String userId, User user);
}
