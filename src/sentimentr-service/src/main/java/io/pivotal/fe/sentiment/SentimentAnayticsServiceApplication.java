package io.pivotal.fe.sentiment;

import io.pivotal.fe.sentiment.engine.NLP;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SentimentAnayticsServiceApplication {

	@RequestMapping("/env")
	public Map<String,Object> env(HttpServletRequest request) {
	    Map<String,Object> model = new HashMap<String,Object>();
	    model.put("ip", request.getLocalAddr());
	    model.put("port", request.getLocalPort());
		return model;
	}

	@RequestMapping("/sentiment/{text}")
	public Map<String,Object> sentiment(@PathVariable String text, HttpServletRequest request) {
		int sentiment = NLP.findSentiment(text);
	    Map<String,Object> model = new HashMap<String,Object>();
	    model.put("id", UUID.randomUUID().toString());
	    model.put("sentiment", new Integer(sentiment));
	    
	    System.out.println("######### Remote IP: "+request.getRemoteHost());
	    
		return model;
	}

	public static void main(String[] args) {
        SpringApplication.run(SentimentAnayticsServiceApplication.class, args);
        NLP.init();
    }
}
