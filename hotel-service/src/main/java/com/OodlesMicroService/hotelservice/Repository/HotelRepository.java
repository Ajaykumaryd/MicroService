package com.OodlesMicroService.hotelservice.Repository;

import com.OodlesMicroService.hotelservice.Entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRepository extends JpaRepository<Hotel,String> {
}
