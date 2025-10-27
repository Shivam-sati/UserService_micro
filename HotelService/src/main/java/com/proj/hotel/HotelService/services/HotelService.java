package com.proj.hotel.HotelService.services;

import com.proj.hotel.HotelService.entities.Hotel;

import java.util.List;

public interface HotelService {

    Hotel create(Hotel hotel);

    List<Hotel> getall();

    Hotel getsinglehotel(String id);
}
