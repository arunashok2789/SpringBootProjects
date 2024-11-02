package com.bankingmanagement;

import org.apache.commons.logging.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BankingmanagementApiApplication {

	private static final Logger log = LoggerFactory.getLogger(BankingmanagementApiApplication.class);

	public static void main(String[] args) {

		SpringApplication.run(BankingmanagementApiApplication.class, args);
        log.info("spring boot started123","spring boot started456");
	}

}
