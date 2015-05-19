package io.pivotal.fe.sentimentr.client.controller;

import io.pivotal.fe.sentimentr.client.domain.Sentiment;
import io.pivotal.fe.sentimentr.client.facade.SentimentrFacade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class SentimentrClientController {
	
    private SentimentrFacade sentimentrFacade;

    @Autowired
    public SentimentrClientController(SentimentrFacade sentimentrFacade) {
        this.sentimentrFacade = sentimentrFacade;
    }

	@RequestMapping("/sentiment/{text}")
	public Sentiment getSentiment(@PathVariable String text) {
		return sentimentrFacade.getSentiment(text);
	}

	@RequestMapping(value="/sentiment", method=RequestMethod.POST)
	public Sentiment getsSentiment(@RequestBody String text) {
		return sentimentrFacade.getSentiment(text);
	}
}
