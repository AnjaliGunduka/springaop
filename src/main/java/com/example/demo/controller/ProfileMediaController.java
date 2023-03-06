package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.enums.MediaTypes;
import com.example.demo.request.ProfileMediaRequest;
import com.example.demo.response.ProfileMediaResponse;
import com.example.demo.service.ProfileMediaService;

@RestController
@RequestMapping("/profile")
public class ProfileMediaController {

	@Autowired
	private ProfileMediaService profileMediaService;

	@PostMapping(value = "/uploadMedia")
	public ResponseEntity<ProfileMediaResponse> uploadMedia(@RequestPart("file") MultipartFile multiPartFile,
			@RequestParam("mediaTypes") MediaTypes mediaTypes,
			@RequestParam(name = "studentId", required = true) Long studentId) {
		ProfileMediaRequest profileMediaRequest = new ProfileMediaRequest();
		profileMediaRequest.setMultipartfile(multiPartFile);
		profileMediaRequest.setMediaTypes(mediaTypes);
		profileMediaRequest.setStudentId(studentId);
		return ResponseEntity.status(HttpStatus.OK).body(profileMediaService.uploadMedia(profileMediaRequest));
	}

}
