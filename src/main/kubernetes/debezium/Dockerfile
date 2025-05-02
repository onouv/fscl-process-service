FROM quay.io/strimzi/kafka:0.45.0-kafka-3.9.0
USER root:root
RUN mkdir -p /opt/kafka/plugins/debezium
COPY ./debezium-connector-postgres /opt/kafka/debezium
USER 1001
