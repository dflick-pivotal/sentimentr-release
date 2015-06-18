# sentimentr-release
Project Theme: NLP natural language processing service.

Project contents: Pivotal cf ops manager tile, bosh release, service, broker and exemplary client app. 
 
Sentimentr service: 
- provides a sentiment analytics service.
- uses the CoreNLP library (http://nlp.stanford.edu/software/corenlp.shtml) from stanford university.
- spring boot app

Sentimentr service broker:
- based on spring-boot-cf-service-broker (https://github.com/cloudfoundry-community/spring-boot-cf-service-broker)
- provides credentials for accessing the sentimentr service
- spring boot app

Exemplary client:
- Consumes the sentimentr service
- Sends text to the sentimentr service and provides the score analyced by the sentimentr service via the ui
- spring boot app, spring cloud, bootsrap, angularjs

Possible execution:
- All apps on local machine
- All apps in elastic runtime
- Servic and broker deployed via bosh/bosh release and client app in elastic runtime 
- Servic and broker deployed via Pivotal CF Ops Manager and client app in elastic runtime 

Service binding options:
- Managed Service with different service plans - via service broker
- User provided service 

# Tile
- For a quick look - the tile and client app is available on google drivesentimentr-release.
- For own experiements with creating a bosh release or the tile clone this project.

