package io.pivotal.fe.sentimentr.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.cloudfoundry.CloudFoundryConnector;

@SpringBootApplication
public class SentimentrApplication {

	public static void main(String[] args) {
		
        if (new CloudFoundryConnector().isInMatchingCloud()) {
            System.setProperty("spring.profiles.active", "cloud");
            
        }else{
        	System.setProperty("spring.profiles.active", "default");
        }

		SpringApplication.run(SentimentrApplication.class, args);
	}

}
