package com.proj.rating.RatingService.repositories;

import com.proj.rating.RatingService.entities.Rating;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RatingRepository extends MongoRepository<Rating,String> {

    

}
