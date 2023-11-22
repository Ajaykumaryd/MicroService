package com.OodlesMicroService.hotelservice.Service;

import com.OodlesMicroService.hotelservice.Entity.Hotel;
import com.OodlesMicroService.hotelservice.Exceptions.ResourceNotFoundException;
import com.OodlesMicroService.hotelservice.Repository.HotelRepository;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    private HotelRepository hotelRepository;

    @Override
    public Hotel create(Hotel hotel) {
        String hotelId = UUID.randomUUID().toString();
        hotel.setId(hotelId);
        return hotelRepository.save(hotel);
    }

    @Override
    public List<Hotel> getAll() {
        return hotelRepository.findAll();
    }

    @Override
    public Hotel get(String id) {
        return hotelRepository.findById(id).
                orElseThrow(() ->
                        new ResourceNotFoundException("Resource not found Exception"));
    }

}
