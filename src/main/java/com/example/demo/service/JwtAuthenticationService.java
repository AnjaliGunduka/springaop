package com.example.demo.service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.demo.constants.Constants;
import com.example.demo.entity.AuthenticationDetails;
import com.example.demo.enums.Status;
import com.example.demo.exception.UnauthorizedException;
import com.example.demo.model.TokenPayLoadDetails;
import com.example.demo.repository.AuthenticationDetailsRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


@Service
public class JwtAuthenticationService {
	private String secretKey = "secretKey";

	@Value("${web.app.session.minutes}")
	public Long webAppSession;
	@Autowired
	AuthenticationDetailsRepository authenticationDetailsRepository;
	private Logger logger = LoggerFactory.getLogger(JwtAuthenticationService.class);

	public String generateToken(long id, String typeOfUser,String email) {

		logger.info("Inside Generate token method..");
		String token = Jwts.builder().setSubject("" + id).setClaims(getClaims(id, typeOfUser,email))
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.signWith(SignatureAlgorithm.HS512, secretKey.getBytes()).compact();
		logger.info("Token generated...");
		return token;
	}

	public String generateRefreshToken(long id, String typeOfUser,String email) {

		logger.info("Inside Generate token method..");
		String token = Jwts.builder().setSubject("" + id).setClaims(getClaims(id, typeOfUser,email))
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.signWith(SignatureAlgorithm.HS512, secretKey.getBytes()).compact();
		logger.info("Token generated...");
		return token;

	}
	private Map<String, Object> getClaims(long id, String typeOfUser,String email) {
		logger.info("Mapping jwtRequest to token creation content...");
		Map<String, Object> claims = new HashMap<>();
		claims.put("typeOfUser", typeOfUser);
		claims.put("id", id);
		claims.put("emailId", email);
		
		return claims;
	}

	public boolean validateToken(String jwt) {
		if (jwt != null && jwt.startsWith("Bearer ")) {
			jwt = jwt.substring(7);
		}
		return (isTokenExpired(jwt));
	}
	
	private boolean isTokenExpired(String jwt) {
		logger.info("Checking token Expiry ");
		LocalDateTime now = LocalDateTime.now();
		logger.info("Fetching Authentication Details by token and status");
		Optional<AuthenticationDetails> auditingDetailsOpt = authenticationDetailsRepository.findByTokenAndStatus(jwt,
				Status.ACTIVE);
		if (auditingDetailsOpt.isPresent()) {
			logger.info("Authentication Auditing Details record present");
			AuthenticationDetails auditingDetailsObj = auditingDetailsOpt.get();
			logger.info("Checking current and lastAccessed time ");
			boolean isExpired = false;
			// TokenPayLoadDetails tokenPayLoadDetails = getTokenPayLoadDetails(jwt);
			/*
			 * if (Constants.USER.equalsIgnoreCase(tokenPayLoadDetails.getTypeOfUser()) &&
			 * auditingDetailsObj.getUserId() != null) { isExpired = now
			 * .isAfter(auditingDetailsObj.getUpdatedAt().plus(webAppSession,
			 * ChronoUnit.MINUTES)); } else if
			 * (Constants.ADMIN.equalsIgnoreCase(tokenPayLoadDetails.getTypeOfUser())) {
			 * isExpired = now
			 * .isAfter(auditingDetailsObj.getUpdatedAt().plus(webAppSession,
			 * ChronoUnit.MINUTES)); }
			 */
			isExpired = now.isAfter(auditingDetailsObj.getUpdatedAt().plus(webAppSession, ChronoUnit.MINUTES));
			if (isExpired) {
				logger.info("Token Expired...");
				auditingDetailsObj.setStatus(Status.INACTIVE);
				authenticationDetailsRepository.save(auditingDetailsObj);
				throw new UnauthorizedException(Constants.UNAUTHORIZED);
			}
			logger.info("valid token changing last accessed time to current time");
			auditingDetailsObj.setUpdatedAt(LocalDateTime.now());
			authenticationDetailsRepository.save(auditingDetailsObj);
			return true;
		}
		logger.info("Token Expired...");
		return false;
	}
	public TokenPayLoadDetails getTokenPayLoadDetails(String jwt) {
		logger.info("Fetching payload from token..");
		String[] split_string = jwt.split("\\.");
		String base64EncodedBody = split_string[1];
		Base64 base64Url = new Base64();
		String body = new String(base64Url.decode(base64EncodedBody));
		ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		TokenPayLoadDetails tokenPayLoadDetails = null;
		try {
			tokenPayLoadDetails = mapper.readValue(body, TokenPayLoadDetails.class);
		} catch (JsonProcessingException e) {
			logger.info("Error while fetching payload from token..");
			throw new UnauthorizedException(Constants.INVALID_TOKEN);
		}
		logger.info("Fetching payload from token successfull");
		return tokenPayLoadDetails;
	}

	public TokenPayLoadDetails getTokenPayLoadDetails(HttpServletRequest httpRequest) {
		logger.info("Getting token payload details from HttpServlet request ");
		TokenPayLoadDetails tokenPayLoadDetails = new TokenPayLoadDetails();
		if (httpRequest.getAttribute("tokenPayLoadDetails") != null) {
			tokenPayLoadDetails = (TokenPayLoadDetails) httpRequest.getAttribute("tokenPayLoadDetails");
		}
		return tokenPayLoadDetails;

	}

	 
}
