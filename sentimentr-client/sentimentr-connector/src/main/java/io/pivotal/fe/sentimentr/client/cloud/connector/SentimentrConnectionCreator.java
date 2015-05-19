package io.pivotal.fe.sentimentr.client.cloud.connector;

import org.springframework.cloud.service.AbstractServiceConnectorCreator;
import org.springframework.cloud.service.ServiceConnectorConfig;

import io.pivotal.fe.sentimentr.client.cloud.SentimentrServiceInfo;
import io.pivotal.fe.sentimentr.client.facade.SentimentrFacade;
import io.pivotal.fe.sentimentr.client.facade.SentimentrFacadeFactory;

public class SentimentrConnectionCreator extends AbstractServiceConnectorCreator<SentimentrFacade, SentimentrServiceInfo> {
    @Override
    public SentimentrFacade create(SentimentrServiceInfo serviceInfo, ServiceConnectorConfig serviceConnectorConfig) {
        return new SentimentrFacadeFactory().create(serviceInfo.getUri());
    }
}