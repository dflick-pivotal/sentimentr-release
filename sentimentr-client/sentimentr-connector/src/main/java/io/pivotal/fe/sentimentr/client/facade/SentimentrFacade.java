package io.pivotal.fe.sentimentr.client.facade;

import io.pivotal.fe.sentimentr.client.domain.Sentiment;

import org.springframework.web.client.RestTemplate;

public class SentimentrFacade {
	
	private String sentimentrUri;

	public SentimentrFacade()
	{
	}
	public SentimentrFacade(String sentimentrUri)
	{
		this.sentimentrUri = sentimentrUri;
	}
	
	public Sentiment getSentiment(String text)
	{
		RestTemplate restTemplate = new RestTemplate();
		Sentiment sentiment = restTemplate.getForObject(sentimentrUri+"/{text}", Sentiment.class, text);
		return sentiment;
	}

}
