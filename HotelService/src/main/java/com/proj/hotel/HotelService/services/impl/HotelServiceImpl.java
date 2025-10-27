package com.proj.hotel.HotelService.services.impl;

import com.proj.hotel.HotelService.entities.Hotel;
import com.proj.hotel.HotelService.exceptions.ResourceNotFoundException;
import com.proj.hotel.HotelService.repositories.HotelRepository;
import com.proj.hotel.HotelService.services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    private HotelRepository hotelRepository;

    @Override
    public Hotel create(Hotel hotel) {
       String hotelid =  UUID.randomUUID().toString();
       hotel.setId(hotelid);
        return hotelRepository.save(hotel);
    }

    @Override
    public List<Hotel> getall() {
        return hotelRepository.findAll();
    }

    @Override
    public Hotel getsinglehotel(String id) {
        return hotelRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("The Id doesnt Exist"));
    }
}
