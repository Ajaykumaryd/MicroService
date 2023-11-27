package com.OodlesMicroService.userservice.Services;

import com.OodlesMicroService.userservice.Client.HotelClient;
import com.OodlesMicroService.userservice.Client.RatingClient;
import com.OodlesMicroService.userservice.Entities.Hotel;
import com.OodlesMicroService.userservice.Entities.Rating;
import com.OodlesMicroService.userservice.Entities.User;
import com.OodlesMicroService.userservice.External.Service.HotelService;
import com.OodlesMicroService.userservice.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImplementations implements UserService {

    @Autowired
    private UserRepository userRepository;


    @Autowired
    private RatingClient ratingClient;

    @Autowired
    private HotelClient hotelClient;


    @Autowired
    private HotelService hotelService;  //for feight client


    @Override
    public User saveUser(User user) {
        //generate  unique userid
        String randomUserId = UUID.randomUUID().toString();
        user.setUserId(randomUserId);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {
        //implement RATING SERVICE CALL: USING REST TEMPLATE
        return userRepository.findAll();
    }

    @Override
    public User getUser(String userId) {
        User user=userRepository.findById(userId).orElseThrow();
                List<Rating>ratingList=ratingClient.getRatingsByUserId(userId);
        for(Rating rating:ratingList){

            ResponseEntity<Hotel> hotel=hotelClient.getHotel(rating.getHotelId());
             //forfeight client
//            ResponseEntity<Hotel>hotel=hotelService.getHotel(rating.getHotelId());
            rating.setHotel(hotel.getBody());
        }
        user.setRatings(ratingList);
        return user;
    }

}

