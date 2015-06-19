package io.pivotal.fe.sentimentr.client.cloud.cloudfoundry;

import io.pivotal.fe.sentimentr.client.cloud.SentimentrServiceInfo;

import java.util.List;
import java.util.Map;

import org.springframework.cloud.cloudfoundry.CloudFoundryServiceInfoCreator;
import org.springframework.cloud.cloudfoundry.Tags;

public class SentimentrServiceInfoCreator extends
		CloudFoundryServiceInfoCreator<SentimentrServiceInfo> {

	public static final String SENTIMENTR_TAG = "sentimentr";

	public SentimentrServiceInfoCreator() {
		super(new Tags(), SENTIMENTR_TAG);
	}

	@Override
	public SentimentrServiceInfo createServiceInfo(
			Map<String, Object> serviceData) {
		String id = (String) serviceData.get("name");
		Map<String, Object> credentials = getCredentials(serviceData);
		String uri = getUriFromCredentials(credentials);
		return new SentimentrServiceInfo(id, uri);
	}

	@Override
	public boolean accept(Map<String, Object> serviceData) {
		List<String> tags = (List<String>) serviceData.get("tags");
		String label = (String) serviceData.get("label");
		String name = (String) serviceData.get("name");

		boolean tagAcceptable = tags.contains(SENTIMENTR_TAG);
		boolean labelAcceptable = label != null
				&& label.startsWith(SENTIMENTR_TAG);

		return tagAcceptable || labelAcceptable;
	}
}
