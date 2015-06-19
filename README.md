# sentimentr-release
Project: Pivotal cf natural language processing (nlp) service tile.

Contents: Sentimentr service tile for pivotal cf ops manager, bosh release, service, broker and exemplary client app. 
 
Sentimentr service: 
- provides a sentiment analysis service
- Stanford CoreNLP library (http://nlp.stanford.edu/software/corenlp.shtml[CoreNLP])
- ** link:cities-service/src/main/java/com/example/cities/config/CloudDataSourceConfig.java[CloudDataSourceConfig.java]

- spring boot app

Sentimentr service broker:
- based on spring-boot-cf-service-broker (https://github.com/cloudfoundry-community/spring-boot-cf-service-broker)
- provides credentials for accessing the sentimentr service
- spring boot app

![Alt text](/docs/ops-manager.png?raw=true "tile")

![Alt text](/docs/app-manager.png?raw=true "app-manager")

Exemplary client:
- consumes the sentimentr service
- sends text to the sentimentr service and presents the score received from Sentimentr service
- spring boot app, spring cloud, bootstrap, angularjs

![Alt text](/docs/sentimentr-client.png?raw=true "sentimentr-client")

Possible execution:
- all apps on local machine
- all apps in elastic runtime
- service and broker deployed via bosh/bosh release on AWS or bosh-lite and client app in elastic runtime 
- service and broker deployed via Pivotal CF Ops Manager and client app in elastic runtime 

# Quick start
- The tile and client app is available on google drive.
- import the sentimentr.pivotal file in ops manager.
- configure the AZ and hit deploy.
- create a sentimentr service instance (with app manager or cf cli).
- edit the manifest.yml in the client folder and change the servicename to the one you have just created.
- push the client app.

# Prerequisite for advanced work with bosh
On the local machine:
- bosh cli (use: https://github.com/cloudfoundry-community/traveling-bosh)
- bosh-lite wit cloud foundry installed (https://github.com/cloudfoundry/bosh-lite)
- cf commandline (use: https://github.com/cloudfoundry/cli)
- java jdk and maven

# How you get started with bosh
- clone this project
- cd into the sentimentr-release folder
- target bosh lite with your bosh cli
- execute: ./scripts/make_lite_manifest.sh
- execute: ./scripts/add_sec_rule 
	- (required on bosh lite ==> configures a security group that allows the app to communicate with the service)
- execute: bosh upload release releases/sentimentr-release/sentimentr-release-8.yml
	- gets the sentimentr-release packages from remote blobstore and uploads the release.
- execute: bosh deploy
	- deploys the sentimentr-release
- execute: bosh vms
	- shows all bosh deployed job/vms inclusive the two sentimentr jobs 
- execute: bosh run errand broker-registrar
	- registers the service broker with elastic runtime
- execute: cf create-service  sentimentr development mysenti
	- creates a sentimentr service instance  
- edit the manifest.yml in the client folder and change the servicename to the one you have just created.
- execute: cf push
- point your browser to the sentimentr-client route

extend the release
- bosh -n create release --force && bosh -n upload release && bosh -n deploy

# You changed the Release and would like to create a new tile
- execute 'bosh create release --with-tarball'

# How you use the service with your own application

The sentimentr-client project contains two sub projects (sentimentr-connector and sentimentr-ui). The sentimentr-connector sub project builds the 'sentimentr-connector.jar' required in sentimentr-ui and also in your own application.

The sentimentr-ui project uses the sentimentr-connector dependency like this in the maven pom.xml

		<dependency>
			<groupId>pivotal.sentimentr</groupId>
			<artifactId>sentimentr-connector</artifactId>
			<version>0.0.1-SNAPSHOT</version>
		</dependency>

In order to send text to the sentimentr service and to receive the score in your own application add code like this

			return sentimentrFacade.getSentiment(text);
