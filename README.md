# sentimentr-release
Project: Pivotal CF Natural Language Processing (NLP) Service Tile.

Contents: Sentimentr service tile for Pivotal CF Ops Manager, bosh release, service, broker and exemplary client app. 
 
[sentimentr-service](/src/sentimentr-service): 
- Provides a sentiment analysis service
- Stanford [CoreNLP](http://nlp.stanford.edu/software/corenlp.shtml) library
- Receives text and sends back the sentiment score
- Spring Boot app

[sentimentr-service-broker](/src/sentimentr-service-broker):
- Based on [spring-boot-cf-service-broker](https://github.com/cloudfoundry-community/spring-boot-cf-service-broker)
- Provides credentials for consuming the sentimentr-service
- Provides a development and production plan (no difference :))
- Spring Boot app

![Alt text](/docs/ops-manager.png?raw=true "tile")

![Alt text](/docs/app-manager.png?raw=true "app-manager")

[sentimentr-client](/sentimentr-client) - exemplary client:
- Consumes sentimentr-service
- Sends text to the sentimentr-service and presents the score received from sentimentr-service
- Spring Boot app, spring cloud, bootstrap, angularjs

![Alt text](/docs/sentimentr-client.png?raw=true "sentimentr-client")

Options to run the project:
- All apps on local machine
- All apps in elastic runtime
- Service and broker deployed via bosh/bosh release on AWS or bosh-lite and client app in elastic runtime 
- Service and broker deployed via Pivotal CF Ops Manager and client app in elastic runtime 

# Quick start
- The tile and client app is available on [google drive](https://drive.google.com/open?id=0B-JMhN27hCC8b0JVUFlHc3F6VE0&authuser=0).
- Import the sentimentr.pivotal file in ops manager.
- Configure the AZ and hit deploy.
- Create a sentimentr service instance (with app manager or cf cli).
- Edit the manifest.yml in the client folder and change the servicename to the one you have just created.
- Push the client app.

# Prerequisite for advanced work with bosh
On your local machine:
- [bosh cli](https://github.com/cloudfoundry-community/traveling-bosh)
- [bosh-lite](https://github.com/cloudfoundry/bosh-lite) with cloud foundry installed 
- [spiff](https://github.com/cloudfoundry-incubator/spiff/)
- [cf commandline](https://github.com/cloudfoundry/cli)
- java jdk and maven

# How you get started with bosh/bosh release
- Clone this project
- Cd into the sentimentr-release folder
- Target bosh lite with your bosh cli
- Target cf api with your cf cli 
- Execute: [./scripts/make_lite_manifest](/scripts/make_lite_manifest)
	- Generates the sentimentr-manifest.yml pointing to your director and fills in templates with [spiff](https://github.com/cloudfoundry-incubator/spiff/) 
- Execute: [./scripts/add_sec_rule](/scripts/add_sec_rule) 
	- (required on bosh lite ==> configures a security group that allows the app to communicate with the service)
- Execute: bosh upload release [releases/sentimentr-release/sentimentr-release-8.yml](/releases/sentimentr-release/sentimentr-release-8.yml)
	- gets the sentimentr-release packages from remote blobstore and uploads the release.
- Execute: bosh deploy
	- deploys the sentimentr-release
- Execute: bosh vms
	- shows all bosh deployed job/vms inclusive the two sentimentr jobs 
- Execute: bosh run errand broker-registrar
	- registers the service broker with elastic runtime
- Execute: cf create-service  sentimentr development mysenti
	- creates a sentimentr service instance  
- Edit the [manifest.yml](/sentimentr-client/manifest.yml) in the sentimentr-client folder and change the servicename to the one you have just created.
- Execute: cf push
- Point your browser to the sentimentr-client route

#Extend the sentimentr-release
- You can extend and change the sentimentr-release (jobs, src, packages, templates, ...)
	-  For example: Create your own bosh developer sentimentr-release, upload and deploy it.
		- bosh -n create release --force && bosh -n upload release && bosh -n deploy
- Note: you need to have maven and a jdk on your machine 
	
#Extend the sentimentr-tile
Once you finsihed working on your release, you can create a *.pivotal file containing your ops manager tile.
- Execute: bosh create release --with-tarball --force
	- this creates a developer release manifest and a developer release tarball 
- Edit the [sentimentr-tile.yml](/sentimentr-tile.yml) (here is some [documentation](http://docs.pivotal.io/pivotalcf/packaging/))
	- Change the product_version, releases and provides_product_version properties ==> e.g. I created a release with version '8+dev.2' on my machine. Just increment the numbers for the product versions.
```
name: Sentimentr                                    
product_version: 1.0.1.1                                     
metadata_version: "1.3"                                    
target_installer_version: 1.3
requires_product_versions:
- name: cf
  version: "~> 1.3"
stemcell_criteria:
  os: ubuntu-trusty
  version: '2865.1'
  requires_cpi: false
releases:                                                 
  - name: sentimentr-release
    file: sentimentr-release-8.tgz
    version: '8'
provides_product_versions:
- name: sentimentr
  version: 1.0.1.0
```
- Execute: [createTileWithDevRelease.sh](/createTileWithDevRelease.sh)
	- This creates a sentimentr.pivotal file in the sentimentr-release folder
- Before you import the file delete the currently deployed sentimentr tile and hit apply changes in the ops manager
	- The tile is not upgradable - the product version number needs to be different when ever you import a tile.

# How to consume the service in own java applications
The sentimentr-client project contains two sub projects (sentimentr-connector and sentimentr-ui). The sentimentr-connector sub project builds the 'sentimentr-connector.jar' required in sentimentr-ui and also in your own application.

The sentimentr-ui project uses the sentimentr-connector dependency like this in the maven [pom.xml](/sentimentr-client/sentimentr-ui/pom.xml)

		<dependency>
			<groupId>pivotal.sentimentr</groupId>
			<artifactId>sentimentr-connector</artifactId>
			<version>0.0.1-SNAPSHOT</version>
		</dependency>

In order to send text to the sentimentr service and to receive the score in your [own application](/sentimentr-client/sentimentr-ui/src/main/java/io/pivotal/fe/sentimentr/client/controller/SentimentrClientController.java) add code like this

			return sentimentrFacade.getSentiment(text);
