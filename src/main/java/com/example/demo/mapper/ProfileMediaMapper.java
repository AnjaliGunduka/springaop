package com.example.demo.mapper;

import java.time.LocalDateTime;

import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.constants.Constants;
import com.example.demo.entity.ProfileMedia;
import com.example.demo.exception.BadRequestException;
import com.example.demo.request.ProfileMediaRequest;
import com.example.demo.response.ProfileMediaResponse;

public class ProfileMediaMapper {

	public static ProfileMedia getProfileMedia(MultipartFile multiPartFile) {
		ProfileMedia profileMedia = new ProfileMedia();

		try {
			profileMedia.setFile(multiPartFile.getBytes());
		} catch (Exception e) {
			throw new BadRequestException(Constants.FAILED);

		}
		String fileName = StringUtils.cleanPath(multiPartFile.getOriginalFilename());
		profileMedia.setName(fileName);
		profileMedia.setFileExtension(fileName.substring(fileName.lastIndexOf(".") + 1));
		profileMedia.setSize(multiPartFile.getSize());
		profileMedia.setCreatedAt(LocalDateTime.now());
		profileMedia.setUpdatedAt(LocalDateTime.now());
		return profileMedia;
	}
	
	public static ProfileMediaResponse getResponse(ProfileMedia profileMedia)
	{
		ProfileMediaResponse profileMediaResponse=new ProfileMediaResponse();
		profileMediaResponse.setId(profileMedia.getId());
		profileMediaResponse.setStatus(Constants.SUCCESS);
		profileMediaResponse.setMessage(Constants.PROFILE_UPLOADED);
		return profileMediaResponse;
		
	}
	

}
