package org.fscl.process.service.function.adapters.downstream.persistence;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.Optional;

import org.fscl.core.domain.entity.id.FsclEntityId;
import org.fscl.process.service.function.domain.Function;

@ApplicationScoped
public class FunctionRepository implements PanacheRepository<Function> {

    public Optional<Function> findById(FsclEntityId id) throws PersistenceException {
        final PanacheQuery<Function> q = find("entityId = ?1", id);
        Optional<Function> opt = q.firstResultOptional();
        return opt;
    }
}
