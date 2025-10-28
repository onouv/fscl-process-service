package org.fscl.process.adapters.driven.messaging;

import org.fscl.core.domain.function.FunctionCreatedEvent;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
//import jakarta.persistence.EntityManager;
//import jakarta.persistence.PersistenceContext;

@ApplicationScoped
public class DebeziumEventService {

    //@PersistenceContext
    //private EntityManager entityManager;
    
    public void onFunctionCreated(@Observes FunctionCreatedEvent event) {

    }    
}
