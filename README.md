# sentimentr-release
sentiment service for bosh

Just some comments for building and running the sentimentr-service-broker

# Build Sentimentr-Service-Broker
The servicebroker is based on https://github.com/cloudfoundry-community/spring-boot-cf-service-broker.git
clone the repo and execute ./gradlew assemble. This will add the dependency to your local repo.

Clone this repo and execute ./gradlew assemble

# Run Service Broker
java -jar build/libs/spring-boot-cf-service-broker-sentimentr.war --sentimentrServiceIP=ip
