package org.cloudfoundry.community.servicebroker.sentimentr.service;

import java.util.HashMap;
import java.util.Map;

import org.cloudfoundry.community.servicebroker.exception.ServiceBrokerException;
import org.cloudfoundry.community.servicebroker.exception.ServiceInstanceBindingExistsException;
import org.cloudfoundry.community.servicebroker.model.ServiceInstance;
import org.cloudfoundry.community.servicebroker.model.ServiceInstanceBinding;
import org.cloudfoundry.community.servicebroker.service.ServiceInstanceBindingService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SentimentrServiceInstanceBindingService implements
		ServiceInstanceBindingService {

	private ServiceInstanceBinding serviceInstanceBinding = null;
	
    @Value("${sentimentr_uri}")
    private String sentimentr_uri;
    	
	@Override
	public ServiceInstanceBinding createServiceInstanceBinding(
			String bindingId, ServiceInstance serviceInstance,
			String serviceId, String planId, String appGuid)
			throws ServiceInstanceBindingExistsException, ServiceBrokerException {
	
		ServiceInstanceBinding binding = null;
		
		String username = bindingId;
		// TODO Password Generator
		String password = "password";
		
		// TODO check if user already exists in the DB
		
		Map<String,Object> credentials = new HashMap<String,Object>();
		credentials.put("uri", "http://"+sentimentr_uri+"/sentiment");
	    
		if(binding == null)
		{
			binding = new ServiceInstanceBinding(bindingId, serviceInstance.getId(), credentials, null, appGuid);
		}
				
		return binding;
	}

	protected ServiceInstanceBinding getServiceInstanceBinding(String id) {
		// Implement h2 to store Service Instance Binding
		return serviceInstanceBinding;
	}

	@Override
	public ServiceInstanceBinding deleteServiceInstanceBinding(
			String bindingId, ServiceInstance instance, 
			String serviceId, String planId)
			throws ServiceBrokerException {
		ServiceInstanceBinding binding = getServiceInstanceBinding(bindingId);
		if (binding!= null) {
		}
		return binding;
	}

}
