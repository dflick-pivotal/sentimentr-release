package io.pivotal.fe.sentimentr.client.facade;


import java.net.URL;

public class SentimentrFacadeFactory {
    public SentimentrFacade create(String url) {
        return new SentimentrFacade(url);
    }

    public SentimentrFacade create(URL url) {
        return create(url.toString());
    }
}
