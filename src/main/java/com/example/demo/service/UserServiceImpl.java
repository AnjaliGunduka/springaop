package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.constants.Constants;
import com.example.demo.entity.AuthenticationDetails;
import com.example.demo.entity.LoginCredentials;
import com.example.demo.entity.User;
import com.example.demo.enums.Status;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.RecordNotfoundException;
import com.example.demo.mapper.UserMapper;
import com.example.demo.repository.AuthenticationDetailsRepository;
import com.example.demo.repository.LoginCredentialsRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.request.LoginRequest;
import com.example.demo.request.UserRequest;
import com.example.demo.response.LoginResponse;
import com.example.demo.response.UserResponse;

@Service
public class UserServiceImpl implements UserService {
	private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private UserRepository userRepository;
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	JwtAuthenticationService jwtAuthenticationService;
	@Autowired
	AuthenticationDetailsRepository authenticationDetailsRepository;
	@Autowired
	LoginCredentialsRepository loginCredentialsRepository;

	public LoginResponse login(LoginRequest request) {
		logger.info("login method started");
		Optional<User> userOptional = userRepository.findByEmail(request.getEmailId());
		if (!userOptional.isPresent()) {

			throw new RecordNotfoundException(Constants.USER_NOT_FOUND_WITH_THIS_EMAIL);
		}
		Optional<LoginCredentials> loginOptional = loginCredentialsRepository.findByUserId(userOptional.get());
		if (!loginOptional.isPresent()) {
			throw new RecordNotfoundException(Constants.USER_CREDENTIALS_NOT_FOUND);
		}
		if (!request.getEmailId().equalsIgnoreCase(loginOptional.get().getUsername())) {
			throw new RecordNotfoundException(Constants.INVALID_EMAIL);
		}
		request.setPassword(passwordEncoder.encode(userOptional.get().getPassword()));

//		String password = passwordEncoder.encode(request.getPassword());
//		if (StringUtils.isBlank(loginOptional.get().getPassword())
//				|| !password.equalsIgnoreCase(loginOptional.get().getPassword())) {
//			throw new RecordNotfoundException(Constants.INVALID_PASSWORD);
//		}
		try {
			Optional<AuthenticationDetails> authOptional = authenticationDetailsRepository
					.findByUserIdAndStatus(userOptional.get(), Status.ACTIVE);
			if (authOptional.isPresent()) {
				authOptional.get().setStatus(Status.INACTIVE);
				authenticationDetailsRepository.save(authOptional.get());
			}
		} catch (Exception exception) {
			List<AuthenticationDetails> authUsers = authenticationDetailsRepository.findByUserId(userOptional.get());
			authUsers.forEach(auth -> auth.setStatus(Status.INACTIVE));
			authenticationDetailsRepository.saveAll(authUsers);
		}
		logger.info("login method ended");
		return getUserLoginResponse(userOptional.get(), Constants.USER, Constants.USER_LOGIN_SUCCESSFULLY,
				request.getEmailId());
	}

	private LoginResponse getUserLoginResponse(User users, String userType, String message, String email) {
		String token = jwtAuthenticationService.generateToken(users.getId(), userType, email);
		logger.info("Jwt token generated successfully");
		AuthenticationDetails authenticationDetails = UserMapper.getAuthenticationDetails(users, token);

		authenticationDetailsRepository.save(authenticationDetails);
		logger.info("Authentication details are saved.");

		LoginResponse loginResponse = UserMapper.getUserLoginResponses(users.getId(), Constants.SUCCESS, message,
				token);
		loginResponse.setEmailId(email);
		return loginResponse;

	}

	@Override
	public UserResponse createUser(UserRequest createUserRequest) {
		Optional<User> userOptional = userRepository.findByEmail(createUserRequest.getEmail());
		if (userOptional.isPresent()) {
			throw new BadRequestException(Constants.USER_ALREADY_PRESENT);
		}
		User user = UserMapper.createUser(createUserRequest);
		userRepository.save(user);
		user.setPassword(passwordEncoder.encode(createUserRequest.getPassword()));
		String token = jwtAuthenticationService.generateToken(user.getId(), Constants.USER, user.getEmail());
		AuthenticationDetails authenticationDetails = UserMapper.getAuthenticationDetails(user, token);

		authenticationDetailsRepository.save(authenticationDetails);

		LoginCredentials userCredentials = UserMapper.getUserCredentials(createUserRequest, token, user);
		loginCredentialsRepository.save(userCredentials);

		return UserMapper.getUser(user);

	}

}
