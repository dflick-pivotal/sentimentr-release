package io.pivotal.fe.servicebroker.sentimentr.service;

import org.cloudfoundry.community.servicebroker.exception.ServiceBrokerException;
import org.cloudfoundry.community.servicebroker.exception.ServiceInstanceDoesNotExistException;
import org.cloudfoundry.community.servicebroker.exception.ServiceInstanceExistsException;
import org.cloudfoundry.community.servicebroker.exception.ServiceInstanceUpdateNotSupportedException;
import org.cloudfoundry.community.servicebroker.model.ServiceDefinition;
import org.cloudfoundry.community.servicebroker.model.ServiceInstance;
import org.cloudfoundry.community.servicebroker.service.ServiceInstanceService;
import org.springframework.stereotype.Service;

@Service
public class SentimentrServiceInstanceService implements ServiceInstanceService {

	private ServiceInstance serviceInstance = null;
	
	@Override
	public ServiceInstance createServiceInstance(ServiceDefinition service,
			String serviceInstanceId, String planId, String organizationGuid,
			String spaceGuid) 
			throws ServiceInstanceExistsException, ServiceBrokerException {

		if(serviceInstance == null)
		{
			serviceInstance = new ServiceInstance(serviceInstanceId, service.getId(),
					planId, organizationGuid, spaceGuid, null);
		}
		
		return serviceInstance;
	}
	
	@Override
	public ServiceInstance deleteServiceInstance(String arg0, String arg1,
			String arg2) throws ServiceBrokerException {
		// TODO Auto-generated method stub
		return serviceInstance;
	}

	@Override
	public ServiceInstance getServiceInstance(String arg0) {
		// TODO Auto-generated method stub
		return serviceInstance;
	}

	@Override
	public ServiceInstance updateServiceInstance(String arg0, String arg1)
			throws ServiceInstanceUpdateNotSupportedException,
			ServiceBrokerException, ServiceInstanceDoesNotExistException {
		// TODO Auto-generated method stub
		return serviceInstance;
	}

}
