# sentimentr-release
Project: Pivotal cf natural language processing (nlp) service tile.

Contents: Sentimentr service tile for pivotal cf ops manager, bosh release, service, broker and exemplary client app. 
 
Sentimentr service: 
- provides a sentiment analysis service
- Stanford [CoreNLP](http://nlp.stanford.edu/software/corenlp.shtml) library
- spring boot app

Sentimentr service broker:
- based on [spring-boot-cf-service-broker](https://github.com/cloudfoundry-community/spring-boot-cf-service-broker)
- provides credentials for accessing the sentimentr service
- provides a development and production plan (no difference :))
- spring boot app

![Alt text](/docs/ops-manager.png?raw=true "tile")

![Alt text](/docs/app-manager.png?raw=true "app-manager")

Exemplary client:
- consumes the sentimentr service
- sends text to the sentimentr service and presents the score received from sentimentr service
- spring boot app, spring cloud, bootstrap, angularjs

![Alt text](/docs/sentimentr-client.png?raw=true "sentimentr-client")

Options to run the apps:
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
- [bosh cli](use: https://github.com/cloudfoundry-community/traveling-bosh)
- [bosh-lite](https://github.com/cloudfoundry/bosh-lite) with cloud foundry installed 
- [cf commandline](use: https://github.com/cloudfoundry/cli)
- java jdk and maven

# How you get started with bosh/bosh release
- clone this project
- cd into the sentimentr-release folder
- target bosh lite with your bosh cli
- execute: ./scripts/make_lite_manifest.sh
	- generates the sentimentr-manifest.yml pointing to your director and filling in templates with [spiff](https://github.com/cloudfoundry-incubator/spiff/) 
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

#Extend the sentimentr-release
- you can change the jobs, src, packages, templates and create for example a bosh developer sentimentr-release => upload and ==> deploy it like this
	- bosh -n create release --force && bosh -n upload release && bosh -n deploy
- note: you need to have maven and a jdk on your machine 
	
#Extend the sentimentr-tile
Once you finsihed working on your release, you can create a *.pivotal file containing your ops manager tile.

- execute: bosh create release --with-tarball --force
	- this creates a release manifest and a release tarball 
- edit the sentimentr-tile.yml and change the relese file and version ==> on my machine with the dev release created - 8+dev.2
releases:                                                 
  - name: sentimentr-release
    file: sentimentr-release-8.tgz
    version: '8'
- change the product version
product_version: 1.0.1.1                                     
- name: sentimentr
  version: 1.0.1.0
- execute: createTileWithDevRelease.sh
- this creates a sentimentr.pivotal file 
- before you import the file delete and aply changes in the ops manager first - the tile is not upgradable

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
