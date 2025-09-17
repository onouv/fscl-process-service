

# Multi-Aggregate MicroService 

Ideally, each Microservice should host exactly one aggregate root, i.e. it should represent functionality for one single context.

This is not the case here, because the four core concepts (F, S, C , L) are ranking equally in all contexts, and a proper single aggregate root for the process context has not been identified, yet. "Process" would be the obvious candidate, but seems a bit stale at this time.

Elaborating the [Example](https://github.com/onouv/fscl/blob/main/doc/views/process-view/example.md) might shed new light on this in the future.

# [Debug Setup](debug-setup.md)

# [Startup and Teardown Kafka and Strimzi](strimzi-kafka.md)

# [Connect to Postgres from outside k8s](k8s-psql.md)