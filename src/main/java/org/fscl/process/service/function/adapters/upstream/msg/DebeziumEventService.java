package org.fscl.process.service.function.adapters.upstream.msg;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@ApplicationScoped
public class DebeziumEventService {

    @PersistenceContext
    private EntityManager entityManager;
    
    


}
