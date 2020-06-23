package com.flightInventory.api.services;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;

@Configuration
public class AmazonClientServiceImplementation implements AmazonClientService{
	private Logger logger = LoggerFactory.getLogger(Logger.class);
	
    private AmazonS3 s3client;

    @Value("${amazon.properties.endpointUrl}")
    private String endpointUrl;
    @Value("${amazon.properties.bucketName}")
    private String bucketName;
    @Value("${amazon.properties.accessKey}")
    private String accessKey;
    @Value("${amazon.properties.secretKey}")
    private String secretKey;
    
    @PostConstruct
    protected void initialize() {
		AWSCredentials credentials = new BasicAWSCredentials(
				this.accessKey,
				this.secretKey
				);
	    this.s3client = new AmazonS3Client(credentials);
	}
	
	@Override
	public String upload(MultipartFile multipartFile) {
		String fileUrl = "";
	    try {
	        File file = convertMultiPartToFile(multipartFile);
	        String fileName = generateFileName(multipartFile);
	        fileUrl = bucketName + "/" + fileName;
	        uploadFileToS3bucket(fileName, file);
	        logger.info("File uploaded to S3 bucket successfully");
	        file.delete();
	    } catch (Exception e) {
	       e.printStackTrace();
	       logger.error("File cannot be uploaded to S3");
	    }
	    return fileUrl;
	}
	
	private File convertMultiPartToFile(MultipartFile file) throws IOException {
	    File convFile = new File(file.getOriginalFilename());
	    FileOutputStream fos = new FileOutputStream(convFile);
	    fos.write(file.getBytes());
	    fos.close();
	    return convFile;
	}
	
	private String generateFileName(MultipartFile multiPart) {
	    return new Date().getTime() + "-" + multiPart.getOriginalFilename().replace(" ", "_");
	}
	
	private void uploadFileToS3bucket(String fileName, File file) {
	    s3client.putObject(new PutObjectRequest(bucketName, 
	    		fileName, 
	    		file)
	            .withCannedAcl(CannedAccessControlList.PublicRead));
	}

	@Override
	public void delete(String fileUrl) {
		String fileName = fileUrl.substring(fileUrl.lastIndexOf("/")+1,
				fileUrl.lastIndexOf("\""));
		System.out.println("Laal ishq "+fileName);
		s3client.deleteObject(new DeleteObjectRequest(bucketName, fileName));
		logger.info(fileName+ " deleted successfully");
	}

}
