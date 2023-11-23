package com.OodlesMicroService.userservice.controllers;

import com.OodlesMicroService.userservice.Entities.Rating;
import com.OodlesMicroService.userservice.Entities.User;
import com.OodlesMicroService.userservice.RatingCleint.RatingClient;
import com.OodlesMicroService.userservice.Services.UserService;
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

    @Autowired
    private RatingClient ratingClient;


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
    public User getUserWithRatings(@PathVariable String userId){
        User user=userService.getUser(userId);
        List<Rating>ratingList=ratingClient.getRatingsByUserId(userId);
        user.setRatings(ratingList);
        return user;
    }


















}
