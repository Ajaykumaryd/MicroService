package com.OodlesMicroService.hotelservice.Service;

import com.OodlesMicroService.hotelservice.Entity.Hotel;

import java.util.List;

public interface HotelService {

    //create

    Hotel create(Hotel hotel);

    //get all
    List<Hotel> getAll();

    //get single
    Hotel get(String id);
}
