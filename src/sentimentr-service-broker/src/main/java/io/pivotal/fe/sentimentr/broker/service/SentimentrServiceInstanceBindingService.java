package io.pivotal.fe.sentimentr.broker.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.servicebroker.model.CreateServiceInstanceBindingRequest;
import org.springframework.cloud.servicebroker.model.CreateServiceInstanceBindingResponse;
import org.springframework.cloud.servicebroker.model.DeleteServiceInstanceBindingRequest;
import org.springframework.cloud.servicebroker.service.ServiceInstanceBindingService;
import org.springframework.stereotype.Service;

@Service
public class SentimentrServiceInstanceBindingService implements
		ServiceInstanceBindingService {

    @Value("${sentimentr_uri}")
    private String sentimentr_uri;

	@Override
	public CreateServiceInstanceBindingResponse createServiceInstanceBinding(
			CreateServiceInstanceBindingRequest request) {
				
		Map<String,Object> credentials = new HashMap<String,Object>();
		credentials.put("uri", "http://"+sentimentr_uri+"/sentiment");
	    
		return new CreateServiceInstanceBindingResponse(credentials);
	}

	@Override
	public void deleteServiceInstanceBinding(DeleteServiceInstanceBindingRequest request) {
		// TODO Auto-generated method stub
	}
	
}
