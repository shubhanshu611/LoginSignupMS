package com.example.Controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.Service.UserService;
import com.example.dto.SignupRequest;
import com.example.dto.UserDto;


@RestController
public class SignupController {

    @Autowired
    UserService userService;

    @PostMapping({"/sign-up"})
    public ResponseEntity<?> signupUser(@RequestBody(required = true) @Value(value = "") SignupRequest signUpRequest) throws Exception {

        if (UserService.hasUserWithEmail(signUpRequest.getEmail()))
            return new ResponseEntity<>("User already exists", HttpStatus.NOT_ACCEPTABLE);

        UserDto createdUser = userService.createUser(signUpRequest);
        if (createdUser == null)
            return new ResponseEntity<>("User not created, come again later", HttpStatus.NOT_ACCEPTABLE);

        return new ResponseEntity<>(createdUser, HttpStatus.OK);
    }

}
