package io.pivotal.fe.sentiment;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import io.pivotal.fe.sentiment.engine.NLP;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SentimentAnayticsServiceApplication {

	@RequestMapping("/sentiment/{text}")
	public Map<String,Object> sentiment(@PathVariable String text) {
		int sentiment = NLP.findSentiment(text);
	    Map<String,Object> model = new HashMap<String,Object>();
	    model.put("id", UUID.randomUUID().toString());
	    model.put("sentiment", new Integer(sentiment));
		return model;
	}

	public static void main(String[] args) {
        SpringApplication.run(SentimentAnayticsServiceApplication.class, args);
        NLP.init();
    }
    
/*    @Autowired
    public NLP nlp() {
        NLP nlp = new NLP();
        NLP.init();
        return nlp;
    }*/
}
