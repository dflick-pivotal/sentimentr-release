package io.pivotal.fe.sentimentr.client.config;

import io.pivotal.fe.sentimentr.client.facade.SentimentrFacade;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("default")
public class DefaultConfiguration {
    @Bean
    public SentimentrFacade sentimentrFacade() {
        return new SentimentrFacade("http://localhost:8080/sentiment");
    }
}
