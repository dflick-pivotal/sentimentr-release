package io.pivotal.fe.sentimentr.client.config;

import io.pivotal.fe.sentimentr.client.facade.SentimentrFacade;

import org.springframework.cloud.config.java.AbstractCloudConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile({"cloud","local"})
public class CloudConfiguration extends AbstractCloudConfig {
    @Bean
    public SentimentrFacade sentimentrFacade() {
        return connectionFactory().service(SentimentrFacade.class);
    }
}
