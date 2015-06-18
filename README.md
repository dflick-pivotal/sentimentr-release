# sentimentr-release
Project: Pivotal cf nlp natural language processing service.

Contents: Pivotal cf ops manager tile, bosh release, service, broker and exemplary client app. 
 
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

Possible execution:
- All apps on local machine
- All apps in elastic runtime
- Service and broker deployed via bosh/bosh release on AWS or bosh-lite and client app in elastic runtime 
- Service and broker deployed via Pivotal CF Ops Manager and client app in elastic runtime 

Service binding options:
- Managed Service
- User provided service 

# Using pivotal cf ops manager

- The tile and client app is available on google drive.
 1. Import the sentimentr.pivotal file in ops manager and hit deploy.
 2. Create a sentimentr service instance.
 3. Edit the manifest.yml in the client folder and change the servicename to the one you have just created.
 4. Push the client app.
 
- For own experiements creating bosh releases or pivotal cf ops manager tiles clone this project.

