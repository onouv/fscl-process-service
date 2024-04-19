package org.fscl.process.service.function.adapters.downstream.persistence;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import jakarta.enterprise.context.ApplicationScoped;
import org.fscl.core.domain.entity.id.FsclEntityId;
import org.fscl.process.service.function.domain.Function;

import java.util.Optional;

@ApplicationScoped
public class FunctionRepo implements PanacheRepository<Function> {
    public Optional<Function> findById(FsclEntityId id) {
        return find("project = ?1 and code = ?2", id.project(), id.code()).firstResultOptional();
    }
}
