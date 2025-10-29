package com.proj.user.service.UserService.services.impl;

import com.proj.user.service.UserService.entities.Hotel;
import com.proj.user.service.UserService.entities.Rating;
import com.proj.user.service.UserService.entities.User;
import com.proj.user.service.UserService.exceptions.ResourceNotFoundException;
import com.proj.user.service.UserService.external.services.HotelService;
import com.proj.user.service.UserService.repositories.UserRepository;
import com.proj.user.service.UserService.services.UserService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceimpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HotelService hotelService;


    private static final Logger logger = LoggerFactory.getLogger(UserServiceimpl.class);

    @Override
    public User saveUser(User user) {
        String randomUserId = UUID.randomUUID().toString();
        user.setUserId(randomUserId);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User getUser(String userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));

        ResponseEntity<List<Rating>> ratingResponse = restTemplate.exchange(
                "http://RATINGSERVICE/ratings/users/" + user.getUserId(),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Rating>>() {}
        );

        List<Rating> ratings = ratingResponse.getBody();


        List<Rating> ratingList = ratings.stream().map(rating -> {

            //ResponseEntity<Hotel> forEntity = restTemplate.getForEntity("http://HOTELSERVICE/hotels/" + rating.getHotelId(), Hotel.class);
            Hotel hotel = hotelService.getHotel(rating.getHotelId());


            //logger.info("response status code : {} ", forEntity.getStatusCode());

            rating.setHotel(hotel);

            return rating;

        }).collect(Collectors.toList());

        user.setRatings(ratingList);

        return user;
    }

    @Override
    public void deleteUser(String userId) {
        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Cannot delete — user not found with ID: " + userId));

        userRepository.delete(existingUser);
    }

    @Override
    public User updateUser(String userId, User updatedUser) {
        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Cannot update — user not found with ID: " + userId));

        // Update only the fields you allow to change
        existingUser.setName(updatedUser.getName());
        existingUser.setEmail(updatedUser.getEmail());
        existingUser.setAbout(updatedUser.getAbout());

        return userRepository.save(existingUser);
    }
}
