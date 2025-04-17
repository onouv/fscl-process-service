

# Multi-Aggregate MicroService 

Ideally, each Microservice should host exactly one aggregate root, i.e. it should represent functionality for one single context.

This is not the case here, because the four core concepts (F, S, C , L) are ranking equally in all contexts, and a proper single aggregate root for the process context has not been identified, yet. "Process" would be the obvious candidate, but seems a bit stale at this time.

Elaborating the [[Example]] might shed new light on this in the future.
