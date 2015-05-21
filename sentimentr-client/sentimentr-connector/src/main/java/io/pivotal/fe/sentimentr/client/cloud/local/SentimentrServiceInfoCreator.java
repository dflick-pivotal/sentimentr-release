package io.pivotal.fe.sentimentr.client.cloud.local;

import io.pivotal.fe.sentimentr.client.cloud.SentimentrServiceInfo;

import org.springframework.cloud.localconfig.LocalConfigServiceInfoCreator;
import org.springframework.cloud.service.UriBasedServiceData;

public class SentimentrServiceInfoCreator extends LocalConfigServiceInfoCreator<SentimentrServiceInfo> {
    public static final String SENTIMENTR_TAG = "sentimentr";

    public SentimentrServiceInfoCreator() {
        super("http");
    }

    @Override
    public boolean accept(UriBasedServiceData serviceData) {
    	System.out.println("############### serviceData.getKey(): "+serviceData.getKey());
    	System.out.println("############### serviceData.getValue(): "+serviceData.getUri());
        return super.accept(serviceData) && SENTIMENTR_TAG.equals(serviceData.getKey());
    }

    @Override
    public SentimentrServiceInfo createServiceInfo(String id, String uri) {
        return new SentimentrServiceInfo(id, uri);
    }
}
