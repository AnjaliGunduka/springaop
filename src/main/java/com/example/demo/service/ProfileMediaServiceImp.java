package com.example.demo.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.constants.Constants;
import com.example.demo.entity.ProfileMedia;
import com.example.demo.entity.Student;
import com.example.demo.exception.BadRequestException;
import com.example.demo.mapper.ProfileMediaMapper;
import com.example.demo.repository.ProfileMediaRepository;
import com.example.demo.repository.StudentRepository;
import com.example.demo.request.ProfileMediaRequest;
import com.example.demo.response.ProfileMediaResponse;

@Service
public class ProfileMediaServiceImp implements ProfileMediaService{

	Logger log = LoggerFactory.getLogger(ProfileMediaServiceImp.class);

	@Autowired
	private ProfileMediaRepository profileMediaRepository;
	
	@Autowired
	private	StudentRepository studentRepository;
	

	public ProfileMediaResponse uploadMedia(ProfileMediaRequest profileMediaRequest) {
		
		if(checkFileType(profileMediaRequest.getMultipartfile())==false)
		{
			throw new BadRequestException(Constants.FILE_TYPE_DOESNT_MATCH);
		}
		Student findById = studentRepository.findById(profileMediaRequest.getStudentId()).orElse(null);
		if (findById == null) {
			throw new BadRequestException(Constants.NO_VALUE_PRESENT);
		}
		
		ProfileMedia profileMedia = ProfileMediaMapper.getProfileMedia(profileMediaRequest.getMultipartfile());
		profileMedia.setType(profileMediaRequest.getMediaTypes());
		profileMedia.setStudent(findById);
		profileMediaRepository.save(profileMedia);
		return ProfileMediaMapper.getResponse(profileMedia);

	}

	private boolean checkFileType(MultipartFile multiPartFile) {
		String extensions = ".jpeg,.jpg,.png";
		log.info("check File type");
		String originalFile = multiPartFile.getOriginalFilename();

		int lastIndex = originalFile.lastIndexOf('.');

		String substring = originalFile.substring(lastIndex, extensions.length());

		return extensions.contains(substring.toLowerCase());

	}

}
