package org.cloudfoundry.community.servicebroker.sentimentr.service;

import java.util.HashMap;
import java.util.Map;

import org.cloudfoundry.community.servicebroker.exception.ServiceBrokerException;
import org.cloudfoundry.community.servicebroker.exception.ServiceInstanceBindingExistsException;
import org.cloudfoundry.community.servicebroker.model.ServiceInstance;
import org.cloudfoundry.community.servicebroker.model.ServiceInstanceBinding;
import org.cloudfoundry.community.servicebroker.service.ServiceInstanceBindingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SentimentrServiceInstanceBindingService implements
		ServiceInstanceBindingService {

	private ServiceInstanceBinding serviceInstanceBinding = null;
	
    @Value("${sentimentrServiceIP}")
    private String sentimentrServiceIP;
	
//	private MongoAdminService mongo; 
	
//	private MongoServiceInstanceBindingRepository repository;
	
//	@Autowired
//	public SentimentrServiceInstanceBindingService() {
//		this.mongo = mongo;
//		this.repository = repository;
//	}
	
	@Override
	public ServiceInstanceBinding createServiceInstanceBinding(
			String bindingId, ServiceInstance serviceInstance,
			String serviceId, String planId, String appGuid)
			throws ServiceInstanceBindingExistsException, ServiceBrokerException {
	
		ServiceInstanceBinding binding = null;
//		ServiceInstanceBinding binding = repository.findOne(bindingId);
//		if (binding != null) {
//			throw new ServiceInstanceBindingExistsException(binding);
//		}
		
		String username = bindingId;
		// TODO Password Generator
		String password = "password";
		
		// TODO check if user already exists in the DB

//		mongo.createUser(database, username, password);
		
		Map<String,Object> credentials = new HashMap<String,Object>();
//		credentials.put("uri", mongo.getConnectionString(database, username, password));
		credentials.put("uri", "http://"+sentimentrServiceIP+":8080/sentiment");
	    
		if(binding == null)
		{
			binding = new ServiceInstanceBinding(bindingId, serviceInstance.getId(), credentials, null, appGuid);
		}
		
//		repository.save(binding);
		
		return binding;
	}

	protected ServiceInstanceBinding getServiceInstanceBinding(String id) {
		// Implement h2 to store Service Instance Binding
//		return repository.findOne(id);
		return serviceInstanceBinding;
	}

	@Override
	public ServiceInstanceBinding deleteServiceInstanceBinding(
			String bindingId, ServiceInstance instance, 
			String serviceId, String planId)
			throws ServiceBrokerException {
		ServiceInstanceBinding binding = getServiceInstanceBinding(bindingId);
		if (binding!= null) {
//			mongo.deleteUser(binding.getServiceInstanceId(), bindingId);
//			repository.delete(bindingId);
		}
		return binding;
	}

}
