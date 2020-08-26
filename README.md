# kafka-elasticsearch-connector

Java application to read messages from Apache Kafka topics and stream it to\
Elasticsearch cluster

## Prerequisites

Running Apache Kafka server
Elasticsearch cluster
Java 11 or docker installed

## Assumptions

It is assumed that both Apache Kafka and Elasticsearch are running in the same machine\
as this application. If not you need to specify the hostname in `application.yml` file.

It is also assumed that all messages will be sent and read from `topics` topic on kafka\
and 'logstash' index name is used as default

If you want to receive messages on different topics then change the topic name in\
`KafkaTopicListener.java` file.\
If you want to change the index name for elasticsearch then change the value in\
`Message.java` file.

Once all these changes are done, you can build the entire project or docker image.

## Building project

The application can be built using\
`mvn clean project -DskipTests`

Docker image can be built using\
`./mvnw spring-boot:build-image`

## Running the project

You can run the jar file using\
`java -jar target/kafka-elasticsearch-connector.jar`

or start the docker container using\
`docker run -d -it -p 8080:8080 --network host --name kafka-elasticksearch-connector <YOUR docker hub USERNAME>/kafka-elasticsearch-connector`
