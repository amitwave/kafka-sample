bin/zookeeper-server-start.sh config/zookeeper.properties
Open another terminal session and run:

# Start the Kafka broker service
bin/kafka-server-start.sh config/server.properties

run http://localhost:8080/wave/health-check to publish data