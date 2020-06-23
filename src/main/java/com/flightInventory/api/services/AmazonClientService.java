package com.flightInventory.api.services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface AmazonClientService {

	String upload(MultipartFile file);

	void delete(String fileUrl);

}
