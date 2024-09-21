package com.job.scrapper;

import com.job.scrapper.constant.Constants;
import com.job.scrapper.service.EmailService;
import com.job.scrapper.service.Scrapper;
import com.job.scrapper.util.Utility;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@SpringBootApplication
@EnableScheduling
public class ScrapperApplication {

	public static void main(String[] args) {
		SpringApplication.run(ScrapperApplication.class, args);

	}


}
