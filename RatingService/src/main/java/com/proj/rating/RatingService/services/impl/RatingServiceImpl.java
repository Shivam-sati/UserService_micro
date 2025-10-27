package com.proj.rating.RatingService.services.impl;

import com.proj.rating.RatingService.entities.Rating;
import com.proj.rating.RatingService.repositories.RatingRepository;
import com.proj.rating.RatingService.services.RatingService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class RatingServiceImpl implements RatingService {

    @Autowired
    private RatingRepository ratingRepository;


    @Override
    public Rating create(Rating rating) {
        return ratingRepository.save(rating);
    }

    @Override
    public List<Rating> getRatings() {
        return ratingRepository.findAll();
    }

    @Override
    public List<Rating> getRatingByUserId(String userId) {
        return List.of();
    }

    @Override
    public List<Rating> getRatingByHotelId(String HotelId) {
        return List.of();
    }
}
