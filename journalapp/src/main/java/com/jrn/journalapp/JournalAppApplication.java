package com.jrn.journalapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableTransactionManagement
public class JournalAppApplication {

	public static void main(String[] args) {

		SpringApplication.run(JournalAppApplication.class, args);
		System.setProperty("https.protocols", "TLSv1,TLSv1.1,TLSv1.2");
	}
	@Bean
	public PlatformTransactionManager add(MongoDatabaseFactory dbFactory){// interface name PlatformTransactionManager which handle transaction
		return new MongoTransactionManager(dbFactory);
	}
	@Bean
	public RestTemplate restTemplate(){
		return new RestTemplate();
	}
}
//BYKE5ZCq1XiBLZ1n
