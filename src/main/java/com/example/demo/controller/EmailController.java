package com.example.demo.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Emails;
import com.example.demo.service.EmailService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/email")
public class EmailController {

	@Autowired
	public EmailService emailService;

	@ApiOperation(value = "function returns a string description api")
	// this annotation to show this message beside the function projectDescription
	@GetMapping
	public String projectDescription() {
		return "this is a simple api for email sender" + " created by spring boot ";
	}

	@ApiOperation(value = "this function allows you to see all sent email in json format")
	@GetMapping(path = "allEmails")
	public List<Emails> AllEmails() {
		return emailService.getEmailList();
	}

	@ApiOperation(value = "this function allows you to get a specific email id ")
	@GetMapping(path = "{emailId}")

	public List<Emails> getEmailById(@PathVariable("emailId") Integer emailId) {
		// this function accept emailId as a parameter in the url (get request)
		return emailService.getEmailById(emailId);
	}

	@PostMapping
	@ApiOperation(value = "this function allows you to send an email")
	public void sendEmail(@Valid @RequestBody Emails email
			//, @RequestParam(required = false)
	// valid annotation to validate the email object created
	//Integer nbrEmail
	) {
//		if (nbrEmail == null)
//			nbrEmail = 1;// by default, I keep it 1
//		for (int i = 0; i < nbrEmail; i++) {
			emailService.sendSimpleEmail(email);
			// i loop till i reach nbrEmail input parameter
//		}

	}
}
