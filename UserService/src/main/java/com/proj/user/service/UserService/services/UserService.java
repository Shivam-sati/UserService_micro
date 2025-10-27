package com.proj.user.service.UserService.services;

import com.proj.user.service.UserService.entities.User;

import java.util.List;

public interface UserService {


    //user service

    //create
    User saveUser(User user);


    //get all user
    List<User> getAllUser();

    //get single user of given UserID
    User getUser(String userId);

    void deleteUser(String userId);
    User updateUser(String userId, User updatedUser);
}
