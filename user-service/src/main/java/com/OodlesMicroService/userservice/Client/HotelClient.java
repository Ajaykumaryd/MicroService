package com.OodlesMicroService.userservice.Client;

import com.OodlesMicroService.userservice.Entities.Hotel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

@HttpExchange
public interface HotelClient {


    @GetExchange("/hotels/{hotelId}")
    public ResponseEntity<Hotel> getHotel(@PathVariable String hotelId);
}
