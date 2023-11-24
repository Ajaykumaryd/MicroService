package com.OodlesMicroService.userservice.External.Service;

import com.OodlesMicroService.userservice.Entities.Hotel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;

@FeignClient(name = "Hotel-service")
public interface HotelService {

    @GetMapping("/hotels/{hotelId}")
    public ResponseEntity<Hotel> getHotel(@PathVariable String hotelId);



}
