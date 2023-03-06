package com.example.demo.service;

import com.example.demo.request.ProfileMediaRequest;
import com.example.demo.response.ProfileMediaResponse;

public interface ProfileMediaService {

	
	public ProfileMediaResponse uploadMedia(ProfileMediaRequest  profileMediaRequest);
}
