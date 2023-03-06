package com.example.demo.mapper;

import java.time.LocalDateTime;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.demo.entity.AuthenticationDetails;
import com.example.demo.entity.LoginCredentials;
import com.example.demo.entity.User;
import com.example.demo.enums.Status;
import com.example.demo.request.UserRequest;
import com.example.demo.response.ApiResponse;
import com.example.demo.response.LoginResponse;
import com.example.demo.response.UserResponse;


public class UserMapper {
	

	public static User createUser(UserRequest userRequest) {
		User user = new User();
		user.setPassword(userRequest.getPassword());
		user.setEmail(userRequest.getEmail());
		user.setUsername(userRequest.getUsername());
		return user;

	}

	public static UserResponse getUser(User user) {
		UserResponse userResponse = new UserResponse();
		userResponse.setUsername(user.getUsername());
		return userResponse;

	}

	public static AuthenticationDetails getAuthenticationDetails(User user, String token) {
		AuthenticationDetails authenticationDetails = new AuthenticationDetails();
		authenticationDetails.setCreatedAt(LocalDateTime.now());
		authenticationDetails.setStatus(Status.ACTIVE);
		authenticationDetails.setToken(token);
		authenticationDetails.setUpdatedAt(LocalDateTime.now());
		authenticationDetails.setUserId(user);
		return authenticationDetails;

	}

	public static UserResponse getUserLoginResponse(long id, String status, String message, String token) {
		UserResponse loginResponse = new UserResponse();
		loginResponse.setToken(token);
		loginResponse.setMessage(message);
		loginResponse.setStatus(status);

		return loginResponse;

	}

	public static LoginResponse getUserLoginResponses(long id, String status, String message, String token) {
		LoginResponse loginResponse = new LoginResponse();
		//loginResponse.setToken(token);
		loginResponse.setMessage(message);
		loginResponse.setStatus(status);
		return loginResponse;

	}

	public static LoginCredentials getUserCredentials(UserRequest userRequest,
			String password, User user) {
		LoginCredentials userCredentials=new LoginCredentials();
		BeanUtils.copyProperties(userRequest, userCredentials);
		userCredentials.setUsername(userRequest.getEmail());
		userCredentials.setPassword(password);
		userCredentials.setStatus(Status.INACTIVE);
		userCredentials.setCreatedAt(LocalDateTime.now());
		userCredentials.setUpdatedAt(LocalDateTime.now());
		userCredentials.setUserId(user);
		return userCredentials;
	}

}
