package org.cloudfoundry.community.servicebroker.sentimentr.config;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.cloudfoundry.community.servicebroker.model.Catalog;
import org.cloudfoundry.community.servicebroker.model.Plan;
import org.cloudfoundry.community.servicebroker.model.ServiceDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CatalogConfig {
	
	@Bean
	public Catalog catalog() {		
		return new Catalog( Arrays.asList(
				new ServiceDefinition(
					"sentimentr", 
					"sentimentr", 
					"A simple sentiment analysis implementation", 
					true, 
					false,
					Arrays.asList(
							new Plan("sentimentr-plan", 
									"sentimentr-plan", 
									"This is a default sentimentr plan.  All services are created equally.",
									getPlanMetadata())),
					Arrays.asList("sentimentr", "uri"),
					getServiceDefinitionMetadata(),
					null,
					null)));
	}
	
/* Used by Pivotal CF console */	
	
	private Map<String,Object> getServiceDefinitionMetadata() {
		Map<String,Object> sdMetadata = new HashMap<String,Object>();
		sdMetadata.put("displayName", "Sentimentr");
		sdMetadata.put("imageUrl","http://pbs.twimg.com/profile_images/722745234/nlp-logo-4x4.jpg");
		sdMetadata.put("longDescription","Sentimentr Service");
		sdMetadata.put("providerDisplayName","Pivotal");
		sdMetadata.put("documentationUrl","http://www.pivotal.io");
		sdMetadata.put("supportUrl","http://www.pivotal.io");
		return sdMetadata;
	}
	
	private Map<String,Object> getPlanMetadata() {		
		Map<String,Object> planMetadata = new HashMap<String,Object>();
		planMetadata.put("costs", getCosts());
		planMetadata.put("bullets", getBullets());
		return planMetadata;
	}
	
	private List<Map<String,Object>> getCosts() {
		Map<String,Object> costsMap = new HashMap<String,Object>();
		
		Map<String,Object> amount = new HashMap<String,Object>();
		amount.put("usd", new Double(0.0));
	
		costsMap.put("amount", amount);
		costsMap.put("unit", "MONTHLY");
		
		return Arrays.asList(costsMap);
	}
	
	private List<String> getBullets() {
		return Arrays.asList("Shared sentiment analysis service", 
				"enjoy !!!", 
				"enjoy !!!");
	}
	
}