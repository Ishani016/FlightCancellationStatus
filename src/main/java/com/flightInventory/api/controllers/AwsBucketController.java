package com.flightInventory.api.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.flightInventory.api.services.AmazonClientService;
import com.flightInventory.api.services.UserService;

@RestController
@EnableWebMvc
public class AwsBucketController {
	
	AmazonClientService amazonClientService;

	public AwsBucketController(AmazonClientService amazonClientService) {
		this.amazonClientService = amazonClientService;
	}
	
	@Autowired
	UserService userService;
	
	@PostMapping("/upload")
	public ResponseEntity<String> uploadFile(@RequestPart(value="file") MultipartFile file,
			Principal principal) {
		String userName = principal.getName();
		if(userName==null)
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid user");
		
		if(file==null)
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		String fileUrl = amazonClientService.upload(file);
		
		userService.addDocumentUrl(fileUrl, userName);
		
		if(!fileUrl.isEmpty() && fileUrl!=null)
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(fileUrl);
		return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(null);
	}
	
	@DeleteMapping("/deleteDoc")
	public ResponseEntity<String> deleteFile(@RequestBody String fileUrl, Principal principal) {
		String userName = principal.getName();
		if(userName==null)
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid user");
		amazonClientService.delete(fileUrl);
		userService.addDocumentUrl("", userName);
		return ResponseEntity.status(HttpStatus.ACCEPTED)
				.body("Document deleted successfully");
	}
}
