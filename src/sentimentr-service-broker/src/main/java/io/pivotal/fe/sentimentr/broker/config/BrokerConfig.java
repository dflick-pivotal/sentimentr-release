package io.pivotal.fe.sentimentr.broker.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.cloud.servicebroker.model.BrokerApiVersion;

import org.springframework.context.annotation.Bean;

@Configuration
@ComponentScan(basePackages = "io.pivotal.fe.sentimentr.broker")
public class BrokerConfig {

  @Bean
  public BrokerApiVersion brokerApiVersion() {
    return new BrokerApiVersion();
  }

}
