# Hexagonal Design Guideline

The core lib should contain primarily data types/ classes such as entities, DTOs, and utilities like data mappers, etc.  which are applicable across views. Injected Services and the like should be implemented as part of the view microservice. The package structures of both artifacts should be similar to allow for easier orientation, i.e. packages constituting the hexagonal shells should be present in the core lib as well, if there are classes belonging to such a shell.

Strictly common behavior, such as the data management algorithms should be made available in the core lib as reusable utilities that can be instanciated and used in view services. Essentially, the core lib should provide as complete as possible sets of classes for data and manipulation as possible.


1) The adapters hexagonal shell should encapsulate all technology dependencies. I.e., if messaging is done with debezium and kafka, all classes with dependencies to these technologies should be located in the `adapters` shell. It should be possible to exchange i.e. kafka to another messaging technology w/o influencing the `ports` or `application` packages.
2) Therefore, all dependencies from `adapters` must go strictly go to external packages or to the `ports`. All dependencies from `application` shell classes must go either to `port` or to `domain`. `domain` must not depend on anything from the hexagonal.
3) Service Interfaces and DTOs must be provided as needed in the `ports` shell. 
4) If messaging objects are needed for the technologies, they should be part of the `adapters` package, unless the ports DTO is directly applicable. Such objects may use a `static of(PortDto dto)` method for their creation. More advanced mapper services should be part of the appropriate `adapters` or `application` packages. 

# Multi-Aggregate MicroService 

Ideally, each Microservice should host exactly one aggregate root, i.e. it should represent functionality for one single context.

This is not the case here, because the four core concepts (F, S, C , L) are ranking equally in all contexts, and a proper single aggregate root for the process context has not been identified, yet. "Process" would be the obvious candidate, but seems a bit stale at this time.

Elaborating the [Example](https://github.com/onouv/fscl/blob/main/doc/views/process-view/example.md) might shed new light on this in the future.

# [Debug Setup](debug-setup.md)

# [Startup and Teardown Kafka and Strimzi](strimzi-kafka.md)

# [Connect to Postgres from outside k8s](k8s-psql.md)