package com.example.Service;

import com.example.dto.SignupRequest;
import com.example.dto.UserDto;

public interface UserService {

     public static Boolean hasUserWithEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

     UserDto createUser(SignupRequest signupRequest) throws Exception;

}