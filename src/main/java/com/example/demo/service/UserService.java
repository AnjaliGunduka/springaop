package com.example.demo.service;

import com.example.demo.request.LoginRequest;
import com.example.demo.request.UserRequest;
import com.example.demo.response.LoginResponse;
import com.example.demo.response.UserResponse;

public interface UserService {

	UserResponse createUser(UserRequest createUserRequest);

	LoginResponse login(LoginRequest request);

}
