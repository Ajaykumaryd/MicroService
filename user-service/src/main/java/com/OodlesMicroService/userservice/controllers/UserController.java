package com.OodlesMicroService.userservice.controllers;

import com.OodlesMicroService.userservice.Client.HotelClient;
import com.OodlesMicroService.userservice.Entities.Hotel;
import com.OodlesMicroService.userservice.Entities.Rating;
import com.OodlesMicroService.userservice.Entities.User;
import com.OodlesMicroService.userservice.Client.RatingClient;
import com.OodlesMicroService.userservice.External.Service.HotelService;
import com.OodlesMicroService.userservice.Services.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;



    //create
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User user1 = userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user1);
    }



    @GetMapping("/{userId}")
    public ResponseEntity<User> getSingleUser(@PathVariable String userId) {
        User user = userService.getUser(userId);
        return ResponseEntity.ok(user);
    }

    //all user get
    @GetMapping
    public ResponseEntity<List<User>> getAllUser() {
        List<User> allUser = userService.getAllUser();
        return ResponseEntity.ok(allUser);
    }
        @GetMapping("/with-ratings/{userId}")
        @CircuitBreaker(name = "ratingHotelBreaker",fallbackMethod = "ratingHotelFallback")
        public User getUserWithRatings(@PathVariable String userId){
        User user=userService.getUser(userId);
        return user;
        }

        public ResponseEntity<User>ratingHotelFallBack(String userId,Exception ex){
        User user = User.builder().email("dummy@gmail.com").name("Dummy").about("This user is created dummy because some service is down").userId("141234").build();
        return new ResponseEntity<>(user, HttpStatus.BAD_REQUEST);
        }



















}
