package com.proj.hotel.HotelService.controllers;


import com.proj.hotel.HotelService.entities.Hotel;
import com.proj.hotel.HotelService.services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotels")
public class HotelController {

    @Autowired
    private HotelService hotelService;

    @PostMapping
    public ResponseEntity<Hotel> createhotel(@RequestBody Hotel hotel){
        return ResponseEntity.status(HttpStatus.CREATED).body(hotelService.create(hotel));
    }

    @GetMapping("/{hotelid}")
    public ResponseEntity<Hotel> getsingleid(@PathVariable String hotelid){
        return ResponseEntity.status(HttpStatus.OK).body(hotelService.getsinglehotel(hotelid));
    }

    @GetMapping
    public ResponseEntity<List<Hotel>> getallhotel(){
        return ResponseEntity.ok(hotelService.getall());
    }


}
