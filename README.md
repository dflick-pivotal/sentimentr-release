# sentimentr-release
Project: Pivotal cf nlp natural language processing service.

Contents: Sentimentr service tile for pivotal cf ops manager, bosh release, service, broker and exemplary client app. 
 
Sentimentr service: 
- provides a sentiment analysis service
- Stanford CoreNLP library (http://nlp.stanford.edu/software/corenlp.shtml)
- spring boot app

Sentimentr service broker:
- based on spring-boot-cf-service-broker (https://github.com/cloudfoundry-community/spring-boot-cf-service-broker)
- provides credentials for accessing the sentimentr service
- spring boot app

Exemplary client:
- Consumes the sentimentr service
- Sends text to the sentimentr service and presents the score received from Sentimentr service
- spring boot app, spring cloud, bootstrap, angularjs

![Alt text](/docs/sentimentr-client.png?raw=true "sentimentr-client")

Possible execution:
- All apps on local machine
- All apps in elastic runtime
- Service and broker deployed via bosh/bosh release on AWS or bosh-lite and client app in elastic runtime 
- Service and broker deployed via Pivotal CF Ops Manager and client app in elastic runtime 

Service binding options:
- Managed Service
- User provided service 

# Quick start

- The tile and client app is available on google drive.
 - Import the sentimentr.pivotal file in ops manager and hit deploy.
 - Create a sentimentr service instance.
 - Edit the manifest.yml in the client folder and change the servicename to the one you have just created.
 - Push the client app.

# prerequisite for the more advanced stuff
On the local machine:
- bosh cli
- cf commandline 
- java jdk and maven


# How you get started with the sentimentr tile or bosh release
- clone the project
- cd into the sentimentr-release folder
- target bosh lite with your bosh cli
- execute ./scripts/make_lite_manifest.sh
- execute 'bosh create release --with-tarball'
- 


example bosh lite
- 

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
