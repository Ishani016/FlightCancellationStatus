package com.flightInventory.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.amazonaws.services.s3.AmazonS3;
import com.flightInventory.api.DemoApiApplication;
import com.flightInventory.api.services.AmazonClientServiceImplementation;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DemoApiApplication.class)
@WebAppConfiguration
public class AmazonClientServiceImplementationTest {
	
	@InjectMocks
	AmazonClientServiceImplementation amazonClientServiceImplementation;
	
	AmazonS3 client = Mockito.mock(AmazonS3.class);
	
	String bucketName = "testBucket";
	String key = "testkey";
	String fileName = "ABC.jpg";
	
	@Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }
	
	@Test
	public void uploadTestFileExists() throws IOException {
		File mockedFile = Mockito.mock(File.class);

		when(mockedFile.exists()).thenReturn(true);
	}	
	
	@Test
	public void deleteFileTestUrlExists() {
		String fileUrl = "https://abcd.com/bdajhgdjhs.jpg";
		assertThat(fileUrl);
	}
	
}
