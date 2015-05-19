package io.pivotal.fe.sentimentr.client.cloud;

import org.springframework.cloud.service.UriBasedServiceInfo;

public class SentimentrServiceInfo extends UriBasedServiceInfo {
    public SentimentrServiceInfo(String id, String url) {
        super(id, url);
    }
}